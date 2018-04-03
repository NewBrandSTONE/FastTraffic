package com.dahua.oz.t.core.delegate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * 所有Fragment的基类
 *
 * @author gaohuang_yangzi@dahuatech.com
 * @version 2018/4/3
 */

public abstract class BaseDelegate extends SwipeBackFragment {

    /**
     * 设置Fragment的布局
     *
     * @return id-视图id；view-视图View
     */
    public abstract Object setLayout();

    private Unbinder mUnBinder;

    /**
     * 绑定视图时的操作
     *
     * @param savedInstanceState 缓存实例
     * @param rootView           根视图
     */
    public abstract void onBindView(@Nullable Bundle savedInstanceState, View rootView);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        }
        if (rootView != null) {
            mUnBinder = ButterKnife.bind(this, rootView);
            onBindView(savedInstanceState, rootView);
        }
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }
}
