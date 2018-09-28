package com.auribises.gw2018c;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyLocationActivity extends AppCompatActivity
        implements View.OnClickListener, LocationListener {

    TextView txtLocation;
    Button btnFetchLocation;

    LocationManager locationManager;

    double latitude, longitude;
    StringBuffer buffer;

    ProgressDialog progressDialog;

    void initViews() {
        txtLocation = findViewById(R.id.textViewLocation);
        btnFetchLocation = findViewById(R.id.buttonFetch);

        btnFetchLocation.setOnClickListener(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching Location...");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_location);
        initViews();
    }

    @Override
    public void onClick(View v) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this,"Please Grant Location Permissions",Toast.LENGTH_LONG).show();
        }else {
            progressDialog.show();
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10, 10, this);
        }

    }

    @Override
    public void onLocationChanged(Location location) {

        latitude = location.getLatitude();
        longitude = location.getLongitude();

        //txtLocation.setText("Your Location is: \n"+latitude+" , "+longitude);

        // Reverse Geocoding
        try{

            Geocoder geocoder = new Geocoder(this);
            List<Address> addresses = geocoder.getFromLocation(latitude,longitude,5);

            if(addresses !=null && addresses.size()>0) {

                Address address = addresses.get(0); // this is the closes address
                buffer = new StringBuffer();

                for(int i=0;i<=address.getMaxAddressLineIndex();i++){
                    buffer.append(address.getAddressLine(i)+"\n");
                }

                txtLocation.setText(buffer.toString());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        progressDialog.dismiss();

        // Stop getting more location changes !!
        locationManager.removeUpdates(this);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
