package com.dahua.oz.t.core.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.dahua.oz.t.core.R;
import com.dahua.oz.t.core.delegate.TrafficDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Activity的代理类
 *
 * @author gaohuang_yangzi@dahuatech.com
 * @version 2018/4/3
 */

public abstract class ProxyActivity extends SupportActivity {

    /**
     * 返回根Delegate
     *
     * @return 根Delegate
     */
    public abstract TrafficDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 下面两个方法不一定执行
        System.gc();
        System.runFinalization();
    }
}
