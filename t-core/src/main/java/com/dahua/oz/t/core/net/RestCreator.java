package com.dahua.oz.t.core.net;

import com.dahua.oz.t.core.app.ConfigType;
import com.dahua.oz.t.core.app.Traffic;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 创建RestService，OkHttp以及RestClient，并且对外提供getRestService()方法。
 * <p>
 * ParamHolder用来保存请求参数
 *
 * @author gaohuang_yangzi@dahuatech.com
 * @version 2018/4/4
 */

public class RestCreator {

    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    private static class ParamsHolder {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    private static class RetorfitHolder {
        private static final String BASE_URL = (String) Traffic.getConfigurations().get(ConfigType.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    private static final class OkHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE = RetorfitHolder.RETROFIT_CLIENT.create(RestService.class);
    }
}
