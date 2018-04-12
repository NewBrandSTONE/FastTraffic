package com.dahua.oz.t.core.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.dahua.oz.t.core.app.Traffic;
import com.dahua.oz.t.core.net.callback.IRequest;
import com.dahua.oz.t.core.net.callback.ISuccess;
import com.dahua.oz.t.core.utils.file.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * 处理文件的流写入
 *
 * @author O.z Young
 * @version 2018/4/10
 */

public class SaveFileTask extends AsyncTask<Object, Void, File> {


    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private static final String APK_EXTENSION = "apk";

    public SaveFileTask(IRequest request, ISuccess success) {
        this.REQUEST = request;
        this.SUCCESS = success;
    }

    @Override
    protected File doInBackground(Object... objects) {
        String downloadDir = (String) objects[0];
        String extension = (String) objects[1];
        String fileName = (String) objects[2];
        ResponseBody body = (ResponseBody) objects[3];
        InputStream ins = body.byteStream();

        if (TextUtils.isEmpty(downloadDir)) {
            downloadDir = "download_dir";
        }

        if (TextUtils.isEmpty(extension)) {
            extension = "";
        }

        if (TextUtils.isEmpty(fileName)) {
            return FileUtil.writeToDisk(ins, downloadDir, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(ins, downloadDir, fileName);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        // 执行完异步回到主线程
        if (SUCCESS != null) {
            SUCCESS.onSuccess(file.getAbsolutePath());
        }

        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }

        autoInstallAPK(file);
    }

    private void autoInstallAPK(File file) {
        if (APK_EXTENSION.equals(FileUtil.getExtension(file.getPath()))) {
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Traffic.getApplicationContext().startActivity(install);
        }
    }
}
