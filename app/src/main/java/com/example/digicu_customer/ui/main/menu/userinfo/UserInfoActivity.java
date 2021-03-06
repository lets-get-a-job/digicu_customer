package com.example.digicu_customer.ui.main.menu.userinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.digicu_customer.R;
import com.example.digicu_customer.firebase.NotificationSetting;
import com.example.digicu_customer.general.GeneralVariable;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {

    TextView userName;
    TextView userEmail;
    TextView subTitle;
    TextView changeSimplePass;
    TextView checkAuth;
    ImageView userImage;
    SwitchMaterial notificationSwitch;
    private GoogleSignInAccount account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

//        setupToolBar();
        setupObj();
        setupListener();
        viewData();
    }

    private void setupListener() {
        changeSimplePass.setOnClickListener(this);
        checkAuth .setOnClickListener(this);
        notificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                NotificationSetting.changeNotificationPermission(UserInfoActivity.this, b);
                Log.d(GeneralVariable.TAG, "onCheckedChanged: " + NotificationSetting.getCurrentNotificationPermission(UserInfoActivity.this));
            }
        });
    }

    private void viewData() {
        account = GoogleSignIn.getLastSignedInAccount(this);

        userName.setText(account.getDisplayName());
        userEmail.setText(account.getEmail());
        Glide.with(this).load(account.getPhotoUrl())
                .fitCenter()
                .into(userImage);

        notificationSwitch.setChecked(NotificationSetting.getCurrentNotificationPermission(this));
    }

    private void setupObj() {
        userName = findViewById(R.id.profile_user_name);
        userImage = findViewById(R.id.profile_user_image);
        subTitle = findViewById(R.id.profile_sub_title);
        userEmail = findViewById(R.id.user_info_email);
        changeSimplePass = findViewById(R.id.user_info_change_pass);
        checkAuth = findViewById(R.id.user_info_auth);
        notificationSwitch = findViewById(R.id.notification_switch);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.user_info_auth) {
            Log.d(GeneralVariable.TAG, "onClick: Check user Auth");
        } else if(view.getId() == R.id.user_info_change_pass) {
            Log.d(GeneralVariable.TAG, "onClick: Change simple password");
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.out_to_down, R.anim.out_to_down);
    }
}