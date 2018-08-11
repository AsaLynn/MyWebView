package com.zxn.webview;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by zxn on 2018/8/11.
 */
public class LoadWebChromeClient extends WebChromeClient {
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        LoadWebView lwv = (LoadWebView) view;

        if (newProgress == 100) {
            lwv.setProgressBarGone();
        } else {
            if (lwv.isProgressBarVisible())
                lwv.setProgressBarVisible();
            lwv.setProgress(newProgress);
        }
        super.onProgressChanged(view, newProgress);
    }


}
