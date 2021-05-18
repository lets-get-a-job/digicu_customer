package com.example.digicu_customer.firebase;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.digicu_customer.R;
import com.example.digicu_customer.ui.loading.LoadingActivity;
import com.example.digicu_customer.ui.login.LoginActivity;
import com.example.digicu_customer.ui.main.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private final String TAG = "Firebase_log";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(100);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if (account == null) {
            return;
        }

        Log.d(TAG, remoteMessage.getFrom());

        // only use data payload
        if (remoteMessage.getData().size() > 0 && NotificationSetting.getCurrentNotificationPermission(this)) {
            Log.e(TAG, "Message data payload: " + remoteMessage.getData());
            pushMessage(remoteMessage);
            handleData(remoteMessage);
        }

        // not use notification payload
        if (remoteMessage.getNotification() != null && NotificationSetting.getCurrentNotificationPermission(this)) {
            Log.d(TAG, "Message Notification Body : " + remoteMessage.getNotification().getBody());

            String getMessage = remoteMessage.getNotification().getBody();

            if (TextUtils.isEmpty(getMessage)) {
                Log.d(TAG, "ERR : Message data is empty");
            } else {
                Log.d(TAG, "onMessageReceived: " + getMessage);
//                pushMessage(remoteMessage.getNotification());
            }
        }
    }

    private void handleData(RemoteMessage remoteMessage) {
        Log.d(TAG, "Short lived task is done.");
    }

    private void pushMessage(RemoteMessage remoteMessage) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(this, LoadingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        String channelId = getString(R.string.digicu_channel_id);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(remoteMessage.getData().get("title"))
                .setContentText(remoteMessage.getData().get("body"))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        notificationManager.notify(Integer.parseInt(channelId), notificationBuilder.build());
    }

    @Override
    public void onNewToken(@NonNull String token) {
        Log.d(TAG, "onNewToken: " + token);

        sendRegistrationToServer(token);
    }
    
    private void sendRegistrationToServer(String token) {
        // save to server
        Log.d("Firebase_log", "here ! sendRegistrationToServer! token is " + token);
    }

}