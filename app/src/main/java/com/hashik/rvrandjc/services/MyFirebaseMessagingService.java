package com.hashik.rvrandjc.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.hashik.rvrandjc.models.Constants;
import com.hashik.rvrandjc.models.MyNotificationManager;

public class MyFirebaseMessagingService extends FirebaseMessagingService{

    private static final String TAG = "FirebaseMessaging";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "onMessageReceived: Got the message!");

        //Assigning a channel which is must from API 26 and above
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(Constants.CHANNEL_ID, Constants.CHANNEL_NAME, importance);
            mChannel.setDescription(Constants.CHANNEL_DESCRIPTION);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mNotificationManager.createNotificationChannel(mChannel);
        }

        //if the message contains data payload
        if(remoteMessage.getData().size() > 0){
            String title = remoteMessage.getData().get("title");
            String body = remoteMessage.getData().get("body");
            int id;
            try{
                id = Integer.parseInt(remoteMessage.getData().get("id"));
            }catch (Exception e){
                Log.e(TAG, "onMessageReceived: ID is missing");
                id = 555; //Default ID
            }

            if (title != null && body != null) {
                MyNotificationManager.getInstance(getApplicationContext()).
                        displayNotification(title, body,id);
            }
        }
    }
}
