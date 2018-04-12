package com.dahua.oz.fasttraffic;

import com.dahua.oz.t.core.activities.AbstractProxyActivity;
import com.dahua.oz.t.core.delegate.TrafficDelegate;
import com.dahua.oz.t.traffic.launcher.LauncherDelegate;

/**
 * 全局唯一的Activity
 *
 * @author Oz. Young
 */
public class ExampleActivity extends AbstractProxyActivity {

    @Override
    public TrafficDelegate setRootDelegate() {
        return new LauncherDelegate();
    }
}
