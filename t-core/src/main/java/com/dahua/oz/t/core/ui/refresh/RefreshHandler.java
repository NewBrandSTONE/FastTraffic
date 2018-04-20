package com.dahua.oz.t.core.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dahua.oz.t.core.app.Traffic;
import com.dahua.oz.t.core.net.RestClient;
import com.dahua.oz.t.core.net.callback.ISuccess;
import com.dahua.oz.t.core.ui.recycler.AbstractDataConverter;
import com.dahua.oz.t.core.ui.recycler.MultipleRecyclerAdapter;

/**
 * SwipRefreshLayout的刷新方法
 *
 * @author O.z Young
 * @version 2018/4/18
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private final AbstractDataConverter CONVERTER;
    private MultipleRecyclerAdapter mAdapter = null;


    private RefreshHandler(SwipeRefreshLayout refreshLayout, RecyclerView recyclerView,
                           AbstractDataConverter converter, PagingBean bean) {
        this.REFRESH_LAYOUT = refreshLayout;
        this.RECYCLERVIEW = recyclerView;
        this.CONVERTER = converter;
        this.BEAN = bean;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    public static RefreshHandler create(SwipeRefreshLayout refreshLayout, RecyclerView recyclerView,
                                        AbstractDataConverter converter) {
        return new RefreshHandler(refreshLayout, recyclerView, converter, new PagingBean());
    }


    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        // 模拟网络请求
        Traffic.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 进行网络请求

                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    public void firstPage(String url) {
        // 增加延迟效果
        BEAN.setDelayed(1000);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject jsonObject = JSONObject.parseObject(response);
                        BEAN.setTotal(jsonObject.getInteger("total"))
                                .setPageSize(jsonObject.getInteger("page_size"));
                        // 设置Adapter
                        mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response));
                        mAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(mAdapter);
                        BEAN.addIndex();
                    }
                })
                .build()
                .get();
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
