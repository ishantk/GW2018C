package com.auribises.gw2018c;

import android.content.ContentResolver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.auribises.gw2018c.model.Customer;

public class AddCustomerActivity extends AppCompatActivity implements View.OnClickListener{


    EditText eTxtName, eTxtPhone, eTxtEmail;
    Button btnAdd;

    Customer customer;

    ContentResolver resolver;

    void initViews(){
        eTxtName = findViewById(R.id.editTextName);
        eTxtPhone = findViewById(R.id.editTextPhone);
        eTxtEmail = findViewById(R.id.editTextEmail);

        btnAdd = findViewById(R.id.buttonAdd);
        btnAdd.setOnClickListener(this);

        customer = new Customer();
        resolver = getContentResolver();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        initViews();
    }

    @Override
    public void onClick(View v) {
        customer.name = eTxtName.getText().toString();
        customer.phone = eTxtPhone.getText().toString();
        customer.email = eTxtEmail.getText().toString();

    }
}
