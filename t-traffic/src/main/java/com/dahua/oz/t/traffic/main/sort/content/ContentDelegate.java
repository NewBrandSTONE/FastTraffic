package com.dahua.oz.t.traffic.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.dahua.oz.t.core.delegate.TrafficDelegate;
import com.dahua.oz.t.core.net.RestClient;
import com.dahua.oz.t.core.net.callback.ISuccess;
import com.dahua.oz.t.traffic.R;
import com.dahua.oz.t.traffic.R2;

import java.util.List;

import butterknife.BindView;

/**
 * 分类列表中，右侧内容
 *
 * @author O.z Young
 * @version 2018/4/23
 */

public class ContentDelegate extends TrafficDelegate {

    public static final String ARG_CONTENT_ID = "CONTENT_ID";
    private int mContentId = -1;
    private List<SectionBean> mDatas;

    @BindView(R2.id.rv_list)
    RecyclerView mRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mContentId = args.getInt(ARG_CONTENT_ID);
        }
    }

    public static ContentDelegate newInstance(int contentId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID, contentId);
        final ContentDelegate delegate = new ContentDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_list_content;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRecyclerView();
        initData();
    }

    private void initRecyclerView() {
        // 第一个是左右能显示多少个item
        final StaggeredGridLayoutManager manager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
    }

    private void initData() {
        RestClient.builder()
                .url("http://192.168.31.230:8080/sort_data?contentId=" + mContentId)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        mDatas = new SectionDataConvert().convert(response);
                        final SectionAdapter adapter =
                                new SectionAdapter(R.layout.item_section_content, R.layout.item_section_header, mDatas);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build()
                .get();
    }
}
