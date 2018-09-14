package com.auribises.gw2018c;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/*
    Life Cycle of Activity
	CallBacks : They will be executed automatically
			    and we shall override them for our usage
	1. onCreate
	onRestart -> Executed when activity is restarted
	2. onStart
	3. onResume
	4. onPause
	5. onStop
	6. onDestroy



	Components:
	-----------
	Activity
	Service
	BroadcastReceiver
	ContentProvider


	Use Case:
		1. Launch a new Activity
		Object of Activity will be created | Inversion of Control
			onCreate

		Object of Activity will be pushed into stack | BackStack or Task
			onStart

		Object of Activity becomes visible to User and hence user can interact with it
			onResume

		2. Press the back key
		Object of Activity will be destroyed i.e. removed from memory
			onDestroy

		3. 	Launch a new Activity2 from Activity1
		A1 : create > start > resume
		A2 : create > start > resume
		A1 : pause > stop

			If activity is fully covered by another activity is is in onStop state
			But from onResume it will be firstly paused and then stopped

		4. Activity1 launches UI which covers Activity1 partially
			onPause
 */

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

        Log.d("MainActivity","==onCreate==");
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

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity","==onStart==");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity","==onResume==");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity","==onPause==");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity","==onStop==");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity","==onDestroy==");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MainActivity","==onRestart==");
    }



    // This method will create menu for us !
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Explicit Way:
        // Follow it: Keep itemId unique !!
        /*menu.add(1,101,1,"All Songs");
        menu.add(1,201,1,"Favourites");
        menu.add(1,301,1,"Artists");
        menu.add(1,401,1,"Recently Played");
        menu.add(2,501,1,"Bollywood");*/

        // Implicit Way
        getMenuInflater().inflate(R.menu.menu_main,menu);

        return super.onCreateOptionsMenu(menu);
    }

    // This method will handle menu item clicks !
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        int gid = item.getGroupId();

        switch (id){
            case 101:
                Toast.makeText(this,"You Selected All Songs",Toast.LENGTH_LONG).show();
                break;

            case 201:

                break;

            case 301:

                break;


            case 401:

                break;

            case 501:

                break;

            case R.id.allSongs:
                Toast.makeText(this,"You Selected All Songs",Toast.LENGTH_LONG).show();
                break;

            case R.id.favourites:

                break;

            case R.id.artists:

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
