package com.example.digicu_customer.auth;

import android.accounts.AccountManager;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.digicu_customer.R;
import com.example.digicu_customer.data.dataset.SocialUserDataModel;

public class DigicuAuth {
    public static Context context;

    public static void setContext(Context context) {
        DigicuAuth.context = context;
    }

    public static void setToken(String token, SocialUserDataModel socialUserDataModel) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.digicu_preference_file_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.digicu_token) + socialUserDataModel.getSocial_id(), token);
        editor.commit();
    }

    public static String getToken(SocialUserDataModel socialUserDataModel) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.digicu_preference_file_name), Context.MODE_PRIVATE);

        String token = sharedPreferences.getString(context.getString(R.string.digicu_token) + socialUserDataModel.getSocial_id(), null);

        return token;
    }

    public static void removeToken(SocialUserDataModel socialUserDataModel) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.digicu_preference_file_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(context.getString(R.string.digicu_token) + socialUserDataModel.getSocial_id());
        editor.commit();
    }
}
