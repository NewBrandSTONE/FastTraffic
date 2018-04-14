package com.dahua.oz.t.traffic.database;

import android.content.Context;

/**
 * 每次GreenDao都会新建一个OpenHelper，这里需要我们自定义一个
 *
 * @author O.z Young
 * @version 2018/4/13
 */

public class ReleaseOpenHelper extends DaoMaster.OpenHelper {
    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }
}
