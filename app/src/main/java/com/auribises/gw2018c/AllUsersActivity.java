package com.auribises.gw2018c;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.auribises.gw2018c.model.User;

import java.util.ArrayList;

public class AllUsersActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    //ListView listView;
    //GridView listView;

    RecyclerView recyclerView;

    ArrayList<User> users;
    UserAdapter userAdapter;

    UserRecyclerAdapter recyclerAdapter;

    void initViews(){

        recyclerView = findViewById(R.id.recyclerView);

        users = new ArrayList<>();

        User user1 = new User(R.drawable.contact,"John","john@example.com");
        User user2 = new User(R.drawable.category,"Jennie","jennie@example.com");
        User user3 = new User(R.drawable.pb,"Jim","jim@example.com");
        User user4 = new User(R.drawable.todo,"Jack","jack@example.com");
        User user5 = new User(R.drawable.music,"Joe","joe@example.com");
        User user6 = new User(R.drawable.folder,"Mike","mike@example.com");
        User user7 = new User(R.drawable.pb,"Leo","leo@example.com");

        users.add(user1); //0
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5); //4
        users.add(user6);
        users.add(user7);

        //userAdapter = new UserAdapter(this,R.layout.list_item,users);
        //listView.setAdapter(userAdapter);
        //listView.setOnItemClickListener(this);

        recyclerAdapter = new UserRecyclerAdapter(this,R.layout.list_item,users);

        //LinearLayoutManager linearLayoutManager  = new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);

        //StaggeredGridLayoutManager -> Explore

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(recyclerAdapter);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);
        initViews();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        User user = users.get(position);
        Toast.makeText(this,"You Selected: "+user.name,Toast.LENGTH_LONG).show();
    }
}
