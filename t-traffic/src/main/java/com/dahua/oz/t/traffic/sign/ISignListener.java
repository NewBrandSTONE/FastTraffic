package com.dahua.oz.t.traffic.sign;

/**
 * 登录回调接口
 *
 * @author O.z Young
 * @version 2018/4/14
 */

public interface ISignListener {

    /**
     * 登录成功的回调
     */
    void onSignInSuccess();

    /**
     * 注册成功的回调
     */
    void onSignUpSuccess();

}
