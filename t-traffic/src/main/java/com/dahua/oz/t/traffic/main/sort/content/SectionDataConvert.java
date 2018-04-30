package com.dahua.oz.t.traffic.main.sort.content;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类页面，右侧Content内容数据转换
 *
 * @author O.z Young
 * @version 2018/4/28
 */

public class SectionDataConvert {


    final List<SectionBean> convert(String json) {
        // 获取每个SetionBean
        final List<SectionBean> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(json).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String title = data.getString("section");

            // 添加title
            final SectionBean sectionTitleBean = new SectionBean(true, title);
            sectionTitleBean.setId(id);
            sectionTitleBean.setIsMore(true);

            // 将TitleBean添加到dataList中
            dataList.add(sectionTitleBean);

            // 获取商品内循环
            final JSONArray goods = data.getJSONArray("goods");
            final int goodsSize = goods.size();
            for (int j = 0; j < goodsSize; j++) {
                final JSONObject good = goods.getJSONObject(j);
                final int goodsId = good.getInteger("goods_id");
                final String goodsThumb = good.getString("goods_thumb");
                final String goodsName = good.getString("goods_name");
                // 获取内容
                final SectionContentItenEntity entity = new SectionContentItenEntity();
                entity.setGoodsId(goodsId);
                entity.setGoodsName(goodsName);
                entity.setGoodsThumb(goodsThumb);
                // 添加内容
                dataList.add(new SectionBean(entity));
            }
            // 商品内容循环结束
        }
        return dataList;
    }

}
