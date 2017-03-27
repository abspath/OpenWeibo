package com.abspath.openweibo.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.abspath.openweibo.AppManager;
import com.abspath.openweibo.R;
import com.abspath.openweibo.view.activity.MainActivity;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

import java.lang.ref.WeakReference;

import rx.Observable;
import rx.functions.Action0;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/3/24
 * <br>Email: developer.huajianjiang@gmail.com
 */
public final class AuthHelper implements WeiboAuthListener {
    private static final String TAG = AuthHelper.class.getSimpleName();
    private WeakReference<Activity> mActivityRef;
    private SsoHandler mSsoHandler;

    public AuthHelper(Activity activity) {
        mActivityRef = new WeakReference<>(activity);
    }

    public SsoHandler getSsoHandler() {
        return mSsoHandler;
    }

    public void checkAuth() {
        Activity acti = mActivityRef.get();
        if (acti == null) return;
        // 从 SharedPreferences 中读取上次已保存好 AccessToken 等信息，
        // 第一次启动本应用，AccessToken 不可用
        Oauth2AccessToken mAccessToken = AccessTokenKeeper
                .readAccessToken(acti.getApplicationContext());
        if (mAccessToken != null && mAccessToken.isSessionValid()) {
            AppManager.getInstance(acti).setAccessToken(mAccessToken);
            acti.startActivity(new Intent(acti, MainActivity.class));
            acti.finish();
        } else {
            doAuth();
        }
    }

    public void doAuth() {
        Activity acti = mActivityRef.get();
        if (acti == null) return;
        AuthInfo mAuthInfo = new AuthInfo(acti.getApplicationContext(), Constants.APP_KEY,
                Constants.REDIRECT_URL, Constants.SCOPE);
        mSsoHandler = new SsoHandler(acti, mAuthInfo);
        mSsoHandler.authorize(this);
    }

    @Override
    public void onComplete(final Bundle bundle) {
        Logger.e(TAG, "onComplete>>>>>>>>>>>>" + Thread.currentThread());
        Rxs.applyBase(Observable.empty()).doOnCompleted(new Action0() {
            @Override
            public void call() {
                Logger.e(TAG, "call>>>>>>>>>>>>" + Thread.currentThread());
                Activity acti = mActivityRef.get();
                if (acti == null) return;
                Oauth2AccessToken mAccessToken = Oauth2AccessToken.parseAccessToken(bundle);
                if (mAccessToken.isSessionValid()) {
                    AppManager.getInstance(acti).setAccessToken(mAccessToken);
                    // 保存 Token 到 SharedPreferences
                    AccessTokenKeeper.writeAccessToken(acti.getApplicationContext(), mAccessToken);
                    Msgs.longToast(acti.getApplicationContext(),
                            acti.getString(R.string.auth_success));
                    acti.startActivity(new Intent(acti, MainActivity.class));
                    acti.finish();
                } else {
                    // 以下几种情况，您会收到 Code：
                    // 1. 当您未在平台上注册的应用程序的包名与签名时；
                    // 2. 当您注册的应用程序包名与签名不正确时；
                    // 3. 当您在平台上注册的包名和签名与您当前测试的应用的包名和签名不匹配时。
                    String code = bundle.getString("code");
                    String message = acti.getString(R.string.auth_failed);
                    if (!TextUtils.isEmpty(code)) {
                        message = message + "\nObtained the code: " + code;
                    }
                    Msgs.longToast(acti.getApplicationContext(), message);
                }
            }
        }).subscribe();
    }

    @Override
    public void onWeiboException(WeiboException e) {
        Activity acti = mActivityRef.get();
        if (acti == null) return;
        Msgs.longToast(acti.getApplicationContext(),
                String.format(acti.getString(R.string.auth_exception), e.getMessage()));
    }

    @Override
    public void onCancel() {
        Activity acti = mActivityRef.get();
        if (acti == null) return;
        Msgs.longToast(acti.getApplicationContext(), acti.getString(R.string.auth_canceled));
    }
}
