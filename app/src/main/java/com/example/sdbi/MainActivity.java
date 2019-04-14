package com.example.sdbi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.*;

public class MainActivity extends Activity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //************在此填入信息***************//
        final String user = "17852531038";
        final String pwd = "qq1669401090";
        //*************************************//
        String url = "http://login.gwifi.com.cn/cmps/admin.php/api/login/";
        WebView mWebView = findViewById(R.id.mainWebView);
        mWebView.loadUrl(url);
        WebSettings settings;
        settings = mWebView.getSettings();
        settings.setSupportZoom(true);                              //支持缩放
        settings.setBuiltInZoomControls(true);                      //设置出现缩放工具
        settings.setJavaScriptEnabled(true);                        //允许JS
        mWebView.getSettings().setUserAgentString("Chrome");        //设置UA为谷歌浏览器

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:(function(){" +
                        "var user = document.getElementById('first_name');" +
                        "var pwd = document.getElementById('first_password'); " +
                        "var _button = document.getElementById('first_button'); " +
                        " user.value = ('"+user+"');"+
                        " pwd.value= ('"+pwd+"');"+
                        " _button.click(); "+
                        "})()");
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.contains("new")){
                    view.loadUrl("http://172.16.1.2/");
                    //view.loadUrl("file:///android_asset/index.html");
                    return true;
                } else {
                    return false;
                }
            }
        });
    }
}