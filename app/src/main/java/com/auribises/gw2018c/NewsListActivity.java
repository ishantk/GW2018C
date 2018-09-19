package com.auribises.gw2018c;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class NewsListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listView;
    ArrayAdapter<String> adapter;

    void initViews(){

        listView = findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);

        adapter.add("ZeeNews");     //0
        adapter.add("AajTak");      //1
        adapter.add("BBC");         //2
        adapter.add("CNN");         //3
        adapter.add("IndiaToday");  //4

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        initViews();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(this,"You Selected: "+adapter.getItem(position),Toast.LENGTH_LONG).show();

        switch (position){
            case 0:
                Intent intent = new Intent(NewsListActivity.this,NewsActivity.class);
                startActivity(intent);
                break;

            case 1:

                break;

            //...
        }
    }
}
