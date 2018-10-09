package com.auribises.gw2018c;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpperFragment extends Fragment implements MyItemClickListener{

    WebView webView;

    public UpperFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_upper, container, false);

        webView = view.findViewById(R.id.webView);
        WebViewClient client = new WebViewClient();

        webView.setWebViewClient(client);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://google.co.in");

        return view;


    }

    @Override
    public void itemClicked(int position) {
        switch (position){
            case 0:
                webView.loadUrl("https://google.co.in");
                break;

            case 1:
                webView.loadUrl("https://amazon.com");
                break;
        }
    }
}
