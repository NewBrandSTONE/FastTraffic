package com.dahua.oz.fasttraffic;

import android.app.Application;

import com.dahua.oz.t.core.app.Traffic;
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
                .withApiHost("http://www.baidu.com")
                .withIcons(new FontAwesomeModule())
                .config();
    }
}
