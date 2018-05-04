package com.dahua.oz.t.core.delegate.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.dahua.oz.t.core.delegate.web.event.AbstractEvent;
import com.dahua.oz.t.core.delegate.web.event.EventManager;

/**
 * JS与原生交互的接口
 *
 * @author O.z Young
 * @version 2018/4/30
 */

public final class TrafficWebInterface {

    private final AbstractWebDelegate DELEGATE;

    private TrafficWebInterface(AbstractWebDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    static TrafficWebInterface create(AbstractWebDelegate delegate) {
        return new TrafficWebInterface(delegate);
    }


    @SuppressWarnings("unused")
    @JavascriptInterface
    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");
        AbstractEvent event = EventManager.getInstance().createEvent(action);
        if (event != null) {
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }
}
