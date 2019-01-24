package com.zxn.webview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxn on 2018-8-10 15:58:29.
 * 带进度条的wevview
 */
public class WebViewActivity extends AppCompatActivity {
//    private static final String URL_NEWS_FAST = "http://info.superwie.com/";
    private static final String URL_NEWS_FAST = "http://m.3500.com/userinfo/login/340.html";
    @BindView(R.id.wv_loading)
    LoadWebView wvLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        wvLoading.loadUrl(URL_NEWS_FAST);
    }

    public static void jumpTo(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, WebViewActivity.class);
        context.startActivity(intent);
    }
}
