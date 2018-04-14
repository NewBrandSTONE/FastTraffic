package com.dahua.oz.t.traffic.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.dahua.oz.t.core.app.AccountManager;
import com.dahua.oz.t.core.app.IUserChecker;
import com.dahua.oz.t.core.delegate.TrafficDelegate;
import com.dahua.oz.t.core.ui.launcher.ILauncherListener;
import com.dahua.oz.t.core.ui.launcher.LauncherFinishTag;
import com.dahua.oz.t.core.ui.launcher.LauncherHolderCreator;
import com.dahua.oz.t.core.ui.launcher.ScrollLauncherTag;
import com.dahua.oz.t.core.utils.storage.TrafficPreference;
import com.dahua.oz.t.traffic.R;

import java.util.ArrayList;

/**
 * 可以滚动的广告页面
 *
 * @author O.z Young
 * @version 2018/4/12
 */

public class LauncherScrollDelegate extends TrafficDelegate implements OnItemClickListener {

    /**
     * 保存可滚动的广告页面，实际上是一个图片
     */
    private ConvenientBanner<Integer> mBanner;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();
    private ILauncherListener mLauncherListener = null;

    private void initBanner() {
        INTEGERS.add(R.drawable.soild_red);
        INTEGERS.add(R.drawable.soild_blue);
        mBanner.setPages(new LauncherHolderCreator(), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mLauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        mBanner = new ConvenientBanner<>(getContext());
        return mBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        // 如果点击的是最后一个
        if (position == INTEGERS.size() - 1) {
            TrafficPreference.setAppFlag(ScrollLauncherTag.HAS_LAUNCH_APP.name(), true);
            // 检查用户是否登陆APP
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mLauncherListener != null) {
                        mLauncherListener.onLauncherFinish(LauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mLauncherListener != null) {
                        mLauncherListener.onLauncherFinish(LauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }
}
