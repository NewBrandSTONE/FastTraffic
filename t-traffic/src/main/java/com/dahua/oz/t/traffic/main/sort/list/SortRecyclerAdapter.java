package com.dahua.oz.t.traffic.main.sort.list;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.dahua.oz.t.core.delegate.TrafficDelegate;
import com.dahua.oz.t.core.ui.recycler.ItemType;
import com.dahua.oz.t.core.ui.recycler.MultipleFields;
import com.dahua.oz.t.core.ui.recycler.MultipleItemEntity;
import com.dahua.oz.t.core.ui.recycler.MultipleRecyclerAdapter;
import com.dahua.oz.t.core.ui.recycler.MultipleViewHolder;
import com.dahua.oz.t.traffic.R;
import com.dahua.oz.t.traffic.main.sort.SortDelegate;
import com.dahua.oz.t.traffic.main.sort.content.ContentDelegate;

import java.util.List;

/**
 * 分类菜单列表的Adapter
 *
 * @author O.z Young
 * @version 2018/4/28
 */

public class SortRecyclerAdapter extends MultipleRecyclerAdapter {

    /**
     * 传入SortDelegate才能控制左右关系
     */
    private final SortDelegate DELEGATE;
    /**
     * 上一次点击的位置
     */
    private int mPrePosition = 0;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    protected SortRecyclerAdapter(List<MultipleItemEntity> data, SortDelegate delegate) {
        super(data);
        this.DELEGATE = delegate;
        // 添加垂直菜单布局
        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_vertical_menu_list);
    }

    @Override
    protected void convert(final MultipleViewHolder helper, final MultipleItemEntity item) {
        super.convert(helper, item);
        switch (helper.getItemViewType()) {
            case ItemType.VERTICAL_MENU_LIST:
                final String text = item.getField(MultipleFields.TEXT);
                final boolean isClicked = item.getField(MultipleFields.TAG);
                final AppCompatTextView name = helper.getView(R.id.tv_vertical_item_name);
                name.setText(text);
                final View lineLeft = helper.getView(R.id.view_line);
                final View itemView = helper.itemView;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final int currentPosition = helper.getAdapterPosition();
                        if (mPrePosition != currentPosition) {
                            // 还原上一个
                            getData().get(mPrePosition).setField(MultipleFields.TAG, false);
                            notifyItemChanged(mPrePosition);
                            // 更新选中的item
                            item.setField(MultipleFields.TAG, true);
                            notifyItemChanged(currentPosition);

                            mPrePosition = currentPosition;

                            final int contentId = getData().get(currentPosition).getField(MultipleFields.ID);
                            showContent(contentId);
                        }
                    }
                });
                if (!isClicked) {
                    lineLeft.setVisibility(View.INVISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.we_chat_black));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.item_background));
                } else {
                    lineLeft.setVisibility(View.VISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.app_main));
                    lineLeft.setBackgroundColor(ContextCompat.getColor(mContext, R.color.app_main));
                    itemView.setBackgroundColor(Color.WHITE);
                }
                break;
            default:
                break;
        }
    }

    private void showContent(int contentId) {
        final ContentDelegate delegate = ContentDelegate.newInstance(contentId);
    }

    private void switchContent(ContentDelegate delegate) {
        // 找到content的Fragment
        final TrafficDelegate contentDelegate = DELEGATE.findChildFragment(ContentDelegate.class);
        if (contentDelegate != null) {
            contentDelegate.replaceFragment(delegate, false);
        }
    }
}
