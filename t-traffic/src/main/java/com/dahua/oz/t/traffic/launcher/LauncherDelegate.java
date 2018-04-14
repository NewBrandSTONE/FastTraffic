package com.dahua.oz.t.traffic.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.dahua.oz.t.core.app.AccountManager;
import com.dahua.oz.t.core.app.IUserChecker;
import com.dahua.oz.t.core.delegate.TrafficDelegate;
import com.dahua.oz.t.core.ui.launcher.ILauncherListener;
import com.dahua.oz.t.core.ui.launcher.LauncherFinishTag;
import com.dahua.oz.t.core.ui.launcher.ScrollLauncherTag;
import com.dahua.oz.t.core.utils.storage.TrafficPreference;
import com.dahua.oz.t.core.utils.timer.BaseTimerTask;
import com.dahua.oz.t.core.utils.timer.ITimerListener;
import com.dahua.oz.t.traffic.R;
import com.dahua.oz.t.traffic.R2;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录广告页面
 *
 * @author O.z Young
 * @version 2018/4/12
 */

public class LauncherDelegate extends TrafficDelegate implements ITimerListener {

    private Timer mTimer;
    private int mCount = 5;
    private ILauncherListener mLauncherListener = null;

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mLauncherTv = null;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {
        onTimerStop();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mLauncherListener = (ILauncherListener) activity;
        }
    }

    /**
     * 当结束倒计时或点击跳过的时候
     */
    private void onTimerStop() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    private void initTimer() {
        // TODO: 2018/4/12 替换新方法
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mLauncherTv != null) {
                    mLauncherTv.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        onTimerStop();
                    }
                }
            }
        });
    }

    /**
     * 判断是否显示滑动启动页面
     */
    private void checkIsShowScroll() {
        if (!TrafficPreference.getAppFlag(ScrollLauncherTag.HAS_LAUNCH_APP.name())) {
            // 第一次登录
            start(new LauncherScrollDelegate(), SINGLETASK);
        } else {
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
