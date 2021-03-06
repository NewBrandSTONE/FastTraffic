package com.dahua.oz.t.core.app;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.dahua.oz.t.core.delegate.web.event.AbstractEvent;
import com.dahua.oz.t.core.delegate.web.event.EventManager;
import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * 配置信息的获取以及存储
 *
 * @author gaohuang_yangzi@dahuatech.com
 * @version 2018/4/2
 */

public class Configurator {
    private static final HashMap<String, Object> TRAFIC_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();
    private static final Handler HANDLER = new Handler();

    private Configurator() {
        TRAFIC_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), false);
        TRAFIC_CONFIGS.put(ConfigKeys.HANDLER.name(), HANDLER);
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void config() {
        // 初始化图标和字体
        initIcons();
        TRAFIC_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), true);
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    public HashMap<String, Object> getConfigurations() {
        return TRAFIC_CONFIGS;
    }

    public final Configurator withApiHost(String host) {
        TRAFIC_CONFIGS.put(ConfigKeys.API_HOST.name(), host);
        return this;
    }

    private void initIcons() {
        if (ICONS.size() > 0) {
            // 此时已经有一个字体了
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcons(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        TRAFIC_CONFIGS.put(ConfigKeys.INTERCEPTOR.name(), INTERCEPTORS);
        return this;
    }

    public Configurator withJavascriptInterface(@NonNull String name) {
        TRAFIC_CONFIGS.put(ConfigKeys.JAVASCRIPT_INTERFACE.name(), name);
        return this;
    }

    public Configurator withWebEvent(@NonNull String name, @NonNull AbstractEvent event) {
        final EventManager manager = EventManager.getInstance();
        manager.addEvent(name, event);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> list) {
        INTERCEPTORS.addAll(list);
        TRAFIC_CONFIGS.put(ConfigKeys.INTERCEPTOR.name(), list);
        return this;
    }

    public final Configurator withWxAppIdKey(String idKey) {
        TRAFIC_CONFIGS.put(ConfigKeys.WXAPP_ID.name(), idKey);
        return this;
    }

    public final Configurator withWxAppSecret(String secretKey) {
        TRAFIC_CONFIGS.put(ConfigKeys.WXAPP_KEY.name(), secretKey);
        return this;
    }

    public final Configurator withWxCallBackActivity(Activity activity) {
        TRAFIC_CONFIGS.put(ConfigKeys.WX_CALLBACK_ACTIVITY.name(), activity);
        return this;
    }

    /**
     * 浏览器加载的Host
     *
     * @param webHost 浏览器Host
     * @return
     */
    public final Configurator withWebHost(String webHost) {
        TRAFIC_CONFIGS.put(ConfigKeys.WEB_HOST.name(), webHost);
        return this;
    }

    /**
     * 在获取某个配置文件之前，需要检查一下，配置工作是否完成
     */
    private void checkConfiguration() {
        final boolean isReady = (boolean) TRAFIC_CONFIGS.get(ConfigKeys.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready, call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(String key) {
        checkConfiguration();
        return (T) TRAFIC_CONFIGS.get(key);
    }
}
