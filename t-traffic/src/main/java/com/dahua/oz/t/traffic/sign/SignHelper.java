package com.dahua.oz.t.traffic.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dahua.oz.t.core.app.AccountManager;
import com.dahua.oz.t.traffic.database.DataBaseManager;
import com.dahua.oz.t.traffic.database.UserProfile;

/**
 * 登录和注册的帮助类
 *
 * @author O.z Young
 * @version 2018/4/13
 */

public class SignHelper {

    /**
     * 当用户注册的时候调用
     *
     * @param response
     * @param signListener
     */
    public static void onSignUp(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile userProfile = new UserProfile(userId, name, avatar, gender, address);
        DataBaseManager.getInstance().getDao().insert(userProfile);

        // 已经注册成功
        AccountManager.setSignTag(true);
        // 直接登录啦
        signListener.onSignUpSuccess();
    }

    /**
     * 当用户登录的时候调用
     *
     * @param response
     * @param signListener
     */
    public static void onSignIn(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile userProfile = new UserProfile(userId, name, avatar, gender, address);
        DataBaseManager.getInstance().getDao().insert(userProfile);

        // 已经注册成功
        AccountManager.setSignTag(true);
        // 直接登录啦
        signListener.onSignInSuccess();
    }

}
