package com.auribises.gw2018c;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityOne extends AppCompatActivity implements View.OnClickListener{

    // Declare References to the Views
    EditText eTxtName, eTxtPhone;
    Button btnSubmit;

    // User Defined Method
    void initViews(){
        // Ensure ID's match in XML(Layout)
        eTxtName = findViewById(R.id.editTextName);
        eTxtPhone = findViewById(R.id.editTextPhone);
        btnSubmit = findViewById(R.id.button);

        btnSubmit.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView sets the Layout on Activity
        setContentView(R.layout.activity_one);

        // Should be called after setContentView
        initViews();
    }

    @Override
    public void onClick(View v) {

        String name = eTxtName.getText().toString();
        String phone = eTxtPhone.getText().toString();

        Toast.makeText(this,"You Entered: "+name+" - "+phone,Toast.LENGTH_LONG).show();

        // Navigating from One Activity to Other
        // Explicit Intent:
        //Intent intent = new Intent(ActivityOne.this, ActivityTwo.class);

        // Implicit Intent:
        Intent intent = new Intent("com.auribises.gw2018c.activitytwo");
        intent.putExtra("keyName",name);
        intent.putExtra("keyPhone",phone);
        intent.putExtra("keyAge",30);
        startActivity(intent);

    }
}
