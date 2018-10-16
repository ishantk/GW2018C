package com.auribises.gw2018c;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.auribises.gw2018c.model.Customer;

import java.util.ArrayList;

public class AllCustomersActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listView;            // Please Use RecyclerView
    ArrayAdapter<String> adapter; // Built In -> Please Use Customized Adapter
    ContentResolver resolver;

    ArrayList<Customer> customers;

    Customer customer;

    int pos;

    void initViews(){
        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);

        resolver = getContentResolver();
        customers = new ArrayList<>();
    }

    void fetchCustomers(){
        String[] projection = {Util.COL_ID,Util.COL_NAME,Util.COL_EMAIL,Util.COL_PHONE};
        Cursor cursor = resolver.query(Util.CUSTOMER_URI,projection,null,null,null);

        if(cursor!=null){
            while (cursor.moveToNext()){

                Customer customer = new Customer();

                customer.id = cursor.getInt(cursor.getColumnIndex(Util.COL_ID));
                customer.name = cursor.getString(cursor.getColumnIndex(Util.COL_NAME));
                customer.phone = cursor.getString(cursor.getColumnIndex(Util.COL_PHONE));
                customer.email = cursor.getString(cursor.getColumnIndex(Util.COL_EMAIL));

                customers.add(customer);
                adapter.add(customer.name+"\n"+customer.phone);
            }

            listView.setAdapter(adapter);
            listView.setOnItemClickListener(this);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_customers);
        initViews();
        fetchCustomers();
    }

    void showInfo(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Details of "+customer.name);
        builder.setMessage(customer.toString());
        builder.setPositiveButton("Done",null);
        builder.create().show();

    }

    void deleteFromDB(){
        String where = Util.COL_ID+" = "+customer.id;
        int i = resolver.delete(Util.CUSTOMER_URI,where,null);
        if(i>0){
            Toast.makeText(this,customer.name+" deleted",Toast.LENGTH_LONG).show();
            adapter.remove(customer.name+"\n"+customer.phone);
            adapter.notifyDataSetChanged(); // Refresh
        }else{
            Toast.makeText(this,customer.name+" not deleted",Toast.LENGTH_LONG).show();
        }
    }

    void askForDeletion(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete "+customer.name+" ?");
        builder.setMessage("Are You Sure to delete record?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteFromDB();
            }
        });
        builder.setNegativeButton("Cancel",null);
        builder.create().show();
    }

    void showOptions(){
        String[] options = {"View","Delete","Update","Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        showInfo();
                        break;

                    case 1:
                        askForDeletion();
                        break;

                    case 2:

                        break;

                    case 3:
                        //finish();
                        break;
                }
            }
        });
        builder.create().show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        pos = position;
        customer = customers.get(position);
        showOptions();
    }
}
