package com.example.digicu_customer.ui.loading;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.digicu_customer.R;
import com.example.digicu_customer.auth.DigicuAuth;
import com.example.digicu_customer.data.dataset.DigicuTokenDataModel;
import com.example.digicu_customer.data.dataset.SocialUserDataModel;
import com.example.digicu_customer.data.remote.ApiUtils;
import com.example.digicu_customer.data.remote.DigicuCouponService;
import com.example.digicu_customer.data.remote.DigicuUserService;
import com.example.digicu_customer.general.GeneralVariable;
import com.example.digicu_customer.ui.login.LoginActivity;
import com.example.digicu_customer.ui.main.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_loading);
        DigicuAuth.setContext(this);
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
            DigicuUserService digicuUserService = ApiUtils.getDigicuUserService();

            digicuUserService.getSocial(account.getId()).enqueue(new Callback<SocialUserDataModel>() {
                @Override
                public void onResponse(Call<SocialUserDataModel> call, Response<SocialUserDataModel> response) {
                    Log.d(GeneralVariable.TAG, "getSocial : " + response.code());
                    SocialUserDataModel socialUserDataModel;

                    if(response.code() == 200) {
                        // Todo send user token data to MainActivity
                        String digicu_token = DigicuAuth.getToken(response.body());

                        Log.d(GeneralVariable.TAG, "updateUI: " + account.getId() + ", Login_User_token : " + digicu_token);

                        if (digicu_token == null || digicu_token.isEmpty()) {
                            // token 갱신 루틴
                            Log.d(GeneralVariable.TAG, "onResponse: token Nothing");

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
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Log.d(GeneralVariable.TAG, "onResponse: get token error // " + response.code());
                                    }
                                }

                                @Override
                                public void onFailure(Call<DigicuTokenDataModel> call, Throwable t) {
                                    Log.d(GeneralVariable.TAG, "onResponse: get token error // " + t.getMessage());
                                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                    finish();
                                }
                            });
                        }
                    } else {
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<SocialUserDataModel> call, Throwable t) {
                    Log.d(GeneralVariable.TAG, "getSocial onFailure: " + t.getMessage());
                    finish();
                }
            });
        } else {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            Log.d(GeneralVariable.TAG, "updateUI: not account");
        }
    }
}