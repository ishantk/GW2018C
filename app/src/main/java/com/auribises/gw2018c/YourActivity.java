package com.auribises.gw2018c;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.auribises.gw2018c.model.User;

public class YourActivity extends AppCompatActivity implements View.OnClickListener{

    // Declare References to the Views
    EditText eTxtName, eTxtEmail;
    Button btnBack;

    User user;

    // User Defined Function - Any Name of your choice
    void initViews(){
        eTxtName = findViewById(R.id.editTextName); // IOC : Inversion of Control
        eTxtEmail = findViewById(R.id.editTextEmail);

        btnBack = findViewById(R.id.buttonBack);

        btnBack.setOnClickListener(this); // Event Handling

        // Creating Object of User
        user = new User();

        // Get the Intent from Activity which started this Activity
        Intent rcv = getIntent();


        /*user.name = rcv.getStringExtra("keyName");
        user.email = rcv.getStringExtra("keyEmail");
        int age = rcv.getIntExtra("keyAge",0);*/

        /*Bundle rcvBun = rcv.getBundleExtra("keyBundle");
        user.name = rcvBun.getString("keyName");
        user.email = rcvBun.getString("keyEmail");
        int age = rcvBun.getInt("keyAge",0);*/

        //user = (User)rcv.getSerializableExtra("keyUser");

        //eTxtName.setText(user.name);//+" - "+age);
        //eTxtEmail.setText(user.email);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your);

        initViews();
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if(id == R.id.buttonBack){

            user.name = eTxtName.getText().toString();
            user.email = eTxtEmail.getText().toString();

            Intent data = new Intent();
            data.putExtra("keyUser",user);

            setResult(201,data);

            finish();
        }

    }
}
