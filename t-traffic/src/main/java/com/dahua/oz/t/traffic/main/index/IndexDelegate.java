package com.dahua.oz.t.traffic.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.dahua.oz.t.core.bottom.BottomItemDelegate;
import com.dahua.oz.t.core.net.RestClient;
import com.dahua.oz.t.core.net.callback.ISuccess;
import com.dahua.oz.t.core.ui.recycler.MultipleFields;
import com.dahua.oz.t.core.ui.recycler.MultipleItemEntity;
import com.dahua.oz.t.core.ui.refresh.RefreshHandler;
import com.dahua.oz.t.traffic.R;
import com.dahua.oz.t.traffic.R2;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 底部导航
 *
 * @author O.z Young
 * @version 2018/4/17
 */

public class IndexDelegate extends BottomItemDelegate {

    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;

    private RefreshHandler mRefreshHandler = null;

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
//        mRefreshHandler.firstPage("http://192.168.31.230:8080/index");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler = new RefreshHandler(mRefreshLayout);
        RestClient.builder()
                .url("http://192.168.31.230:8080/index")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        IndexDataConverter converter = new IndexDataConverter();
                        converter.setJsonData(response);
                        ArrayList<MultipleItemEntity> datas = converter.convert();
                        String text = datas.get(1).getField(MultipleFields.TEXT);
                        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .get();
    }
}
