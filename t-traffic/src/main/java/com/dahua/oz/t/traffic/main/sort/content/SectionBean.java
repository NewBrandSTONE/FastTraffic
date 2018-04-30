package com.dahua.oz.t.traffic.main.sort.content;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * 分类页面，右侧的数据转换类，因为有所不一样所以不能继承AbsDataConvert
 *
 * @author O.z Young
 * @version 2018/4/28
 */

public class SectionBean extends SectionEntity<SectionContentItenEntity> {

    private boolean mIsMore = false;
    private int mId = -1;

    public SectionBean(SectionContentItenEntity sectionContentItenEntity) {
        super(sectionContentItenEntity);
    }

    public SectionBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public boolean isIsMore() {
        return mIsMore;
    }

    public void setIsMore(boolean isMore) {
        this.mIsMore = mIsMore;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = mId;
    }
}
