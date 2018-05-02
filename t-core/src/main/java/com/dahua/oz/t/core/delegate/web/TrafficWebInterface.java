package com.dahua.oz.t.core.delegate.web;

import com.alibaba.fastjson.JSON;

/**
 * JS与原生交互的接口
 *
 * @author O.z Young
 * @version 2018/4/30
 */

public class TrafficWebInterface {

    private final AbstractWebDelegate DELEGATE;

    private TrafficWebInterface(AbstractWebDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    static TrafficWebInterface create(AbstractWebDelegate delegate) {
        return new TrafficWebInterface(delegate);
    }

    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");
        return null;
    }
}
