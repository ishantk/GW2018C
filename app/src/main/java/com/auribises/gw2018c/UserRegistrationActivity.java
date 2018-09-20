package com.auribises.gw2018c;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.auribises.gw2018c.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserRegistrationActivity extends AppCompatActivity implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener{


    @BindView(R.id.editTextName)
    EditText eTxtName;

    @BindView(R.id.editTextEmail)
    EditText eTxtEmail;

    @BindView(R.id.editTextPassword)
    EditText eTxtPassword;

    @BindView(R.id.radioButtonMale)
    RadioButton rbMale;

    @BindView(R.id.radioButtonFemale)
    RadioButton rbFemale;

    @BindView(R.id.spinnerCity)
    Spinner spinnerCity;

    @BindView(R.id.buttonRegister)
    Button btnRegister;

    @BindView(R.id.ratingBar)
    RatingBar ratingBar;

    User user;

    ArrayAdapter<String> adapter;

    /*void initViews(){
        eTxtName = findViewById(R.id.editTextName);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        ButterKnife.bind(this);

        btnRegister.setOnClickListener(this);

        rbMale.setOnCheckedChangeListener(this);
        rbFemale.setOnCheckedChangeListener(this);

        // Creating User Object with ref variable user
        user = new User();

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item);
        adapter.add("--Select City--");    //0
        adapter.add("Ludhiana");           //1
        adapter.add("Chandigarh");         //2
        adapter.add("Delhi");
        adapter.add("Pune");
        adapter.add("Bengaluru");

        spinnerCity.setAdapter(adapter);

        // Event Handling using Anonymous Class
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    user.city = adapter.getItem(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Event Handling using Anonymous Class
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(UserRegistrationActivity.this,"You Gave "+rating+" stars",Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonRegister){

            user.name = eTxtName.getText().toString();
            user.email = eTxtEmail.getText().toString();
            user.password = eTxtPassword.getText().toString();


            Toast.makeText(this,"User Details:"+user,Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();

        if(id == R.id.radioButtonMale){
            user.gender = "Male";
        }else{
            user.gender = "Female";
        }
    }

}
