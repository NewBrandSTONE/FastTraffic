package com.dahua.oz.t.core.net;

import android.content.Context;

import com.dahua.oz.t.core.net.callback.IError;
import com.dahua.oz.t.core.net.callback.IFailure;
import com.dahua.oz.t.core.net.callback.IRequest;
import com.dahua.oz.t.core.net.callback.ISuccess;
import com.dahua.oz.t.core.ui.loader.LoaderStyle;

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

public class RestClientBuilder {

    private String mUrl;
    private WeakHashMap<String, Object> mParams = RestCreator.getParams();
    private IRequest mRequest;
    private ISuccess mSuccess;
    private IFailure mFailure;
    private IError mError;
    private RequestBody mBody;
    private LoaderStyle mLoaderStyle;
    private Context mContext;
    private File mFile;
    private String mDownloadDir;
    private String mExtension;
    private String mFileName;

    RestClientBuilder() {
    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        mParams.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        this.mParams.put(key, value);
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder success(ISuccess success) {
        this.mSuccess = success;
        return this;
    }

    public final RestClientBuilder failure(IFailure failure) {
        this.mFailure = failure;
        return this;
    }

    public final RestClientBuilder error(IError error) {
        this.mError = error;
        return this;
    }

    public final RestClientBuilder request(IRequest request) {
        this.mRequest = request;
        return this;
    }

    public final RestClientBuilder loader(Context context, LoaderStyle loaderStyle) {
        this.mContext = context;
        this.mLoaderStyle = loaderStyle;
        return this;
    }

    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallSpinFadeLoaderIndicator;
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }


    public final RestClientBuilder downloadURL(String url) {
        this.mDownloadDir = url;
        return this;
    }

    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    public final RestClientBuilder downloadFileName(String fileName) {
        this.mFileName = fileName;
        return this;
    }


    public final RestClient build() {
        // 这里面的任何一个参数都不能为空，否则会报空指针错误
        return new RestClient(
                mUrl,
                mParams,
                mRequest,
                mSuccess,
                mFailure,
                mError,
                mBody,
                mLoaderStyle,
                mContext,
                mFile,
                mDownloadDir,
                mExtension,
                mFileName);
    }
}
