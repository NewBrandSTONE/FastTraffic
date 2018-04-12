package com.dahua.oz.t.core.net.rx;

import android.content.Context;

import com.dahua.oz.t.core.net.RestCreator;
import com.dahua.oz.t.core.ui.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * RestClient构建对象
 *
 * @author gaohuang_yangzi@dahuatech.com
 * @version 2018/4/4
 */

public class RxRestClientBuilder {

    private String mUrl;
    private WeakHashMap<String, Object> mParams = RestCreator.getParams();
    private RequestBody mBody;
    private LoaderStyle mLoaderStyle;
    private Context mContext;
    private File mFile;

    RxRestClientBuilder() {
    }

    public final RxRestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder params(WeakHashMap<String, Object> params) {
        mParams.putAll(params);
        return this;
    }

    public final RxRestClientBuilder params(String key, Object value) {
        this.mParams.put(key, value);
        return this;
    }

    public final RxRestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }


    public final RxRestClientBuilder loader(Context context, LoaderStyle loaderStyle) {
        this.mContext = context;
        this.mLoaderStyle = loaderStyle;
        return this;
    }

    public final RxRestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallSpinFadeLoaderIndicator;
        return this;
    }

    public final RxRestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RxRestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final RxRestClient build() {
        // 这里面的任何一个参数都不能为空，否则会报空指针错误
        return new RxRestClient(mUrl, mParams, mBody,
                mLoaderStyle, mContext, mFile);
    }
}
