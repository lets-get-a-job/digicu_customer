package com.example.digicu_customer.firebase;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.appcompat.app.AlertDialog;

import com.example.digicu_customer.R;

public class NotificationSetting {
    public static void setUpNotificationConfirmWithAlert(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.digicu_preference_file_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        builder.setMessage(context.getString(R.string.notificationServicePerm))
                .setTitle("알림")
                .setPositiveButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        editor.putString("digicu notification channel" + context.getString(R.string.digicu_token), "false");
                    }
                })
                .setNegativeButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        editor.putString("digicu notification channel" + context.getString(R.string.digicu_token), "true");
                    }
                })
                .show();

        editor.commit();
    }

    public static boolean getCurrentNotificationPermission(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.digicu_preference_file_name), Context.MODE_PRIVATE);

        String perm = sharedPreferences.getString("digicu notification channel" + context.getString(R.string.digicu_token), null);
        if (perm == null) {
            perm = "false";
        }

        return Boolean.valueOf(perm).booleanValue();
    }

    public static void changeNotificationPermission(Context context, boolean perm) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.digicu_preference_file_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String perm_s = String.valueOf(perm);
        editor.putString("digicu notification channel" + context.getString(R.string.digicu_token), perm_s);
        editor.commit();
    }

    public static void buildNotificationChannel(Context context) {
        // setup notification channel
        String channelId = context.getString(R.string.digicu_channel_id);
        String channelName = context.getString(R.string.digicu_channel_name);
        String channelDescription = context.getString(R.string.digicu_channel_description);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(channelDescription);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(context.getColor(R.color.digicu_base_primary_color));
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 100, 200});
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
