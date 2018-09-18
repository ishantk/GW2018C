package com.auribises.gw2018c;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.auribises.gw2018c.model.User;

import JavaAPI.AvsInfo;
import JavaAPI.CvdInfo;
import JavaAPI.HttpsPostRequest;
import JavaAPI.Purchase;
import JavaAPI.Receipt;

public class MyActivity extends AppCompatActivity implements View.OnClickListener{

    // Declare References to the Views
    EditText eTxtName, eTxtEmail;
    Button btnSubmit;

    User user;

    // User Defined Function - Any Name of your choice
    void initViews(){
        eTxtName = findViewById(R.id.editTextName); // IOC : Inversion of Control
        eTxtEmail = findViewById(R.id.editTextEmail);

        btnSubmit = findViewById(R.id.buttonSubmit);
        btnSubmit.setOnClickListener(this); // Event Handling

        // Creating Object of User
        user = new User();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setContentView will bind the Layout on Activity
        setContentView(R.layout.activity_my);

        // After Layout is set then execute initViews
        initViews();
    }

    void checkMoneris(){
        String store_id = "store5";
        String api_token = "yesguy";
        java.util.Date createDate = new java.util.Date();
        String order_id = "Test"+createDate.getTime();
        String amount = "10.02";
        String pan = "4242424242424242";
        String expiry_date = "1901"; //YYMM format
        String crypt = "7";
        String processing_country_code = "CA";
        boolean status_check = false;

        AvsInfo avsCheck = new AvsInfo();
        avsCheck.setAvsStreetNumber("212");
        avsCheck.setAvsStreetName("Payton Street");
        avsCheck.setAvsZipCode("M1M1M1");
        avsCheck.setAvsEmail("test@host.com");
        avsCheck.setAvsHostname("hostname");
        avsCheck.setAvsBrowser("Mozilla");
        avsCheck.setAvsShiptoCountry("CAN");
        avsCheck.setAvsShipMethod("G");
        avsCheck.setAvsMerchProdSku("123456");
        avsCheck.setAvsCustIp("192.168.0.1");
        avsCheck.setAvsCustPhone("5556667777");

        CvdInfo cvdCheck = new CvdInfo();
        cvdCheck.setCvdIndicator("1");
        cvdCheck.setCvdValue("099");

        Purchase purchase = new Purchase();
        purchase.setOrderId(order_id);
        purchase.setAmount(amount);
        purchase.setPan(pan);
        purchase.setExpdate(expiry_date);
        purchase.setCryptType(crypt);
        purchase.setAvsInfo(avsCheck);
        purchase.setCvdInfo(cvdCheck);

        HttpsPostRequest mpgReq = new HttpsPostRequest();
        mpgReq.setProcCountryCode(processing_country_code);
        mpgReq.setTestMode(true); //false or comment out this line for production transactions
        mpgReq.setStoreId(store_id);
        mpgReq.setApiToken(api_token);
        mpgReq.setTransaction(purchase);
        mpgReq.setStatusCheck(status_check);
        mpgReq.send();

        try
        {
            Receipt receipt = mpgReq.getReceipt();

            System.out.println("CardType = " + receipt.getCardType());
            System.out.println("TransAmount = " + receipt.getTransAmount());
            System.out.println("TxnNumber = " + receipt.getTxnNumber());
            System.out.println("ReceiptId = " + receipt.getReceiptId());
            System.out.println("TransType = " + receipt.getTransType());
            System.out.println("ReferenceNum = " + receipt.getReferenceNum());
            System.out.println("ResponseCode = " + receipt.getResponseCode());
            System.out.println("ISO = " + receipt.getISO());
            System.out.println("BankTotals = " + receipt.getBankTotals());
            System.out.println("Message = " + receipt.getMessage());
            System.out.println("AuthCode = " + receipt.getAuthCode());
            System.out.println("Complete = " + receipt.getComplete());
            System.out.println("TransDate = " + receipt.getTransDate());
            System.out.println("TransTime = " + receipt.getTransTime());
            System.out.println("Ticket = " + receipt.getTicket());
            System.out.println("TimedOut = " + receipt.getTimedOut());
            System.out.println("Avs Response = " + receipt.getAvsResultCode());
            System.out.println("Cvd Response = " + receipt.getCvdResultCode());
            System.out.println("ITD Response = " + receipt.getITDResponse());
            System.out.println("IsVisaDebit = " + receipt.getIsVisaDebit());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if(id == R.id.buttonSubmit){

            // Write the data in object
            //user.name = eTxtName.getText().toString();
            //user.email = eTxtEmail.getText().toString();

            //Toast.makeText(this,user.toString(),Toast.LENGTH_LONG).show();

            //Intent intent = new Intent(MyActivity.this,YourActivity.class);

            // 1. Add Data into Intent
            //intent.putExtra("keyName",user.name);
            //intent.putExtra("keyEmail",user.email);
            //intent.putExtra("keyAge",30); // Hard Code

            //2. Add Data into Bundle and bundle into Intent
            /*Bundle bundle = new Bundle();
            bundle.putString("keyName",user.name);
            bundle.putString("keyEmail",user.email);
            bundle.putInt("keyAge",20);

            intent.putExtra("keyBundle",bundle);*/

            //intent.putExtra("keyUser",user);

            //startActivity(intent);

            //Intent intent = new Intent(MyActivity.this,YourActivity.class);
            //startActivityForResult(intent,101);
            checkMoneris();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 101 && resultCode == 201){
            user = (User)data.getSerializableExtra("keyUser");
            eTxtName.setText(user.name);
            eTxtEmail.setText(user.email);
        }
    }
}
