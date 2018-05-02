package com.dahua.oz.t.traffic.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dahua.oz.t.core.delegate.bottom.BottomItemDelegate;
import com.dahua.oz.t.traffic.R;
import com.dahua.oz.t.traffic.main.sort.content.ContentDelegate;
import com.dahua.oz.t.traffic.main.sort.list.VerticalListDelegate;

/**
 * 分类父级页面
 *
 * @author O.z Young
 * @version 2018/4/23
 */

public class SortDelegate extends BottomItemDelegate {


    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        super.onLazyInitView(savedInstanceState);
        final VerticalListDelegate listDelegate = new VerticalListDelegate();
        getSupportDelegate().loadRootFragment(R.id.vertical_list_container, listDelegate);
        // 设置右侧第一个分类显示，默认显示分类1
        getSupportDelegate().loadRootFragment(R.id.sort_contain_container, ContentDelegate.newInstance(1));
    }
}

