package com.dahua.oz.fasttraffic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dahua.oz.t.core.callback.IError;
import com.dahua.oz.t.core.callback.IFailure;
import com.dahua.oz.t.core.callback.ISuccess;
import com.dahua.oz.t.core.delegate.TrafficDelegate;
import com.dahua.oz.t.core.net.RestClient;

/**
 * @author gaohuang_yangzi@dahuatech.com
 * @version 2018/4/3
 */

public class ExampleDelegate extends TrafficDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    private void testRestClient() {
        RestClient.builder()
                .url("")
                .params("", "")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String message) {

                    }
                })
                .build();
    }
}
