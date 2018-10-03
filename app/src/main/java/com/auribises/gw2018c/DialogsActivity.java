package com.auribises.gw2018c;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogsActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.textViewData)
    TextView txtData;

    @BindView(R.id.buttonShow)
    Button btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs);

        ButterKnife.bind(this);
        btnShow.setOnClickListener(this);
    }

    // User Defined - can be any name
    void showAlertDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("This is Title");
        builder.setMessage("This is Message");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Date date = new Date();
                txtData.setText(date.toString());
            }
        });

        // if you do not want the dialog should be dismissed on back press
        builder.setCancelable(false);

        builder.setNegativeButton("Cancel",null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    void showAlertDialogWithOptions(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String[] items = {"Hindi","English","Punjab","Cancel"};
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:

                        break;

                    case 1:

                        break;

                    case 2:

                        break;

                    case 3:
                        finish();
                        break;
                }
            }
        });

        // if you do not want the dialog should be dismissed on back press
        builder.setCancelable(false);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    Dialog dialog = null;
    Button btnCancel;

    void showCustomDialog(){
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_dialog);

        btnCancel = dialog.findViewById(R.id.button15);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dialog.show();

        //dialog.setCancelable(false);
        //dialog.dismiss();
    }

    void showProgressDialog(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");
        progressDialog.show();

        //progressDialog.setCancelable(false);
        //progressDialog.dismiss();
    }

    DatePickerDialog datePickerDialog;
    DatePickerDialog.OnDateSetListener onDateSetListener;

    void showDatePickerDialog(){

        Calendar calendar = Calendar.getInstance();
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        int mm = calendar.get(Calendar.MONTH);
        int yy = calendar.get(Calendar.YEAR);

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtData.setText(dayOfMonth+"/"+(month+1)+"/"+year);
            }
        };

        datePickerDialog = new DatePickerDialog(this,onDateSetListener,yy,mm,dd);
        datePickerDialog.show();
    }

    TimePickerDialog timePickerDialog;
    TimePickerDialog.OnTimeSetListener onTimeSetListener;

    void showTimePickerDialog(){

        Calendar calendar = Calendar.getInstance();
        int hh = calendar.get(Calendar.HOUR_OF_DAY);
        int mm = calendar.get(Calendar.MINUTE);

       onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
           @Override
           public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                txtData.setText(hourOfDay+":"+minute);
           }
       };

       timePickerDialog = new TimePickerDialog(this,onTimeSetListener,hh,mm,true);
       timePickerDialog.show();
    }

    @Override
    public void onClick(View v) {
        //showAlertDialog();
        //showAlertDialogWithOptions();
        //showCustomDialog();
        //showProgressDialog();
        //showDatePickerDialog();
        showTimePickerDialog();
    }
}
