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
 * 注册页面
 *
 * @author O.z Young
 * @version 2018/4/12
 */

public class SignUpDelegate extends TrafficDelegate {

    @BindView(R2.id.ed_name)
    TextInputEditText mNameEd;
    @BindView(R2.id.ed_email)
    TextInputEditText mEmailEd;
    @BindView(R2.id.ed_phone)
    TextInputEditText mPhoneEd;
    @BindView(R2.id.ed_password)
    TextInputEditText mPasswordEd;
    @BindView(R2.id.ed_repassword)
    TextInputEditText mRepasswordEd;

    private ISignListener mSignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mSignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_signup)
    void onClickSignUp() {
        if (checkForm()) {
            // 向服务器提交
            RestClient.builder()
                    .url("http://192.168.31.230:8080/hello")
                    .params("", "")
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            Log.d("asd", "onSuccess: " + response);
                            SignHelper.onSignUp(response, mSignListener);
                        }
                    })
                    .build()
                    .post();
        }
    }


    private boolean checkForm() {
        final String name = mNameEd.getText().toString();
        final String email = mEmailEd.getText().toString();
        final String phone = mPhoneEd.getText().toString();
        final String password = mPasswordEd.getText().toString();
        final String repassword = mRepasswordEd.getText().toString();

        boolean ispass = true;

        if (TextUtils.isEmpty(name)) {
            mNameEd.setError("请输入姓名");
            ispass = false;
        } else {
            mNameEd.setError(null);
        }

        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailEd.setError("错误的邮件格式");
            ispass = false;
        } else {
            mEmailEd.setError(null);
        }

        if (TextUtils.isEmpty(phone) || phone.length() != 11) {
            mPhoneEd.setError("手机号应该为11位");
            ispass = false;
        } else {
            mPhoneEd.setError(null);
        }

        if (TextUtils.isEmpty(password) || password.length() < 6) {
            mPasswordEd.setError("请输入至少6位密码");
            ispass = false;
        } else {
            mPasswordEd.setError(null);
        }

        if (TextUtils.isEmpty(repassword) || repassword.length() < 6 || !repassword.equals(password)) {
            mRepasswordEd.setError("两次输入的密码不一致");
            ispass = false;
        } else {
            mRepasswordEd.setError(null);
        }
        return ispass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_signup;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
