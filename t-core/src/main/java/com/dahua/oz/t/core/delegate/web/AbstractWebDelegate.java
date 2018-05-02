package com.dahua.oz.t.core.delegate.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.dahua.oz.t.core.delegate.TrafficDelegate;
import com.dahua.oz.t.core.delegate.web.route.RouteKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Web封装抽象类
 *
 * @author O.z Young
 * @version 2018/4/30
 */

public abstract class AbstractWebDelegate extends TrafficDelegate {
    private WebView mWebView = null;
    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();
    private String mUrl = null;
    /**
     * WebView是否可用
     */
    private boolean mIsWebViewAvailable = false;

    public AbstractWebDelegate() {

    }

    /**
     * 初始化WebView
     *
     * @return webView
     */
    public abstract IWebViewInit setInitializer();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mUrl = args.getString(RouteKeys.URL.name());
    }

    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            final IWebViewInit init = setInitializer();
            if (init != null) {
                final WeakReference<WebView> webViewWeakReference =
                        new WeakReference<>(new WebView(getContext()), WEB_VIEW_QUEUE);
                mWebView = webViewWeakReference.get();
                mWebView = init.initWebView(mWebView);
                mWebView.setWebViewClient(init.initWebViewClient());
                mWebView.setWebChromeClient(init.initWebChromeClient());
                // js与原生进行交互
                mWebView.addJavascriptInterface(TrafficWebInterface.create(this), "taffic");
                // webview可以使用了
                mIsWebViewAvailable = true;
            } else {
                throw new NullPointerException("Initializer is null");
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebViewAvailable = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.destroy();
        }
    }
}
