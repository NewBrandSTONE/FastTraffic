package com.dahua.oz.fasttraffic;

import android.app.Application;

import com.dahua.oz.t.core.app.Traffic;
import com.dahua.oz.t.core.delegate.web.event.TestEvent;
import com.dahua.oz.t.core.net.interceptor.DebugInterceptor;
import com.dahua.oz.t.traffic.database.DataBaseManager;
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
                .withApiHost("http://192.168.31.230:8080/")
                .withJavascriptInterface("traffic")
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .withIcons(new FontAwesomeModule())
                .withWebEvent("test", new TestEvent())
                .config();

        // 数据库初始化
        DataBaseManager.getInstance().init(this);
    }
}
