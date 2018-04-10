package com.dahua.oz.t.core.net.download;

import android.os.AsyncTask;

import com.dahua.oz.t.core.net.RestCreator;
import com.dahua.oz.t.core.net.callback.IError;
import com.dahua.oz.t.core.net.callback.IFailure;
import com.dahua.oz.t.core.net.callback.IRequest;
import com.dahua.oz.t.core.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 用来处理下载的Handler
 *
 * @author O.z Young
 * @version 2018/4/10
 */

public class DownLoadHandler {

    private final String URL;
    private final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String FILE_NAME;

    public DownLoadHandler(String url,
                           IRequest request,
                           ISuccess success,
                           IFailure failure,
                           IError error,
                           String downloadDir,
                           String extension,
                           String fileName) {
        this.URL = url;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.FILE_NAME = fileName;
    }

    public final void handleDonwload() {
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        RestCreator.getRestService().download(URL, PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            final SaveFileTask task = new SaveFileTask(REQUEST, SUCCESS);
                            ResponseBody body = response.body();
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, DOWNLOAD_DIR, EXTENSION, FILE_NAME, body);

                            // 这里一定要注意判断，否则文件下载不全
                            if (task.isCancelled()) {
                                if (REQUEST != null) {
                                    REQUEST.onRequestEnd();
                                }
                            }
                        } else {
                            ERROR.onError(response.code(), response.message());
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (FAILURE != null) {
                            FAILURE.onFailure();
                        }
                    }
                });
    }

}
