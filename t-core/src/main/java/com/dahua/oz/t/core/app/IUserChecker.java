package com.dahua.oz.t.core.app;

/**
 * 检查用户是否登录的宏观接口
 *
 * @author O.z Young
 * @version 2018/4/14
 */

public interface IUserChecker {

    /**
     * 用户已经登录
     */
    void onSignIn();

    /**
     * 用户没有登录
     */
    void onNotSignIn();

}
