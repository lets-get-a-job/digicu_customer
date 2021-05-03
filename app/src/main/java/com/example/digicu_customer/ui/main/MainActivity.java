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

import com.example.digicu_customer.R;
import com.example.digicu_customer.auth.DigicuAuth;
import com.example.digicu_customer.data.dataset.SocialUserDataModel;
import com.example.digicu_customer.data.remote.RetrofitClient;
import com.example.digicu_customer.general.GeneralVariable;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    SocialUserDataModel socialUserDataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        socialUserDataModel = (SocialUserDataModel) getIntent().getSerializableExtra("social_data");
        RetrofitClient.setSocialUserDataModel(socialUserDataModel);
        Log.d(GeneralVariable.TAG, "onCreate: " + socialUserDataModel.toString());

        Log.d(GeneralVariable.TAG, "onCreate Digicu Token: " + DigicuAuth.getToken(socialUserDataModel));

        if (savedInstanceState == null) {
            setUpBottomNav();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

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

    @Override
    public boolean onSupportNavigateUp() {
//        NavHostFragment.findNavController(couponInfoFragment).navigate(R.id);
        Log.d(GeneralVariable.TAG, "onSupportNavigateUp: ");
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.digicu_main_nav_host);
        NavController navController = navHostFragment.getNavController();

        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(GeneralVariable.TAG, "onActivityResult: main");
    }
}