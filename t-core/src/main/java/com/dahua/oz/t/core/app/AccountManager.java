package com.dahua.oz.t.core.app;

import com.dahua.oz.t.core.utils.storage.TrafficPreference;

/**
 * 用来管理用户信息
 *
 * @author O.z Young
 * @version 2018/4/14
 */

public class AccountManager {

    private enum SignTag {
        SIGN_TAG
    }

    /**
     * 保存用户登录状态，登录后调用，或者退出登录后调用
     *
     * @param state true-用户已经登录
     */
    public static void setSignTag(boolean state) {
        TrafficPreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    /**
     * 获取用户登录状态
     *
     * @return true-用户已经登录
     */
    public static boolean isSign() {
        return TrafficPreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    /**
     * 检查用户是否登录
     *
     * @param checker
     */
    public static void checkAccount(IUserChecker checker) {
        if (isSign()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }
}
