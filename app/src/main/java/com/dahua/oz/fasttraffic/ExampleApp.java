package com.dahua.oz.fasttraffic;

import android.app.Application;

import com.dahua.oz.t.core.app.Traffic;
import com.dahua.oz.t.core.net.interceptor.DebugInterceptor;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * @author gaohuang_yangzi@dahuatech.com
 * @version 2018/4/2
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Traffic.init(this)
                .withApiHost("http://127.0.0.1")
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .withIcons(new FontAwesomeModule())
                .config();
    }
}
