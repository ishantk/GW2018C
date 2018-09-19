package com.auribises.gw2018c;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NewsActivity extends AppCompatActivity {

    WebView webView;

    void initViews(){

        webView = findViewById(R.id.webView);

        WebViewClient client = new WebViewClient();

        webView.setWebViewClient(client);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://zeenews.india.com/");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initViews();
    }
}
