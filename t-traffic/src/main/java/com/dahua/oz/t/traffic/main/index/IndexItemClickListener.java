package com.dahua.oz.t.traffic.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.dahua.oz.t.core.delegate.TrafficDelegate;
import com.dahua.oz.t.traffic.detail.GoodsDetailDelegate;

/**
 * 首页item点击事件
 *
 * @author O.z Young
 * @version 2018/4/23
 */

public class IndexItemClickListener extends SimpleClickListener {

    private final TrafficDelegate DELEGATE;

    private IndexItemClickListener(TrafficDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static IndexItemClickListener create(TrafficDelegate delegate) {
        return new IndexItemClickListener(delegate);
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final GoodsDetailDelegate detailDelegate = GoodsDetailDelegate.create();
        DELEGATE.start(detailDelegate);
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
