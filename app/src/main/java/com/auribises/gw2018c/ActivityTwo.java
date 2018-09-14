package com.auribises.gw2018c;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityTwo extends AppCompatActivity {

    // Declare References to the Views
    TextView txtData;

    void initViews(){
        txtData = findViewById(R.id.textViewData);

        Intent rcv = getIntent();

        String name = rcv.getStringExtra("keyName");
        String phone = rcv.getStringExtra("keyPhone");
        int age = rcv.getIntExtra("keyAge",0);

        txtData.setText(name+"\n"+phone+"\n"+age);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Binding the layout with Activity
        setContentView(R.layout.activity_two);

        initViews();
    }
}
