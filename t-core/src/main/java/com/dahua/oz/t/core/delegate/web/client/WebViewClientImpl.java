package com.dahua.oz.t.core.delegate.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dahua.oz.t.core.app.ConfigKeys;
import com.dahua.oz.t.core.app.Traffic;
import com.dahua.oz.t.core.delegate.IPageLoaderListener;
import com.dahua.oz.t.core.delegate.web.AbstractWebDelegate;
import com.dahua.oz.t.core.delegate.web.route.TrafficRouter;
import com.dahua.oz.t.core.ui.loader.TrafficLoader;
import com.dahua.oz.t.core.utils.storage.TrafficPreference;

/**
 * 当Web内容进行跳转的时候，使用原生的WebViewClient进行拦截，并跳转
 *
 * @author O.z Young
 * @version 2018/5/2
 */

public class WebViewClientImpl extends WebViewClient {

    private final AbstractWebDelegate DELEGATE;

    private IPageLoaderListener mPageLoaderListener = null;

    private static Handler HANDLER = new Handler();

    public WebViewClientImpl(AbstractWebDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    public void setPageLoaderListener(IPageLoaderListener pageLoaderListener) {
        this.mPageLoaderListener = pageLoaderListener;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.d("WebViewClientImpl", "shouldOverrideUrlLoading: url->" + url);
        // 所有Javascript的location.href都有这里来接管，如果return true就表示由我来处理
        return TrafficRouter.getInstance().handleWebUrl(DELEGATE, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mPageLoaderListener != null) {
            mPageLoaderListener.onLoadStart();
        }
        // 展示原生的Loader
        TrafficLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        // 同步Cookie
        syncCookie();
        if (mPageLoaderListener != null) {
            mPageLoaderListener.onLoadEnd();
        }
        TrafficLoader.stopLoading();
    }

    /**
     * 获取浏览器的Cookie
     */
    private void syncCookie() {
        final CookieManager cookieManager = CookieManager.getInstance();
        // 注意，这里的Cookie和API请求的是不一样的
        // 这个在网页中不可见

        String webHost = (String) Traffic.getConfigurations().get(ConfigKeys.WEB_HOST.name());
        if (webHost != null) {
            // 这个url写具体的网页域名
            final String cookieStr = cookieManager.getCookie(webHost);
            if (cookieStr != null && !cookieStr.equals("")) {
                if (cookieManager.hasCookies()) {
                    // 将webHost保存到SharePreference中
                    TrafficPreference.addCustomAppProfile("cookie", cookieStr);
                }
            }
        }
    }
}
