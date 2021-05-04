package com.example.digicu_customer.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.digicu_customer.R;
import com.example.digicu_customer.auth.DigicuAuth;
import com.example.digicu_customer.data.dataset.DigicuTokenDataModel;
import com.example.digicu_customer.data.dataset.SocialUserDataModel;
import com.example.digicu_customer.data.remote.ApiUtils;
import com.example.digicu_customer.data.remote.DigicuUserService;
import com.example.digicu_customer.general.GeneralVariable;
import com.example.digicu_customer.ui.login.signup.SignUpActivity;
import com.example.digicu_customer.ui.main.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private final int GOOGLE_SIGN_IN = 9001;
    SignInButton mLogin_btn;
    GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        mLogin_btn = (SignInButton) findViewById(R.id.digicu_google_login_btn);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mLogin_btn.setOnClickListener(this);
    }

    private void updateUI(GoogleSignInAccount account) {
        if (account != null) {
            // Check Server
            DigicuUserService digicuUserService = ApiUtils.getDigicuUserService();

            Log.d(GeneralVariable.TAG, "updateUI: " + account.getId());

            digicuUserService.getSocial(account.getId()).enqueue(new Callback<SocialUserDataModel>() {
                @Override
                public void onResponse(Call<SocialUserDataModel> call, Response<SocialUserDataModel> response) {
                    Intent intent;
                    SocialUserDataModel socialUserDataModel;
                    Log.d(GeneralVariable.TAG, "getSocial : " + response.code());
                    if(response.code() == 404) {
                        // Todo modify data model
                        socialUserDataModel = new SocialUserDataModel(
                                account.getId(),
                                account.getIdToken(),
                                account.getEmail(),
                                account.getDisplayName(),
                                account.getPhotoUrl() != null? account.getPhotoUrl().toString(): "",
                                account.getPhotoUrl() != null? account.getPhotoUrl().toString(): "",
                                new Date(),
                                new Date(),
                                null
                        );

                        intent = new Intent(getApplicationContext(), SignUpActivity.class);
                        intent.putExtra("account", socialUserDataModel);
                        startActivityForResult(intent, SignUpActivity.SIGNUP_REQUEST);
                    } else if(response.code() == 200) {
                        // get user token
                        Log.d(GeneralVariable.TAG, "onResponse: " + response.body().toString());

                        socialUserDataModel = response.body();
                        if (socialUserDataModel.getSocial_id() == null || socialUserDataModel.getSocial_id().isEmpty()) {
                            socialUserDataModel.setSocial_id(account.getId());
                        }

                        digicuUserService.patchSocial(socialUserDataModel).enqueue(new Callback<DigicuTokenDataModel>() {
                            @Override
                            public void onResponse(Call<DigicuTokenDataModel> call, Response<DigicuTokenDataModel> response) {
                                if (response.code() == 200) {
                                    Intent intent;
                                    intent = new Intent(getApplicationContext(), MainActivity.class);
                                    intent.putExtra("social_data", socialUserDataModel);

                                    DigicuAuth.setToken(response.body().getToken(), socialUserDataModel);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Log.d(GeneralVariable.TAG, "onResponse: get token error // " + response.code());
                                }
                            }

                            @Override
                            public void onFailure(Call<DigicuTokenDataModel> call, Throwable t) {
                                Log.d(GeneralVariable.TAG, "onResponse: get token error // " + t.getMessage());
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<SocialUserDataModel> call, Throwable t) {
                    Log.d(GeneralVariable.TAG, "getSocial onFailure: " + t.getMessage());
                }
            });
        } else {
            Log.d(GeneralVariable.TAG, "updateUI: not account");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.digicu_google_login_btn:
                signIn();
                break;
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN);
    }

    private void signOut() {
        // signOut routine
        mGoogleSignInClient.signOut();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GOOGLE_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        } else if(requestCode == SignUpActivity.SIGNUP_REQUEST) {
            if (resultCode == SignUpActivity.SIGNUP_SUCCESS) {
                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
                updateUI(account);
            } else {
                signOut();
            }
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);

//            Log.d(GeneralVariable.TAG, "handleSignInResult: IdToken : " + account.getIdToken());
//            Log.d(GeneralVariable.TAG, "handleSignInResult: ServerAuthCode : " + account.getServerAuthCode());
            updateUI(account);
        } catch (ApiException e) {
            Log.d(GeneralVariable.TAG, "signInResult : failed code = " + e.getStatusCode());
            updateUI(null);
        }
    }
}