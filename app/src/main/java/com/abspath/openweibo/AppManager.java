package com.abspath.openweibo;

import android.annotation.SuppressLint;
import android.content.Context;

import com.abspath.openweibo.net.Api;
import com.github.huajianjiang.net.RetrofitManager;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/27
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class AppManager {
    @SuppressLint("StaticFieldLeak")
    private static AppManager instance;

    private Context mAppCtxt;

    private RetrofitManager mRetrofitManager;
    private Api mApi;

    private AppManager(Context ctxt) {
        mAppCtxt = ctxt.getApplicationContext();
    }

    public static AppManager getInstance(Context ctxt) {
        synchronized (AppManager.class) {
            if (instance == null) {
                instance = new AppManager(ctxt);
            }
            return instance;
        }
    }

    public RetrofitManager getRetrofitManager() {
        if (mRetrofitManager == null) {
            mRetrofitManager = RetrofitManager.getInstance(mAppCtxt);
        }
        return mRetrofitManager;
    }

    public Api getAppService() {
        if (mApi == null) {
            mApi = getRetrofitManager().createService(Api.class);
        }
        return mApi;
    }
}
