package com.example.digicu_customer.ui.loading;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.digicu_customer.R;
import com.example.digicu_customer.data.dataset.SocialUserDataModel;
import com.example.digicu_customer.data.remote.ApiUtils;
import com.example.digicu_customer.data.remote.DigicuService;
import com.example.digicu_customer.general.GeneralVariable;
import com.example.digicu_customer.ui.login.LoginActivity;
import com.example.digicu_customer.ui.login.signup.SignUpActivity;
import com.example.digicu_customer.ui.main.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_loading);
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
            DigicuService digicuService = ApiUtils.getDigicuUserService();

            Log.d(GeneralVariable.TAG, "updateUI: " + account.getId());

            digicuService.getSocial(account.getId()).enqueue(new Callback<SocialUserDataModel>() {
                @Override
                public void onResponse(Call<SocialUserDataModel> call, Response<SocialUserDataModel> response) {
                    Log.d(GeneralVariable.TAG, "getSocial : " + response.code());

                    if(response.code() == 200) {
                        // Todo send user token data to MainActivity
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }

                    finish();
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