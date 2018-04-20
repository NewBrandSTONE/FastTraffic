package com.dahua.oz.t.core.ui.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * BannerHolder创建者
 *
 * @author O.z Young
 * @version 2018/4/19
 */

public class BannerHolderCreator implements CBViewHolderCreator<BannerImageHolder> {
    @Override
    public BannerImageHolder createHolder() {
        return new BannerImageHolder();
    }
}
