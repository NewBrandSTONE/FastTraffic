package com.dahua.oz.t.core.delegate.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * WebView的初始化接口
 *
 * @author O.z Young
 * @version 2018/4/30
 */

public interface IWebViewInit {

    WebView initWebView(WebView webView);

    WebViewClient initWebViewClient();

    WebChromeClient initWebChromeClient();

}
