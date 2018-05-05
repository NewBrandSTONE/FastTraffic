package com.dahua.oz.t.core.net.rx;

import com.dahua.oz.t.core.utils.storage.TrafficPreference;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 添加Cookie拦截器
 *
 * @author O.z Young
 * @version 2018/5/5
 */

public final class AddCookieInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        // 将原始请求拦截下来
        final Request.Builder builder = chain.request().newBuilder();
        Observable
                .just(TrafficPreference.getCustomAppProfile("cookie"))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String cookie) throws Exception {
                        // 给API请求添加WebView拦截下来的Cookie
                        builder.addHeader("Cookie", cookie);
                    }
                });
        return chain.proceed(builder.build());
    }
}
