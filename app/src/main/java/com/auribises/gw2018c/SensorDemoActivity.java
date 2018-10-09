package com.auribises.gw2018c;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class SensorDemoActivity extends AppCompatActivity implements SensorEventListener{

    TextView txtData;
    Button btnActivate;

    SensorManager sensorManager;
    Sensor sensor;

    TextToSpeech textToSpeech;

    void initViews(){
        txtData = findViewById(R.id.textViewData);
        btnActivate = findViewById(R.id.buttonActivate);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.ERROR){
                    Toast.makeText(SensorDemoActivity.this,"Please Enable TTS Service in Settings",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_demo);
        initViews();
    }

    public void clickHandler(View view){
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        // Proximity Sensor

        float[] value = event.values;

        float x = value[0];
        float y = value[1];
        float z = value[2];

        txtData.setText("Sensor Data\n"+x+" : "+y+" : "+z);

        float cal = ((x*x)+(y*y)+(z*z))/(SensorManager.GRAVITY_EARTH*SensorManager.GRAVITY_EARTH);
        if(cal>3){
            txtData.setText("Device Shaken !!");
            sensorManager.unregisterListener(this);
            //Request -> LocationManager to get Location

            Date date = new Date();
            String text = "Device Shaken at "+date.toString();
            textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null,null);
        }

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("+91 99155 71177",null,"Hello, This is text message",null,null);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this);
    }
}
