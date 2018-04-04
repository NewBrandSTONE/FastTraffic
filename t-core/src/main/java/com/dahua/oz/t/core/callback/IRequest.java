package com.dahua.oz.t.core.callback;

/**
 * 发送请求时回调的接口
 *
 * @author gaohuang_yangzi@dahuatech.com
 * @version 2018/4/4
 */

public interface IRequest {

    void onRequestStart();

    void onRequestEnd();

}
