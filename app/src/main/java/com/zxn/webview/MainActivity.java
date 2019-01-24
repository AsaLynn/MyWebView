package com.zxn.webview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zxn on 2018-8-10 15:52:18.
 * webview的使用介绍.
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bt_baidu)
    Button btBaidu;
    @BindView(R.id.bt_upload_photo)
    Button btUploadPhoto;
    @BindView(R.id.bt_movie)
    Button btMovie;
    @BindView(R.id.bt_movie_full)
    Button btMovieFull;
    @BindView(R.id.bt_call)
    Button btCall;
    @BindView(R.id.bt_java_js)
    Button btJavaJs;
    @BindView(R.id.bt_deeplink)
    Button btDeeplink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.bt_baidu, R.id.bt_upload_photo, R.id.bt_movie, R.id.bt_movie_full, R.id.bt_call, R.id.bt_java_js, R.id.bt_deeplink, R.id.bt_game})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_game:

                break;
            case R.id.bt_baidu:
                WebViewActivity.jumpTo(this);
                break;
            case R.id.bt_upload_photo:
                break;
            case R.id.bt_movie:
                break;
            case R.id.bt_movie_full:
                break;
            case R.id.bt_call:
                break;
            case R.id.bt_java_js:
                break;
            case R.id.bt_deeplink:
                break;
        }
    }
}
