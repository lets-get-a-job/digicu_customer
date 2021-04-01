package com.example.digicu_customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView mBottomNavigationView;
    HomeFragment homeFragment;
    MenuFragment menuFragment;

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

        mBottomNavigationView = findViewById(R.id.digicu_main_bottom_navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);

        homeFragment = new HomeFragment();
        menuFragment = new MenuFragment();

        // First Fragment is HomeFragment
        getSupportFragmentManager().beginTransaction().replace(R.id.digicu_main_fg_linearLayout, homeFragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.page_1:
                getSupportFragmentManager().beginTransaction().replace(R.id.digicu_main_fg_linearLayout, homeFragment).commit();
                return true;
            case R.id.page_2:
                return true;
            case R.id.page_3:
                return true;
            case R.id.page_4:
                getSupportFragmentManager().beginTransaction().replace(R.id.digicu_main_fg_linearLayout, menuFragment).commit();
                return true;
        }

        return false;
    }
}