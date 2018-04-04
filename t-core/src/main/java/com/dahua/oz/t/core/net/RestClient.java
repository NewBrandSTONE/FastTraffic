package com.dahua.oz.t.core.net;

import com.dahua.oz.t.core.callback.IError;
import com.dahua.oz.t.core.callback.IFailure;
import com.dahua.oz.t.core.callback.IRequest;
import com.dahua.oz.t.core.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.RequestBody;

/**
 * 网络请求客户端
 *
 * @author gaohuang_yangzi@dahuatech.com
 * @version 2018/4/4
 */

public class RestClient {
    private final String URL;
    private final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest IREQUEST;
    private final ISuccess ISUCCESS;
    private final IFailure iFAILURE;
    private final IError IERROR;
    /**
     * 这里会涉及到请求体
     */
    private final RequestBody BODY;

    public RestClient(String url, WeakHashMap<String, Object> params, IRequest request
            , ISuccess success, IFailure failure, IError error, RequestBody body) {
        this.URL = url;
        this.PARAMS.putAll(params);
        this.IREQUEST = request;
        this.ISUCCESS = success;
        this.iFAILURE = failure;
        this.IERROR = error;
        this.BODY = body;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }
}
