package com.dahua.oz.t.core.delegate.web.client;

import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dahua.oz.t.core.delegate.web.AbstractWebDelegate;
import com.dahua.oz.t.core.delegate.web.route.TrafficRouter;

/**
 * 当Web内容进行跳转的时候，使用原生的WebViewClient进行拦截，并跳转
 *
 * @author O.z Young
 * @version 2018/5/2
 */

public class WebViewClientImpl extends WebViewClient {

    private final AbstractWebDelegate DELEGATE;

    public WebViewClientImpl(AbstractWebDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.d("WebViewClientImpl", "shouldOverrideUrlLoading: url->" + url);
        // 所有Javascript的location.href都有这里来接管，如果return true就表示由我来处理
        return TrafficRouter.getInstance().handleWebUrl(DELEGATE, url);
    }
}
