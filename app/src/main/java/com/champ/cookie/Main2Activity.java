package com.champ.cookie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Main2Activity extends AppCompatActivity {
    WebView w;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        w=(WebView)findViewById(R.id.w1);
        w.setWebViewClient(new WebViewClient());
        w.getSettings().setJavaScriptEnabled(true);
        w.getSettings().setAppCacheEnabled(true);
        w.getSettings().setLoadWithOverviewMode(true);
        w.loadUrl("https://mbasic.facebook.com");

    }
}
