package com.example.digicu_customer.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.digicu_customer.R;
import com.example.digicu_customer.general.GeneralVariable;
import com.example.digicu_customer.ui.main.couponinfo.CouponInfoFragment;
import com.example.digicu_customer.ui.main.home.HomeFragment;
import com.example.digicu_customer.ui.main.menu.MenuFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
//    HomeFragment homeFragment;
//    MenuFragment menuFragment;
//    CouponInfoFragment couponInfoFragment;

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

        Log.d(GeneralVariable.TAG, "onCreate: personName : " + personName);
        Log.d(GeneralVariable.TAG, "onCreate: personGivenName : " + personGivenName);
        Log.d(GeneralVariable.TAG, "onCreate: person" +
                "FamilyName : " + personFamilyName);
        Log.d(GeneralVariable.TAG, "onCreate: personEmail : " + personEmail);
        Log.d(GeneralVariable.TAG, "onCreate: personId : " + personId);
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
}