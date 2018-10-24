package com.auribises.gw2018c;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

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

    // Access Google Web Services : LocationServices
    GoogleApiClient apiClient;
    GoogleApiClient.Builder builder;

    GoogleApiClient.ConnectionCallbacks connectionCallbacks = new GoogleApiClient.ConnectionCallbacks() {
        @Override
        public void onConnected(@Nullable Bundle bundle) {

            /*if (ActivityCompat.checkSelfPermission(MyLocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MyLocationActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MyLocationActivity.this, "Please Grant Location Permissions", Toast.LENGTH_LONG).show();
            } else {
                Location location = LocationServices.FusedLocationApi.getLastLocation(apiClient);
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                txtLocation.setText(latitude + " : " + longitude);
            }*/

            fetchLocationUpdates();
        }

        @Override
        public void onConnectionSuspended(int i) {

        }
    };

    GoogleApiClient.OnConnectionFailedListener connectionFailedListener = new GoogleApiClient.OnConnectionFailedListener() {
        @Override
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        }
    };

    com.google.android.gms.location.LocationListener locationListener = new com.google.android.gms.location.LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            txtLocation.setText(latitude + " : " + longitude);

            // Reverse Geocoding
        }
    };

    void initGoogleApiClient() {

        builder = new GoogleApiClient.Builder(this);
        builder.addConnectionCallbacks(connectionCallbacks);
        builder.addOnConnectionFailedListener(connectionFailedListener);
        builder.addApi(LocationServices.API);

        apiClient = builder.build();

    }

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
        initGoogleApiClient();
    }

    void fetchLocationUpdates() {

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000); // 10 secs
        locationRequest.setFastestInterval(5000); // 5secs
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this,"Please Grant Location Permissions",Toast.LENGTH_LONG).show();
        }else {
            LocationServices.FusedLocationApi.requestLocationUpdates(apiClient, locationRequest, locationListener);
        }


    }

    @Override
    public void onClick(View v) {

        /*if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this,"Please Grant Location Permissions",Toast.LENGTH_LONG).show();
        }else {
            progressDialog.show();
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10, 10, this);
        }*/

        // Request to create a connection with Google Location Services
        apiClient.connect();

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
