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
import com.example.digicu_customer.general.GeneralVariable;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Google login
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        String personName = acct.getDisplayName();
        String personGivenName = acct.getGivenName();
        String personFamilyName = acct.getFamilyName();
        String personEmail = acct.getEmail();
        String personId = acct.getId();

        Log.d(GeneralVariable.TAG, "onCreate: IdToken : " + acct.getIdToken());
//        Log.d(GeneralVariable.TAG, "onCreate: ServerAuthCode : " + acct.getServerAuthCode());
        Log.d(GeneralVariable.TAG, "onCreate: GrantedScope : " + acct.getGrantedScopes().toString());
        Log.d(GeneralVariable.TAG, "onCreate: personName : " + personName);
        Log.d(GeneralVariable.TAG, "onCreate: personGivenName : " + personGivenName);
        Log.d(GeneralVariable.TAG, "onCreate: person" +
                "FamilyName : " + personFamilyName);
        Log.d(GeneralVariable.TAG, "onCreate: personEmail : " + personEmail);
        Log.d(GeneralVariable.TAG, "onCreate: personId : " + personId);
        Log.d(GeneralVariable.TAG, "onCreate: photoURL : " + acct.getPhotoUrl());
        // Google Login test

//        homeFragment = new HomeFragment();
//        menuFragment = new MenuFragment();
//        couponInfoFragment = new CouponInfoFragment();

        if (savedInstanceState == null) {
            setUpBottomNav();
        }
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