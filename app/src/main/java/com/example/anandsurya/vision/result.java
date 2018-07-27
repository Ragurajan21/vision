package com.example.anandsurya.vision;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        WebView w1=(WebView)findViewById(R.id.webView);
        w1.loadUrl("http://timesofindia.indiatimes.com/rssfeeds/2950623.cms");
    }
}
