package com.dahua.oz.fasttraffic;

import com.dahua.oz.t.core.activities.AbstractProxyActivity;
import com.dahua.oz.t.core.delegate.TrafficDelegate;

/**
 * 全局唯一的Activity
 *
 * @author Oz. Young
 */
public class ExampleActivity extends AbstractProxyActivity {

    @Override
    public TrafficDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
