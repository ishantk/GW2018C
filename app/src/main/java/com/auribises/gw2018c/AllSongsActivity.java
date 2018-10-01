package com.auribises.gw2018c;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;

public class AllSongsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listView;
    ArrayAdapter<String> adapter;

    void initViews(){
        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);

        // To read files from SD Card
        String path = Environment.getExternalStorageDirectory().getPath()+"/Music";

        Log.i("AllSongsActivity","Path is: "+path);

        try{

            File file = new File(path);
            String[] fileNames = file.list();

            /*File[] files = file.listFiles();
            for(File f : files){
                if(f.isDirectory()){

                }else{

                }
            }*/

            for(String name : fileNames){
                if(name.endsWith(".mp3")) {
                    adapter.add(name);
                }
            }

            listView.setAdapter(adapter);
            listView.setOnItemClickListener(this);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_songs);
        initViews();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String songName = adapter.getItem(position);

        Intent intent = new Intent(AllSongsActivity.this,PlayActivity.class);
        intent.putExtra("keySong",songName);
        startActivity(intent);
    }
}
