package com.dahua.oz.t.core.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.dahua.oz.t.core.app.Traffic;
import com.dahua.oz.t.core.net.RestClient;
import com.dahua.oz.t.core.net.callback.ISuccess;

/**
 * SwipRefreshLayout的刷新方法
 *
 * @author O.z Young
 * @version 2018/4/18
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;

    public RefreshHandler(SwipeRefreshLayout refreshLayout) {
        this.REFRESH_LAYOUT = refreshLayout;
        REFRESH_LAYOUT.setOnRefreshListener(this);
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
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(Traffic.getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .get();
    }
}
