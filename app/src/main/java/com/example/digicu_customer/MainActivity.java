package com.example.digicu_customer;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct == null) {
            finish();
        }

        String personName = acct.getDisplayName();
        String personGivenName = acct.getGivenName();
        String personFamilyName = acct.getFamilyName();
        String personEmail = acct.getEmail();
        String personId = acct.getId();
        Uri personPhoto = acct.getPhotoUrl();

        Log.d(GeneralVariable.TAG, "onCreate: personName : " + personName);
        Log.d(GeneralVariable.TAG, "onCreate: personGivenName : " + personGivenName);
        Log.d(GeneralVariable.TAG, "onCreate: person" +
                "FamilyName : " + personFamilyName);
        Log.d(GeneralVariable.TAG, "onCreate: personEmail : " + personEmail);
        Log.d(GeneralVariable.TAG, "onCreate: personId : " + personId);
    }
}