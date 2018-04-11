package com.dahua.oz.t.core.app;

import android.content.Context;

import java.util.HashMap;

/**
 * 统一处理整个APP，对外的工具类
 * <p>
 * 增加final别人就不能随意修改
 *
 * @author gaohuang_yangzi@dahuatech.com
 * @version 2018/4/2
 */

public final class Traffic {

    public static Configurator init(Context context) {
        // 将整个APP的Context传入到配置文件中
        getConfigurations().put(ConfigKeys.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<String, Object> getConfigurations() {
        return Configurator.getInstance().getConfigurations();
    }

    public static Context getApplicationContet() {
        return (Context) getConfigurations().get(ConfigKeys.APPLICATION_CONTEXT.name());
    }

}
