package com.dahua.oz.t.core.ui.recycler;

import java.util.ArrayList;

/**
 * 数据转换的约束
 *
 * @author O.z Young
 * @version 2018/4/18
 */

public abstract class AbstractDataConverter {

    protected final ArrayList<MultipleItemEntity> ENTITYIES = new ArrayList<>();
    private String mJsonData = null;

    /**
     * 返回数组
     *
     * @return 数组
     */
    public abstract ArrayList<MultipleItemEntity> convert();

    public AbstractDataConverter setJsonData(String json) {
        this.mJsonData = json;
        return this;
    }

    protected String getJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("DATA IS NULL");
        }
        return mJsonData;
    }
}
