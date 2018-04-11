package com.dahua.oz.t.core.net.interceptor;

import android.support.annotation.RawRes;

import com.dahua.oz.t.core.utils.file.FileUtil;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 调试专用的拦截器
 *
 * @author O.z Young
 * @version 2018/4/11
 */

public class DebugInterceptor extends BaseInterceptor {

    private final String DEBUG_URL;
    private final int RAW_ID;

    public DebugInterceptor(String debugUrl, int rawId) {
        this.DEBUG_URL = debugUrl;
        this.RAW_ID = rawId;
    }

    private Response getResponse(Chain chain, String json) {
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type", "application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"), json))
                .message("ok")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }

    private Response debugResponse(Chain chain, @RawRes int rowId) {
        final String json = FileUtil.getRawFile(rowId);
        return getResponse(chain, json);
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        final HttpUrl url = chain.request().url();
        if (url.toString().contains(DEBUG_URL)) {
            return debugResponse(chain, RAW_ID);
        }
        return chain.proceed(chain.request());
    }
}
