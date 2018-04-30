package com.dahua.oz.t.traffic.main.sort.content;

/**
 * Sort_Data中，某一种分类下的详细类别
 *
 * @author O.z Young
 * @version 2018/4/28
 */

public class SectionContentItenEntity {

    private int mGoodsId;
    private String mGoodsName;
    private String mGoodsThumb;

    public int getGoodsId() {
        return mGoodsId;
    }

    public void setGoodsId(int mGoodsId) {
        this.mGoodsId = mGoodsId;
    }

    public String getGoodsName() {
        return mGoodsName;
    }

    public void setGoodsName(String mGoodsName) {
        this.mGoodsName = mGoodsName;
    }

    public String getGoodsThumb() {
        return mGoodsThumb;
    }

    public void setGoodsThumb(String mGoodsThumb) {
        this.mGoodsThumb = mGoodsThumb;
    }
}
