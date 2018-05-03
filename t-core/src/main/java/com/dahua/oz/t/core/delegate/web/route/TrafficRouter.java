package com.dahua.oz.t.core.delegate.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.dahua.oz.t.core.delegate.TrafficDelegate;
import com.dahua.oz.t.core.delegate.web.AbstractWebDelegate;
import com.dahua.oz.t.core.delegate.web.WebDelegateImpl;

/**
 * 需要对路由进行截断
 *
 * @author O.z Young
 * @version 2018/5/2
 */

public class TrafficRouter {

    /**
     * 以tel:打头的url请求
     */
    private static final String TEL_URL = "tel:";

    private TrafficRouter() {

    }

    private static class Holder {
        private static final TrafficRouter INSTANCE = new TrafficRouter();
    }

    public static TrafficRouter getInstance() {
        return Holder.INSTANCE;
    }

    public final boolean handleWebUrl(AbstractWebDelegate delegate, String url) {
        // 进行电话的处理
        if (url.contains(TEL_URL)) {
            callPhone(delegate.getContext(), url);
            return true;
        }
        // 进行原生跳转
        // 防止BottomBar只进行内层跳转，外层不跳转，这里判断是否有父Delegate，如果有，则使用父Delegate进行跳转
        final TrafficDelegate parentDelegate = delegate.getParentDelegate();
        final WebDelegateImpl webDelegate = WebDelegateImpl.create(url);
        if (parentDelegate == null) {
            delegate.start(webDelegate);
        } else {
            parentDelegate.start(webDelegate);
        }
        return true;
    }


    private void loadWebPage(WebView webView, String url) {
        if (webView != null) {
            webView.loadUrl(url);
        } else {
            throw new NullPointerException("webview is null!!");
        }
    }

    private void loadLocalPage(WebView webView, String url) {
        loadWebPage(webView, "file:///android_asset/" + url);
    }

    private void loadPage(WebView webView, String url) {
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)) {
            loadWebPage(webView, url);
        } else {
            loadLocalPage(webView, url);
        }
    }

    public void loadPage(AbstractWebDelegate delegate, String url) {
        loadPage(delegate.getWebView(), url);
    }

    private void callPhone(Context context, String uri) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri dataUri = Uri.parse(uri);
        intent.setData(dataUri);
        ContextCompat.startActivity(context, intent, null);
    }
}
