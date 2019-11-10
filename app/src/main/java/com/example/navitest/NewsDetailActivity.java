package com.example.navitest;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

public class NewsDetailActivity extends AppCompatActivity {
    private WebView displayWebview;
    private TextView linkTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        String link = getIntent().getExtras().getString("link");

//        linkTextView = findViewById(R.id.textLink);
//        linkTextView.setText(link);


//        WebView myWebView = (WebView) findViewById(R.id.webItem);
//        myWebView.loadUrl(link);

        initWebView(link);

    }


    private void initWebView(String url){
        WebView webView = findViewById(R.id.webItem);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.nav_agriculture:
//                NavUtils.navigateUpFromSameTask(this);
//                return true;
//
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//
//    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
