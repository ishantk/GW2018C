package com.auribises.gw2018c;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class FilesDemoActivity extends AppCompatActivity implements View.OnClickListener{

    EditText eTxtData;
    Button btnSubmit;

    String data;

    SharedPreferences sharedPreferences; // Its an XML File created in App SBox where data is stored as Key value Pair
    SharedPreferences.Editor editor; // To Write Data in SharedPreferences

    void initViews(){
        eTxtData = findViewById(R.id.editTextData);
        btnSubmit = findViewById(R.id.button);
        btnSubmit.setOnClickListener(this);
                                      // do not mention extension .xml here
        sharedPreferences = getSharedPreferences("myPrefs",MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files_demo);
        initViews();
    }

    void saveDataInInternalFile(){
        try {
            FileOutputStream outputStream = openFileOutput("data.txt", MODE_PRIVATE);
            outputStream.write(data.getBytes());
            outputStream.close();
            Toast.makeText(this,"Data Saved in Internal File",Toast.LENGTH_LONG).show();
            eTxtData.setText("");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void saveDataInExternalFile(){
        try {



            // path is for SD-Card
            String path = Environment.getExternalStorageDirectory().getPath()+"/data.txt";

            // We can created directory i.e. folder in External Storage using Java File API
            // But cannot be done in Internal File Storage i.e. Applciation Sandbox
            //String path1 = Environment.getExternalStorageDirectory().getPath()+"/MyCustomers";
            //File file = new File(path1);
            //file.mkdir();

            FileOutputStream outputStream = new FileOutputStream(path);
            outputStream.write(data.getBytes());
            outputStream.close();
            Toast.makeText(this,"Data Saved in External File",Toast.LENGTH_LONG).show();
            eTxtData.setText("");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    void readDataFromExternalFile(){
        try {

            // path is for SD-Card
            String path = Environment.getExternalStorageDirectory().getPath()+"/data.txt";
            FileInputStream inputStream = new FileInputStream(path);
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader buffer = new BufferedReader(reader);

            String data = buffer.readLine();

            eTxtData.setText(data);

            buffer.close();
            reader.close();
            inputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void readDataFromInternalFile(){
        try {

            FileInputStream inputStream = openFileInput("data.txt");
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader buffer = new BufferedReader(reader);

            String data = buffer.readLine();

            eTxtData.setText(data);

            buffer.close();
            reader.close();
            inputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    void saveDataInPreferences(){

        editor.putString("keyData",data);
        editor.putInt("keyRating",5);

        //editor.commit(); // To Save in UI Thread
        editor.apply();    // To Save in background Thread

        eTxtData.setText("");
        Toast.makeText(this,"Data Saved in Shared Preferences",Toast.LENGTH_LONG).show();
    }

    void readDataFromPreferences(){

        String data = sharedPreferences.getString("keyData","NA");
        int rating = sharedPreferences.getInt("keyRating",0);

        eTxtData.setText(data+" : "+rating);
        Toast.makeText(this,"Data Saved in Shared Preferences",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {

        //data = eTxtData.getText().toString();
        //saveDataInPreferences();
        readDataFromPreferences();

        //saveDataInInternalFile();

        //readDataFromInternalFile();

        //saveDataInExternalFile();

        //readDataFromExternalFile();
    }
}
