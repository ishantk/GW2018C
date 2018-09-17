package com.auribises.gw2018c;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.auribises.gw2018c.model.User;

public class MyActivity extends AppCompatActivity implements View.OnClickListener{

    // Declare References to the Views
    EditText eTxtName, eTxtEmail;
    Button btnSubmit;

    User user;

    // User Defined Function - Any Name of your choice
    void initViews(){
        eTxtName = findViewById(R.id.editTextName); // IOC : Inversion of Control
        eTxtEmail = findViewById(R.id.editTextEmail);

        btnSubmit = findViewById(R.id.buttonSubmit);
        btnSubmit.setOnClickListener(this); // Event Handling

        // Creating Object of User
        user = new User();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setContentView will bind the Layout on Activity
        setContentView(R.layout.activity_my);

        // After Layout is set then execute initViews
        initViews();
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if(id == R.id.buttonSubmit){

            // Write the data in object
            //user.name = eTxtName.getText().toString();
            //user.email = eTxtEmail.getText().toString();

            //Toast.makeText(this,user.toString(),Toast.LENGTH_LONG).show();

            //Intent intent = new Intent(MyActivity.this,YourActivity.class);

            // 1. Add Data into Intent
            //intent.putExtra("keyName",user.name);
            //intent.putExtra("keyEmail",user.email);
            //intent.putExtra("keyAge",30); // Hard Code

            //2. Add Data into Bundle and bundle into Intent
            /*Bundle bundle = new Bundle();
            bundle.putString("keyName",user.name);
            bundle.putString("keyEmail",user.email);
            bundle.putInt("keyAge",20);

            intent.putExtra("keyBundle",bundle);*/

            //intent.putExtra("keyUser",user);

            //startActivity(intent);

            Intent intent = new Intent(MyActivity.this,YourActivity.class);
            startActivityForResult(intent,101);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 101 && resultCode == 201){
            user = (User)data.getSerializableExtra("keyUser");
            eTxtName.setText(user.name);
            eTxtEmail.setText(user.email);
        }
    }
}
