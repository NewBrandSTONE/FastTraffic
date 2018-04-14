package com.dahua.oz.t.core.ui.launcher;

/**
 * 登录监听
 *
 * @author O.z Young
 * @version 2018/4/14
 */

public interface ILauncherListener {

    /**
     * 登录完成后的监听函数
     *
     * @param tag 是什么类型的登录完成，Signed或者Not_Signed
     */
    void onLauncherFinish(LauncherFinishTag tag);

}
