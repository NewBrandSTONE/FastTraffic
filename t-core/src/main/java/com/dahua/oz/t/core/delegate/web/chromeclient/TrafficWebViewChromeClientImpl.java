package com.dahua.oz.t.core.delegate.web.chromeclient;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * 实现WebView内部的行为控制
 *
 * @author O.z Young
 * @version 2018/5/2
 */

public class TrafficWebViewChromeClientImpl extends WebChromeClient {
    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return super.onJsAlert(view, url, message, result);
    }
}
