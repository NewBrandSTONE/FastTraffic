package com.dahua.oz.t.traffic.main.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dahua.oz.t.core.delegate.bottom.BottomItemDelegate;
import com.dahua.oz.t.traffic.R;

/**
 * 购物车页面的承载类
 *
 * @author O.z Young
 * @version 2018/5/8
 */

public class ShopCartDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
