package com.dahua.oz.t.traffic.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dahua.oz.t.core.bottom.BottomItemDelegate;
import com.dahua.oz.t.traffic.R;

/**
 * 底部导航
 *
 * @author O.z Young
 * @version 2018/4/17
 */

public class IndexDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
