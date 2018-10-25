package com.auribises.gw2018c;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action.equals(Intent.ACTION_BATTERY_LOW)){
            // show the notification to the User that battery is LOW
        }

        if(action.equals(Intent.ACTION_PACKAGE_ADDED)){

        }

        if(action.equals(Intent.ACTION_PACKAGE_REMOVED)){

        }
    }
}
// Assignment : Create a Broadcast Receiver which will read an incoming SMS
//              If contents of SMS are LOST, start location fetching and send the message back to the one from where it was received