package com.zxn.webview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.List;
import java.util.Map;

/**
 * Created by zxn on 2018/8/10.
 */
public class LoadWebView extends WebView {

    private ProgressBar mProgressBar;
    private CookieManager mCookieManager;

    public LoadWebView(Context context) {
        this(context, null);
    }

    public LoadWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mProgressBar = new ProgressBar(getContext(), null,
                android.R.attr.progressBarStyleHorizontal);
        LinearLayout.LayoutParams layoutParams
                = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 8);
        mProgressBar.setLayoutParams(layoutParams);
        Drawable drawable
                = getContext()
                .getResources()
                .getDrawable(R.drawable.wv_pb_pd_sp_load);
        mProgressBar.setProgressDrawable(drawable);
        addView(mProgressBar);


        // 支持获取手势焦点
        requestFocusFromTouch();
        setHorizontalFadingEdgeEnabled(true);
        setVerticalFadingEdgeEnabled(false);
        setVerticalScrollBarEnabled(false);

        // 支持JS
        getSettings().setJavaScriptEnabled(true);
        getSettings().setBuiltInZoomControls(true);
        getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        getSettings().setDisplayZoomControls(true);
        getSettings().setLoadWithOverviewMode(true);

        // 支持插件
        getSettings().setPluginState(WebSettings.PluginState.ON);
        getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);

        // 自适应屏幕
        getSettings().setUseWideViewPort(true);
        getSettings().setLoadWithOverviewMode(true);

        // 支持缩放
        getSettings().setSupportZoom(false);//就是这个属性把我搞惨了，
        // 隐藏原声缩放控件
        getSettings().setDisplayZoomControls(false);
        // 支持内容重新布局
        getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        getSettings().supportMultipleWindows();
        getSettings().setSupportMultipleWindows(true);
        // 设置缓存模式
        getSettings().setDomStorageEnabled(true);
        getSettings().setDatabaseEnabled(true);
        getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        getSettings().setAppCacheEnabled(true);
        getSettings().setAppCachePath(getContext().getCacheDir().getAbsolutePath());
        // 设置可访问文件
        getSettings().setAllowFileAccess(true);
        getSettings().setNeedInitialFocus(true);
        // 支持自定加载图片
        if (Build.VERSION.SDK_INT >= 19) {
            getSettings().setLoadsImagesAutomatically(true);
        } else {
            getSettings().setLoadsImagesAutomatically(false);
        }
        getSettings().setNeedInitialFocus(true);
        // 设定编码格式
        getSettings().setDefaultTextEncodingName("UTF-8");

        getSettings().setAllowFileAccessFromFileURLs(true);
        getSettings().setAllowUniversalAccessFromFileURLs(true);

        setWebChromeClient(new LoadWebChromeClient());

        setWebViewClient(new LoadWebViewClient());
//        addJavascriptInterface(new JsObject(), "am");

        CookieSyncManager.createInstance(getContext());
        mCookieManager = CookieManager.getInstance();
    }

    public void setProgressBarGone() {
        mProgressBar.setVisibility(View.GONE);
    }

    public boolean isProgressBarVisible() {
        return mProgressBar.getVisibility() == View.VISIBLE;
    }

    public void setProgressBarVisible() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void setProgress(int newProgress) {
        mProgressBar.setProgress(newProgress);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) mProgressBar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        mProgressBar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    /**
     * 设置Cookie
     *
     * @param url    接口连接
     * @param cookie cookie值.
     */
    public void setCookie(String url, String cookie) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        mCookieManager.setAcceptCookie(true);
        /*cookieManager.removeSessionCookie();
        mCookieManager.removeAllCookie();*/
        mCookieManager.setCookie(url, cookie);
        if (Build.VERSION.SDK_INT < 21) {
            CookieSyncManager.getInstance().sync();
        } else {
            CookieManager.getInstance().flush();
        }
    }

    public void loadUrlWithCookie(String url, Map<String, String> headers, String cookie) {
        setCookie(url, cookie);
        super.loadUrl(url, headers);
    }

    @Deprecated
    public void loadUrl(String url, String aTokenV1, String utoken) {
        setNormalCookie(url, aTokenV1, utoken);
        super.loadUrl(url);
    }

    public void loadUrl(String url, List<String> cookies) {
        for (String cookie : cookies) {
            setCookie(url, cookie);
        }
        super.loadUrl(url);
    }

    /**
     * 设置多个cookie,和添加headers参数.
     *
     * @param url
     * @param headers
     * @param cookies
     */
    public void loadUrl(String url, Map<String, String> headers, List<String> cookies) {
        for (String cookie : cookies) {
            setCookie(url, cookie);
        }
        super.loadUrl(url, headers);
    }

    /**
     * 设置Cookie
     *
     * @param htmlUrl
     * @param
     */
    @Deprecated
    public void setNormalCookie(String htmlUrl, String aTokenV1, String utoken) {
        if (TextUtils.isEmpty(htmlUrl)) {
            return;
        }

        CookieSyncManager.createInstance(getContext());
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);

        cookieManager.setCookie(htmlUrl, "aTokenV1=" + aTokenV1);
        cookieManager.setCookie(htmlUrl, " utoken=" + utoken);
        if (Build.VERSION.SDK_INT < 21) {
            CookieSyncManager.getInstance().sync();
        } else {
            CookieManager.getInstance().flush();
        }

    }
}
