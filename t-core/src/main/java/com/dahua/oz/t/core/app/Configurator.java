package com.dahua.oz.t.core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 配置信息的获取以及存储
 *
 * @author gaohuang_yangzi@dahuatech.com
 * @version 2018/4/2
 */

public class Configurator {
    private static final HashMap<String, Object> TRAFIC_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private Configurator() {
        TRAFIC_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void config() {
        // 初始化图标和字体
        initIcons();
        TRAFIC_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    public HashMap<String, Object> getConfigurations() {
        return TRAFIC_CONFIGS;
    }

    public final Configurator withApiHost(String host) {
        TRAFIC_CONFIGS.put(ConfigType.API_HOST.name(), host);
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

    /**
     * 在获取某个配置文件之前，需要检查一下，配置工作是否完成
     */
    private void checkConfiguration() {
        final boolean isReady = (boolean) TRAFIC_CONFIGS.get(ConfigType.CONFIG_READY.name());
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
