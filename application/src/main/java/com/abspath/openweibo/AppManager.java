package com.abspath.openweibo;

import android.annotation.SuppressLint;
import android.content.Context;

import com.abspath.openweibo.net.WeiboApi;
import com.github.huajianjiang.net.RetrofitManager;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/27
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class AppManager {
    @SuppressLint("StaticFieldLeak")
    private static AppManager INSTANCE;
    private Context mAppCtxt;
    //微博Auth2授权后的 Token 信息
    private Oauth2AccessToken mAccessToken;
    // OkHttp + Retrofit 初始化和创建API接口封装的管理类
    private RetrofitManager mRetrofitManager;
    // 微博 API 接口描述
    private WeiboApi mApi;

    private AppManager(Context ctxt) {
        mAppCtxt = ctxt.getApplicationContext();
    }

    public static AppManager getInstance(Context ctxt) {
        synchronized (AppManager.class) {
            if (INSTANCE == null) {
                INSTANCE = new AppManager(ctxt);
            }
            return INSTANCE;
        }
    }

    public RetrofitManager getRetrofitManager() {
        if (mRetrofitManager == null) {
            mRetrofitManager = RetrofitManager.getInstance(mAppCtxt);
        }
        return mRetrofitManager;
    }

    public WeiboApi getWeiboService() {
        if (mApi == null) {
            mApi = getRetrofitManager().createService(WeiboApi.class);
        }
        return mApi;
    }

    public Oauth2AccessToken getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(Oauth2AccessToken accessToken) {
        mAccessToken = accessToken;
    }

}
