package com.dahua.oz.fasttraffic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.dahua.oz.t.core.activities.AbstractProxyActivity;
import com.dahua.oz.t.core.app.ConfigKeys;
import com.dahua.oz.t.core.app.Traffic;
import com.dahua.oz.t.core.delegate.TrafficDelegate;
import com.dahua.oz.t.core.ui.launcher.ILauncherListener;
import com.dahua.oz.t.core.ui.launcher.LauncherFinishTag;
import com.dahua.oz.t.traffic.launcher.LauncherDelegate;
import com.dahua.oz.t.traffic.main.TrafficBottomDelegate;
import com.dahua.oz.t.traffic.sign.ISignListener;
import com.dahua.oz.t.traffic.sign.SignInDelegate;

/**
 * 全局唯一的Activity
 *
 * @author Oz. Young
 */
public class ExampleActivity extends AbstractProxyActivity
        implements ISignListener, ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Traffic.getConfigurations().put(ConfigKeys.WX_CALLBACK_ACTIVITY.name(), this);
    }

    @Override
    public TrafficDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(LauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                // 用户登录了如何回调
                Toast.makeText(this, "启动结束，用户登录了", Toast.LENGTH_LONG).show();
                startWithPop(new TrafficBottomDelegate());
                break;
            case NOT_SIGNED:
                // 用户没有登录如何回调
                Toast.makeText(this, "启动结束，用户没登录", Toast.LENGTH_LONG).show();
                startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
