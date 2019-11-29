package com.bawei.shilujie;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bawei.shilujie.base.BaseActivity;

public class SecesActivity extends BaseActivity {

    private WebView web;

    @Override
    protected void initData() {
        final String key = getIntent().getStringExtra("key");
        web.loadUrl(key);
        web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                web.loadUrl(key);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.i("TAG", "onProgressChanged: 开始加载");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.i("TAG", "onProgressChanged: 加载完成");
            }
        });
        web.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Log.i("TAG", "onProgressChanged: 进度："+newProgress+"%");
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.i("TAG", "onProgressChanged: 标题："+title);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                Log.i("TAG", "onProgressChanged: 图标："+icon);
            }
        });
    }

    @Override
    protected void initView() {
        web = findViewById(R.id.web);
        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_seces;
    }
}
