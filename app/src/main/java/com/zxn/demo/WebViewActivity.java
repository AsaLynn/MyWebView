package com.zxn.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by zxn on 2018-8-10 15:58:29.
 * 带进度条的wevview
 */
public class WebViewActivity extends AppCompatActivity {
    //    private static final String URL_NEWS_FAST = "http://info.superwie.com/";
    private static final String URL_NEWS_FAST = "http://m.3500.com/userinfo/login/340.html";
//    @BindView(R.id.wv_loading)
//    LoadWebView wvLoading;

    public static void jumpTo(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, WebViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_web_view);
        setContentView(R.layout.activity_web_frag);
        ButterKnife.bind(this);
//        wvLoading.loadUrl(URL_NEWS_FAST);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_loading, WebFragment.newInstance()).commitAllowingStateLoss();
    }
}
