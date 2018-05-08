package com.dahua.oz.t.traffic.main;

import android.graphics.Color;

import com.dahua.oz.t.core.delegate.bottom.BaseBottomDelegate;
import com.dahua.oz.t.core.delegate.bottom.BottomItemDelegate;
import com.dahua.oz.t.core.delegate.bottom.BottomTabBean;
import com.dahua.oz.t.core.delegate.bottom.ItemBuilder;
import com.dahua.oz.t.traffic.main.cart.ShopCartDelegate;
import com.dahua.oz.t.traffic.main.discover.DiscoverDelegate;
import com.dahua.oz.t.traffic.main.index.IndexDelegate;
import com.dahua.oz.t.traffic.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * 首页包含底部导航栏的页面
 *
 * @author O.z Young
 * @version 2018/4/17
 */

public class TrafficBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItem(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new IndexDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickColor() {
        return Color.parseColor("#ffff8800");
    }
}
