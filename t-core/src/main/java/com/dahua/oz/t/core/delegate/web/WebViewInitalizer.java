package com.dahua.oz.t.core.delegate.web;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.dahua.oz.t.core.app.ConfigKeys;
import com.dahua.oz.t.core.app.Traffic;

/**
 * WebView初始化类
 *
 * @author O.z Young
 * @version 2018/5/2
 */

public class WebViewInitalizer {

    @SuppressLint("SetJavaScriptEnabled")
    public WebView ceateWebView(WebView webView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        // cookie
        final CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.setAcceptThirdPartyCookies(webView, true);
        }
        CookieManager.setAcceptFileSchemeCookies(true);

        // 不能横向滚动
        webView.setHorizontalScrollBarEnabled(false);
        // 不能纵向滚动
        webView.setVerticalScrollBarEnabled(false);
        // 允许截图
        webView.setDrawingCacheEnabled(true);
        // 屏蔽长按事件
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });
        // 初始化settings
        final WebSettings settings = webView.getSettings();
        // 设置UserAgent
        String ua = settings.getUserAgentString();
        settings.setUserAgentString(ua + Traffic.getConfigurations().get(ConfigKeys.JAVASCRIPT_INTERFACE.name()));
        // 允许JavaScript脚本运行
        settings.setJavaScriptEnabled(true);
        // 隐藏缩放控件
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        // 禁止缩放
        settings.setSupportZoom(false);
        // 文件权限
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setAllowContentAccess(true);
        // 缓存相关
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        return webView;
    }

}
