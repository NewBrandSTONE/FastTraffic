package com.dahua.oz.t.core.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.dahua.oz.t.core.app.Traffic;

/**
 * 测量工具类
 *
 * @author O.z Young
 * @version 2018/4/8
 */

public class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Traffic.getApplicationContet().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Traffic.getApplicationContet().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }

}
