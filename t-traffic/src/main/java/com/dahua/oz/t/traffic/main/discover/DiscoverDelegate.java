package com.dahua.oz.t.traffic.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dahua.oz.t.core.delegate.bottom.BottomItemDelegate;
import com.dahua.oz.t.core.delegate.web.WebDelegateImpl;
import com.dahua.oz.t.traffic.R;

/**
 * 发现页面
 *
 * @author O.z Young
 * @version 2018/4/30
 */

public class DiscoverDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final WebDelegateImpl delegate = WebDelegateImpl.create("index.html");
        loadRootFragment(R.id.web_container, delegate);
    }
}
