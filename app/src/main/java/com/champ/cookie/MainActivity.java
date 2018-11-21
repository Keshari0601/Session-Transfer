package com.champ.cookie;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.lang.UCharacterEnums;
import android.nfc.Tag;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    WebView w1;
    Button b,b2,b3,b4;
    String s,u,c,sub;
    EditText e,e1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e=(EditText)findViewById(R.id.editText);

        w1 = (WebView) findViewById(R.id.webview1);
        w1.getSettings().setJavaScriptEnabled(true);
        w1.setWebViewClient(new WebViewClient());
        w1.loadUrl("https://mbasic.facebook.com");
        b=(Button)findViewById(R.id.button) ;
        e1=(EditText)findViewById(R.id.editText2);



        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                s = CookieManager.getInstance().getCookie(w1.getUrl().toString());
                e.setText(s);
                e1.setText(w1.getUrl().toString());


                Toast.makeText(MainActivity.this, w1.getUrl().toString()+"   "+s, Toast.LENGTH_SHORT).show();

            }
        });
        b2=(Button)findViewById(R.id.button2) ;
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                u=w1.getUrl().toString();

                android.webkit.CookieManager cookieManager = CookieManager.getInstance();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    cookieManager.removeAllCookies(new ValueCallback<Boolean>() {
                        // a callback which is executed when the cookies have been removed
                        @Override
                        public void onReceiveValue(Boolean aBoolean) {
                            Log.d("Tag", "Cookie removed: " + aBoolean);
                        }
                    });
                }
                else cookieManager.removeAllCookie();
            }
        });
        b3=(Button)findViewById(R.id.button3) ;
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                u=e1.getText().toString();
                c=e.getText().toString();

                CookieSyncManager.createInstance(getApplicationContext());
                CookieSyncManager.getInstance().startSync();
                CookieManager.getInstance().setAcceptCookie(true);
                while(c.length()!=0) {
                    CookieManager.getInstance().setCookie(u, c);
                    if(c.indexOf(";")>-1){
                    c=c.substring(c.indexOf(";")+2);}
                    else{
                        c="";
                    }

                }
                w1.loadUrl(u);
            }
        });
        b4=(Button)findViewById(R.id.button4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                w1.loadUrl(u);

            }
        });




      

    }
}
