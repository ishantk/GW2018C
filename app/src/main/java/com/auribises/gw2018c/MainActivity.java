package com.auribises.gw2018c;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Declare References to the View !!
    EditText eTxtA, eTxtB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // View Initialization
        eTxtA = findViewById(R.id.editTextA);
        eTxtB = findViewById(R.id.editTextB);
    }

    // User Defined Function : Should be public and take View as input
    public void clickHandler(View view){
        String numA = eTxtA.getText().toString();
        String numB = eTxtB.getText().toString();

        int a = Integer.parseInt(numA);
        int b = Integer.parseInt(numB);

        int c = a+b;

        Toast.makeText(this,"c is: "+c,Toast.LENGTH_LONG).show();
    }
}
