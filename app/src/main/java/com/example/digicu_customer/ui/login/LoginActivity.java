package com.example.digicu_customer.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.digicu_customer.R;
import com.example.digicu_customer.data.dataset.SocialUserDataModel;
import com.example.digicu_customer.data.remote.ApiUtils;
import com.example.digicu_customer.data.remote.DigicuService;
import com.example.digicu_customer.general.GeneralVariable;
import com.example.digicu_customer.ui.main.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

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

    @Override
    protected void onStart() {
        super.onStart();

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        updateUI(account);
    }

    private void updateUI(GoogleSignInAccount account) {
        if (account != null) {
            // Check Server

            DigicuService digicuService = ApiUtils.getDigicuService();

            // make SocialUserDataModel
            SocialUserDataModel socialUserDataModel = new SocialUserDataModel(
                    account.getId(),
                    account.getIdToken(),
                    account.getEmail(),
                    account.getDisplayName(),
                    account.getPhotoUrl().toString(),
                    account.getPhotoUrl().toString(),
                    new Date(),
                    new Date()
            );

            Log.d(GeneralVariable.TAG, "updateUI: " + socialUserDataModel.toString());

            digicuService.getSocial(socialUserDataModel.getSocial_id()).enqueue(new Callback<SocialUserDataModel>() {
                @Override
                public void onResponse(Call<SocialUserDataModel> call, Response<SocialUserDataModel> response) {
                    Log.d(GeneralVariable.TAG, "getSocial : " + response.code());

                    if(response.code() == 404) {
                        // not digicu user
                        digicuService.postSocial(socialUserDataModel).enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Log.d(GeneralVariable.TAG, "postSocial: " + response.code());
                                switch (response.code()) {
                                    case 200:
                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                        finish();
                                        break;
                                    case 400:
                                    case 500:
                                        Toast.makeText(LoginActivity.this, "digicu server error", Toast.LENGTH_LONG).show();
                                        break;
                                    default:
                                        Toast.makeText(LoginActivity.this, "unknown res code", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Log.d(GeneralVariable.TAG, "postSocial onFailure: " + t.getMessage());
                                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    } else if(response.code() == 200) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<SocialUserDataModel> call, Throwable t) {
                    Log.d(GeneralVariable.TAG, "getSocial onFailure: " + t.getMessage());
                }
            });
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GOOGLE_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
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