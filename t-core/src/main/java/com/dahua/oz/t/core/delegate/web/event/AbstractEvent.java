package com.dahua.oz.t.core.delegate.web.event;

import android.content.Context;
import android.webkit.WebView;

import com.dahua.oz.t.core.delegate.TrafficDelegate;
import com.dahua.oz.t.core.delegate.web.AbstractWebDelegate;

/**
 * 抽象事件类
 *
 * @author O.z Young
 * @version 2018/5/3
 */

public abstract class AbstractEvent implements IEvent {

    private Context mContext = null;
    private String mAction = null;
    private AbstractWebDelegate mDelegate = null;
    private String mUrl = null;
    private WebView mWebView = null;

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String mAction) {
        this.mAction = mAction;
    }

    public WebView getWebView() {
        return mDelegate.getWebView();
    }

    public TrafficDelegate getDelegate() {
        return mDelegate;
    }

    public void setDelegate(AbstractWebDelegate mDelegate) {
        this.mDelegate = mDelegate;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
