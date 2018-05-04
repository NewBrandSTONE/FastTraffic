package com.dahua.oz.t.core.delegate.web.event;

import android.util.Log;

/**
 * 未定义的事件
 *
 * @author O.z Young
 * @version 2018/5/3
 */

public class UndefinedEvent extends AbstractEvent {
    @Override
    public String execute(String params) {
        Log.e("asd", "Undefined Event!!!");
        return null;
    }
}
