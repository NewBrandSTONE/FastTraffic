package com.dahua.oz.t.core.delegate.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dahua.oz.t.core.delegate.web.chromeclient.TrafficWebViewChromeClientImpl;
import com.dahua.oz.t.core.delegate.web.client.WebViewClientImpl;
import com.dahua.oz.t.core.delegate.web.route.RouteKeys;
import com.dahua.oz.t.core.delegate.web.route.TrafficRouter;

/**
 * AbstractWebDelegate的实现类，以WebView作为主布局
 *
 * @author O.z Young
 * @version 2018/5/2
 */

public class WebDelegateImpl extends AbstractWebDelegate {

    public static WebDelegateImpl create(String url) {
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        final WebDelegateImpl webDelegate = new WebDelegateImpl();
        webDelegate.setArguments(args);
        return webDelegate;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        if (getUrl() != null) {
            // 用原生的方式模拟Web跳转
            // 使用WebViewClient方法进行拦截
            TrafficRouter.getInstance().loadPage(this, getUrl());
        }
    }

    @Override
    public IWebViewInit setInitializer() {
        return this;
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitalizer().ceateWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClient client = new WebViewClientImpl(this);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new TrafficWebViewChromeClientImpl();
    }
}
