package com.dahua.oz.t.traffic.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * @author O.z Young
 * @version 2018/4/13
 */

public class DataBaseManager {

    private DaoSession mDaoSession = null;
    private UserProfileDao mDao = null;

    private DataBaseManager() {

    }

    public DataBaseManager init(Context context) {
        initDao(context);
        return this;
    }

    public final UserProfileDao getDao() {
        return mDao;
    }

    private static class Holder {
        private static final DataBaseManager INSTANCE = new DataBaseManager();
    }

    public static DataBaseManager getInstance() {
        return Holder.INSTANCE;
    }

    private void initDao(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "fast_traffic.db");
        final Database writableDb = helper.getWritableDb();
        mDaoSession = new DaoMaster(writableDb).newSession();
        mDao = mDaoSession.getUserProfileDao();
    }

}
