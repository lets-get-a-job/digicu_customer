package com.example.digicu_customer.ui.login.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.digicu_customer.R;
import com.example.digicu_customer.auth.DigicuAuth;
import com.example.digicu_customer.data.dataset.DigicuTokenDataModel;
import com.example.digicu_customer.data.dataset.SocialUserDataModel;
import com.example.digicu_customer.data.remote.ApiUtils;
import com.example.digicu_customer.data.remote.DigicuUserService;
import com.example.digicu_customer.general.GeneralVariable;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int SIGNUP_REQUEST = 10001;
    public static final int SIGNUP_SUCCESS = 20001;
    public static final int SIGNUP_FAIL = 20002;

    SocialUserDataModel socialUserDataModel;

    MaterialButton signUpBtn;
    TextView social_domain;
    TextView social_email;
    TextView social_name;
    EditText simplePW;
    EditText simplePWCheck;
    EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpBtn = findViewById(R.id.signup_btn);
        social_domain = findViewById(R.id.signup_social_domain);
        social_email = findViewById(R.id.signup_email);
        social_name = findViewById(R.id.signup_name);
        simplePW = findViewById(R.id.signup_simple_pw);
        simplePWCheck = findViewById(R.id.signup_simple_pw_check);
        phone = findViewById(R.id.signup_phone_number);

        socialUserDataModel = (SocialUserDataModel) getIntent().getSerializableExtra("account");

        if (socialUserDataModel == null) {
            setResult(SIGNUP_FAIL);
            finish();
        }

        Log.d(GeneralVariable.TAG, "onCreate: " + socialUserDataModel.toString());

        // Todo modify
        social_domain.setText("Google");
        social_email.setText(socialUserDataModel.getEmail());
        social_name.setText(socialUserDataModel.getNickname());

        signUpBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        DigicuUserService digicuUserService;

        if (view.getId() == R.id.signup_btn) {
            if (phone.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please input your phone number", Toast.LENGTH_LONG).show();
                return;
            }

            socialUserDataModel.setPhone(phone.getText().toString().replaceAll("[-]", ""));

            Log.d(GeneralVariable.TAG, "onClick: " + socialUserDataModel.toString());

            digicuUserService = ApiUtils.getDigicuUserService();

            digicuUserService.postSocial(socialUserDataModel).enqueue(new Callback<DigicuTokenDataModel>() {
                @Override
                public void onResponse(Call<DigicuTokenDataModel> call, Response<DigicuTokenDataModel> response) {
                    Log.d(GeneralVariable.TAG, "postSocial: " + response.code());
                    switch (response.code()) {
                        case 200:
//                            Intent intent = new Intent();
//                            intent.putExtra("digicu_token", response.body());
                            DigicuAuth.setToken(response.body().getToken(), socialUserDataModel);

                            setResult(SIGNUP_SUCCESS);
                            finish();
                            break;
                        case 400:
                        case 500:
                            Toast.makeText(SignUpActivity.this, "digicu server error", Toast.LENGTH_LONG).show();
                            break;
                        default:
                            Toast.makeText(SignUpActivity.this, "unknown res code", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<DigicuTokenDataModel> call, Throwable t) {
                    Log.d(GeneralVariable.TAG, "postSocial onFailure: " + t.getMessage());
                    Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.signup_action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_menu_close) {
            setResult(SIGNUP_FAIL);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.out_to_down, R.anim.out_to_down);
    }
}