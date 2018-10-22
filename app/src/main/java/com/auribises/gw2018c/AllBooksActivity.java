package com.auribises.gw2018c;

import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.auribises.gw2018c.model.Book;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> adapter;

    ProgressDialog progressDialog;
    StringBuffer stringBuffer;

    ArrayList<Book> books;

    RequestQueue requestQueue;
    StringRequest stringRequest;


    void initViews(){
        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");

        requestQueue = Volley.newRequestQueue(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);
        initViews();

        if(isInternetConnected()){
            //BookFetchTask task = new BookFetchTask();
            //task.execute();
            fetchBooks();
        }

    }

    void fetchBooks(){

        progressDialog.show();

        String url = "http://www.json-generator.com/api/json/get/chQLxhBjaW?indent=2";

        stringRequest = new StringRequest(Request.Method.GET, url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();
                        stringBuffer = new StringBuffer();
                        stringBuffer.append(response);
                        parseJSONResponse();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue.add(stringRequest);
    }

    boolean isInternetConnected(){
        // ConnectivityManager needs ACCESS_NETWORK_STATE Permission
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    // Android Background Thread -> AsyncTask

    class BookFetchTask extends AsyncTask{

        @Override
        protected void onPreExecute() {
            progressDialog.show();
        }

        // we cannot write code in doInBackground method which will update UI: eg we cannot show Toast here
        @Override
        protected Object doInBackground(Object[] objects) {

            // We will send Request to the Server and Get Back Response
            try {
                //1. Create URL
                URL url = new URL("http://www.json-generator.com/api/json/get/chQLxhBjaW?indent=2");

                //2. Send Request
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                //3. Get Response and Read it into a StringBuffer
                InputStream inputStream = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(inputStream);
                BufferedReader buffer = new BufferedReader(reader);

                stringBuffer = new StringBuffer();

                String line = "";
                while((line = buffer.readLine()) != null){
                    stringBuffer.append(line+"\n");
                }


            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {

            progressDialog.dismiss();
            Toast.makeText(AllBooksActivity.this,"FROM SERVER: "+stringBuffer.toString(),Toast.LENGTH_LONG).show();

            parseJSONResponse();
        }
    }

    void parseJSONResponse(){
        try {

            JSONObject jsonObject = new JSONObject(stringBuffer.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("bookstore");

            books = new ArrayList<>();

            for(int i=0;i<jsonArray.length();i++){
                Book book = new Book();
                JSONObject jObj = jsonArray.getJSONObject(i);

                book.price = jObj.getString("price");
                book.name = jObj.getString("name");
                book.author = jObj.getString("author");

                books.add(book);
                adapter.add(book.name+"\n"+book.price+"\n"+book.author);
            }

            listView.setAdapter(adapter);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
