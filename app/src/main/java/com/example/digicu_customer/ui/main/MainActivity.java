package com.example.digicu_customer.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.digicu_customer.R;
import com.example.digicu_customer.auth.DigicuAuth;
import com.example.digicu_customer.data.dataset.DigicuTokenDataModel;
import com.example.digicu_customer.data.dataset.ShopDataModel;
import com.example.digicu_customer.data.dataset.SocialUserDataModel;
import com.example.digicu_customer.data.remote.ApiUtils;
import com.example.digicu_customer.data.remote.DigicuUserService;
import com.example.digicu_customer.data.remote.RetrofitClient;
import com.example.digicu_customer.firebase.NotificationSetting;
import com.example.digicu_customer.general.GeneralVariable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final int COUPON_SELECT_REQUEST = 10001;

    BottomNavigationView bottomNavigationView;
    SocialUserDataModel socialUserDataModel;
    ImageButton coupon_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        socialUserDataModel = (SocialUserDataModel) getIntent().getSerializableExtra("social_data");
        RetrofitClient.setSocialUserDataModel(socialUserDataModel);
        Log.d(GeneralVariable.TAG, "onCreate: " + socialUserDataModel.toString());

        Log.d(GeneralVariable.TAG, "onCreate Digicu Token: " + DigicuAuth.getToken(socialUserDataModel));
        Log.d("_log", "FCM token : " + FirebaseInstanceId.getInstance().getToken());

        NotificationSetting.buildNotificationChannel(this);

        if (savedInstanceState == null) {
            setUpBottomNav();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // token expire test
        DigicuUserService digicuUserService = ApiUtils.getDigicuUserService();

        digicuUserService.getCompanies(1, 1, "company_name", false).enqueue(new Callback<List<ShopDataModel>>() {
            @Override
            public void onResponse(Call<List<ShopDataModel>> call, Response<List<ShopDataModel>> response) {
                Log.d(GeneralVariable.TAG, "onResponse: " + response.code());
                if (response.code() == 401) { // token expire
                    digicuUserService.patchSocial(socialUserDataModel).enqueue(new Callback<DigicuTokenDataModel>() {
                        @Override
                        public void onResponse(Call<DigicuTokenDataModel> call, Response<DigicuTokenDataModel> response) {
                            if (response.code() == 200) {
                                DigicuAuth.setToken(response.body().getToken(), socialUserDataModel);
                            } else {
                                Log.d(GeneralVariable.TAG, "onResponse: " + response.code());
                            }
                        }

                        @Override
                        public void onFailure(Call<DigicuTokenDataModel> call, Throwable t) {
                            Log.d(GeneralVariable.TAG, "onFailure: " + t.getMessage());
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<ShopDataModel>> call, Throwable t) {
                Log.d(GeneralVariable.TAG, "onFailure: " + t.getMessage());
            }
        });

        Log.d(GeneralVariable.TAG, "onResume: Main");
    }

    private void setUpBottomNav() {
        bottomNavigationView = findViewById(R.id.digicu_main_bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.d(GeneralVariable.TAG, "onNavigationItemSelected: " + item.getTitle());

                return true;
            }
        });

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.digicu_main_nav_host);
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.getNavController());
    }

//    @Override
//    public boolean onSupportNavigateUp() {
////        NavHostFragment.findNavController(couponInfoFragment).navigate(R.id);
//        Log.d(GeneralVariable.TAG, "onSupportNavigateUp: ");
//        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.digicu_main_nav_host);
//        NavController navController = navHostFragment.getNavController();
//
//        return navController.navigateUp() || super.onSupportNavigateUp();
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(GeneralVariable.TAG, "onActivityResult: main");
    }
}