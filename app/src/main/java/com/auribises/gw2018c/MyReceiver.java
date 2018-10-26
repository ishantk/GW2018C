package com.auribises.gw2018c;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyReceiver extends BroadcastReceiver {

    Context cxt;

    @Override
    public void onReceive(Context context, Intent intent) {

        cxt = context;

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


   /* public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in India and move the camera
        LatLng india = new LatLng(30.9022866,75.8201693);
        mMap.addMarker(new MarkerOptions().position(india).title("Marker in India").snippet("This is Snippet"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(india));

//        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        mMap.setTrafficEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                return false;
            }
        });
    }*/