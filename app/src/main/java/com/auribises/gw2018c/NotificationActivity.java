package com.auribises.gw2018c;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NotificationActivity extends AppCompatActivity {

    NotificationManager notificationManager;
    NotificationCompat.Builder builder;
    Notification notification;

    String channelId = "myChannelId";
    int notificationId = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
    }

    void showNotification(){

        //1. Create NotificationManager : Show the Notification
        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        //2. NotificationChannel for Android Versions >= Oreo
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,"Channel",NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        //3. Create Notification
        builder = new NotificationCompat.Builder(this,channelId);
        builder.setSmallIcon(R.drawable.music);
        builder.setContentTitle("This is Notification Title");
        builder.setContentText("This is Notification Text");

        // Vibration : Permission for Vibration required in Manifest
        builder.setDefaults(Notification.DEFAULT_ALL); // Default Settings Applied

        // Handling Notification Clicks
        Intent intent = new Intent(NotificationActivity.this,AllUsersActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,111,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        // BigTextStyle Notification : Will be able to add Buttons (Action Buttons)
        builder.setStyle(new NotificationCompat.BigTextStyle());
        builder.addAction(android.R.drawable.ic_menu_add,"Add",pendingIntent);
        builder.addAction(android.R.drawable.ic_menu_delete,"Delete",pendingIntent);

        //4. Obtain Notification from Builder
        notification = builder.build();

        //5. Show Notification
        notificationManager.notify(notificationId,notification);
    }

    // Assignment: 1. When you reach your home or your office. Show a Notification: Welcome to Home or Welcome to Office.
    //             2. Create an App for Location Based Reminders
    //                Use Media Player to play an audio

    public void clickHandler(View view){
        showNotification();
    }
}
