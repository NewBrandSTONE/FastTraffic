package com.dahua.oz.t.core.utils.timer;

import java.util.TimerTask;

/**
 * 倒数计时器
 *
 * @author O.z Young
 * @version 2018/4/12
 */

public class BaseTimerTask extends TimerTask {

    private ITimerListener mTimerListener;

    public BaseTimerTask(ITimerListener mTimerListener) {
        this.mTimerListener = mTimerListener;
    }

    @Override
    public void run() {
        if (mTimerListener != null) {
            mTimerListener.onTimer();
        }
    }
}
