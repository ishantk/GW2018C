package com.auribises.gw2018c;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.auribises.gw2018c.model.Customer;

public class AddCustomerActivity extends AppCompatActivity implements View.OnClickListener{


    EditText eTxtName, eTxtPhone, eTxtEmail;
    Button btnAdd;

    Customer customer;

    ContentResolver resolver;

    boolean updateMode;

    void initViews(){
        eTxtName = findViewById(R.id.editTextName);
        eTxtPhone = findViewById(R.id.editTextPhone);
        eTxtEmail = findViewById(R.id.editTextEmail);

        btnAdd = findViewById(R.id.buttonAdd);
        btnAdd.setOnClickListener(this);

        customer = new Customer();
        resolver = getContentResolver();

        Intent rcv = getIntent();
        updateMode = rcv.hasExtra("keyCustomer");

        if(updateMode){
            customer = (Customer)rcv.getSerializableExtra("keyCustomer");
            eTxtName.setText(customer.name);
            eTxtPhone.setText(customer.phone);
            eTxtEmail.setText(customer.email);
            btnAdd.setText("Update Customer");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        initViews();
    }

    boolean validateFields(){
        boolean flag = true;

        if(customer.name.isEmpty())
            flag = false;

        if(customer.email.isEmpty())
            flag = false;

        if(customer.phone.isEmpty())
            flag = false;

        return flag;
    }

    void addCustomerToDB(){

        ContentValues values = new ContentValues();
        values.put(Util.COL_NAME,customer.name);
        values.put(Util.COL_PHONE,customer.phone);
        values.put(Util.COL_EMAIL,customer.email);

        if(validateFields()) {

            if(updateMode){

                String where = Util.COL_ID+" = "+customer.id;
                int i = resolver.update(Util.CUSTOMER_URI,values,where,null);

                if(i>0){
                    Toast.makeText(this, customer.name + " updated in Table", Toast.LENGTH_LONG).show();

                    Intent data = new Intent();
                    data.putExtra("keyUpdatedCustomer",customer);
                    setResult(201,data);

                    finish();
                }

            }else {
                Uri uri = resolver.insert(Util.CUSTOMER_URI, values);
                Toast.makeText(this, customer.name + " added in Table at id " + uri.getLastPathSegment(), Toast.LENGTH_LONG).show();
                clearFields();
            }
        }else {
            Toast.makeText(this, "Enter Details First", Toast.LENGTH_LONG).show();
        }
    }

    void clearFields(){
        eTxtName.setText("");
        eTxtEmail.setText("");
        eTxtPhone.setText("");
    }

    @Override
    public void onClick(View v) {
        customer.name = eTxtName.getText().toString();
        customer.phone = eTxtPhone.getText().toString();
        customer.email = eTxtEmail.getText().toString();

        addCustomerToDB();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(1,101,0,"All Customers");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == 101){
            Intent intent = new Intent(AddCustomerActivity.this,AllCustomersActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
