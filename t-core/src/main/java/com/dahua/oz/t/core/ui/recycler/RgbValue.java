package com.dahua.oz.t.core.ui.recycler;

import com.google.auto.value.AutoValue;

/**
 * 存储红色、蓝色、绿色
 *
 * @author O.z Young
 * @version 2018/4/21
 */
@AutoValue
public abstract class RgbValue {
    public abstract int red();

    public abstract int green();

    public abstract int blue();

    public static RgbValue create(int red, int green, int blue) {
        return new AutoValue_RgbValue(red, green, blue);
    }
}
