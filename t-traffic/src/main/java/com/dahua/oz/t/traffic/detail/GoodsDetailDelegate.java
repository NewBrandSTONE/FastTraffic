package com.dahua.oz.t.traffic.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dahua.oz.t.core.delegate.TrafficDelegate;
import com.dahua.oz.t.traffic.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * 商品详情Delegate
 *
 * @author O.z Young
 * @version 2018/4/23
 */

public class GoodsDetailDelegate extends TrafficDelegate {

    public static GoodsDetailDelegate create() {
        return new GoodsDetailDelegate();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
