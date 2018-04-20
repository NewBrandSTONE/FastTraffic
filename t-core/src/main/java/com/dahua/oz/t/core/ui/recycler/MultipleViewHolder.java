package com.dahua.oz.t.core.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * 给MultipleRecyclerAdapter中传入的ViewHolder
 *
 * @author O.z Young
 * @version 2018/4/19
 */

public class MultipleViewHolder extends BaseViewHolder {
    private MultipleViewHolder(View view) {
        super(view);
    }

    /**
     * 使用简单工厂包装一下
     *
     * @param view
     * @return
     */
    public static MultipleViewHolder create(View view) {
        return new MultipleViewHolder(view);
    }
}
