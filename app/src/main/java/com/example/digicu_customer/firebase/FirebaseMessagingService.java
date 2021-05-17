package com.example.digicu_customer.firebase;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.digicu_customer.ui.login.LoginActivity;
import com.google.firebase.messaging.RemoteMessage;

import java.util.List;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private final String TAG = "Firebase_log";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(100);

        if (taskInfo.get(0).topActivity.getClassName().equals(LoginActivity.class.getName())) {
            return;
        }

        Log.d(TAG, remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Message data payload: " + remoteMessage.getData());

            handleNow();
        }



        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body : " + remoteMessage.getNotification().getBody());

            String getMessage = remoteMessage.getNotification().getBody();

            if (TextUtils.isEmpty(getMessage)) {
                Log.d(TAG, "ERR : Message data is empty");
            } else {

                Log.d(TAG, "onMessageReceived: " + getMessage);
//                Intent intent = new Intent(getApplicationContext(), WaitingActivity.class);
//                intent.putExtra("showType", getMessage);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
            }
        }
    }

    private void handleNow() {
        Log.d(TAG, "Short lived task is done.");
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