package com.dahua.oz.t.core.net.interceptor;

import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;

/**
 * 基础拦截器
 *
 * @author O.z Young
 * @version 2018/4/11
 */

public abstract class BaseInterceptor implements Interceptor {

    /**
     * 获取有序排列的请求参数
     *
     * @param chain
     * @return
     */
    protected LinkedHashMap<String, String> getUrlParameters(Chain chain) {
        final HttpUrl url = chain.request().url();
        // 获取有多少个参数
        int querySize = url.querySize();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        for (int i = 0; i < querySize; i++) {
            params.put(url.queryParameterName(querySize), url.queryParameterValue(querySize));
        }
        return params;
    }

    /**
     * 直接通过请求参数的key值获取value
     *
     * @param chain
     * @param key
     * @return
     */
    protected String getUrlParameters(Chain chain, String key) {
        final HttpUrl url = chain.request().url();
        return url.queryParameter(key);
    }

    /**
     * 获取Post请求体中的参数
     *
     * @param chain
     * @return
     */
    protected LinkedHashMap<String, String> getBodyParameters(Chain chain) {
        final FormBody body = (FormBody) chain.request().body();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        if (body != null) {
            int bodySize = body.size();
            for (int i = 0; i < bodySize; i++) {
                params.put(body.name(i), body.value(i));
            }
        }
        return params;
    }

    protected String getBodyParametersValue(Chain chain, String key) {
        return getBodyParameters(chain).get(key);
    }
}
