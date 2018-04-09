package com.dahua.oz.t.core.net;

import android.content.Context;

import com.dahua.oz.t.core.callback.IError;
import com.dahua.oz.t.core.callback.IFailure;
import com.dahua.oz.t.core.callback.IRequest;
import com.dahua.oz.t.core.callback.ISuccess;
import com.dahua.oz.t.core.callback.RequestCallBacks;
import com.dahua.oz.t.core.ui.LoaderStyle;
import com.dahua.oz.t.core.ui.TrafficLoader;

import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * 网络请求客户端
 *
 * @author gaohuang_yangzi@dahuatech.com
 * @version 2018/4/4
 */

public class RestClient {
    private final String URL;
    private final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    /**
     * 这里会涉及到请求体
     */
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;

    public RestClient(String url,
                      WeakHashMap<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      LoaderStyle loaderStyle,
                      Context context) {
        this.URL = url;
        this.PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.LOADER_STYLE = loaderStyle;
        this.CONTEXT = context;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        if (LOADER_STYLE != null) {
            TrafficLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (method) {
            case GET: {
                call = service.get(URL, PARAMS);
                break;
            }
            case POST: {
                call = service.post(URL, PARAMS);
                break;
            }
            case PUT: {
                call = service.put(URL, PARAMS);
                break;
            }
            case DELETE: {
                call = service.delete(URL, PARAMS);
                break;
            }
            default: {
                break;
            }
        }

        if (call != null) {
            call.enqueue(getRequestCallBack());
        }
    }

    private Callback<String> getRequestCallBack() {
        return new RequestCallBacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,
                LOADER_STYLE);
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

}
