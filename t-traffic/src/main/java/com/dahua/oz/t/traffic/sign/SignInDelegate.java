package com.dahua.oz.t.traffic.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import com.dahua.oz.t.core.delegate.TrafficDelegate;
import com.dahua.oz.t.core.net.RestClient;
import com.dahua.oz.t.core.net.callback.ISuccess;
import com.dahua.oz.t.traffic.R;
import com.dahua.oz.t.traffic.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录页面
 *
 * @author O.z Young
 * @version 2018/4/13
 */

public class SignInDelegate extends TrafficDelegate {

    @BindView(R2.id.ed_email)
    TextInputEditText mEmailEd;
    @BindView(R2.id.ed_password)
    TextInputEditText mPasswordEd;

    private ISignListener mSignListener = null;

    @OnClick(R2.id.btn_signin)
    void onClickSignIn() {
        if (checkForm()) {
            // 向服务器提交
            RestClient.builder()
                    .url("http://192.168.31.230:8080/hello")
                    .params("", "")
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            Log.d("asd", "onSuccess: " + response);
                            SignHelper.onSignIn(response, mSignListener);
                        }
                    })
                    .build()
                    .post();
        } else {

        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mSignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWechat() {

    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickSignUp() {
        start(new SignUpDelegate());
    }

    private boolean checkForm() {
        boolean ispass = true;
        final String email = mEmailEd.getText().toString();
        final String password = mPasswordEd.getText().toString();

        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailEd.setError("错误的邮件格式");
            ispass = false;
        } else {
            mEmailEd.setError(null);
        }

        if (TextUtils.isEmpty(password) || password.length() < 6) {
            mPasswordEd.setError("请输入至少6位密码");
            ispass = false;
        } else {
            mPasswordEd.setError(null);
        }

        return ispass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_signin;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
