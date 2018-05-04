package com.dahua.oz.t.core.delegate.web.event;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * <一句话简述功能>
 * <功能详细描述>
 *
 * @author O.z Young
 * @version 2018/5/3
 */

public class TestEvent extends AbstractEvent {
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(), params, Toast.LENGTH_LONG).show();
        if (getAction().equals("test")) {
            final WebView webView = getWebView();
            webView.post(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void run() {
                    webView.evaluateJavascript("nativeCall();", null);
                }
            });
        }
        return null;
    }
}
