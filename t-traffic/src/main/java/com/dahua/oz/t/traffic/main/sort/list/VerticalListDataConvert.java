package com.dahua.oz.t.traffic.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dahua.oz.t.core.ui.recycler.AbstractDataConverter;
import com.dahua.oz.t.core.ui.recycler.ItemType;
import com.dahua.oz.t.core.ui.recycler.MultipleFields;
import com.dahua.oz.t.core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * 分类菜单数据
 *
 * @author O.z Young
 * @version 2018/4/27
 */

public final class VerticalListDataConvert extends AbstractDataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON
                .parseObject(getJsonData())
                .getJSONObject("data")
                .getJSONArray("list");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");

            final MultipleItemEntity entity =
                    MultipleItemEntity.builder().setField(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                            .setField(MultipleFields.ID, id)
                            .setField(MultipleFields.TEXT, name)
                            // 第一个被选中的菜单，默认的状态都是没有被点击的
                            .setField(MultipleFields.TAG, false)
                            .build();
            dataList.add(entity);
        }
        // 设置一个被选中
        dataList.get(0).setField(MultipleFields.TAG, true);
        return dataList;
    }
}
