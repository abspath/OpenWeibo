package com.abspath.openweibo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.abspath.openweibo.R;
import com.abspath.openweibo.util.Constant;
import com.abspath.openweibo.util.Logger;
import com.abspath.openweibo.util.MsgUtils;
import com.abspath.openweibo.util.RxUtils;
import com.abspath.openweibo.view.fragment.MainFragment;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

import rx.Observable;
import rx.functions.Action0;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private AuthHelper mAuthHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuthHelper = new AuthHelper();
        mAuthHelper.checkAuth();
    }

    @Override
    public Fragment getFragment() {
        return new MainFragment();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // SSO 授权回调
        // 重要：发起 SSO 登陆的 Activity 必须重写 onActivityResults
        final SsoHandler ssoHandler = mAuthHelper.mSsoHandler;
        if (ssoHandler != null) {
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    private class AuthHelper implements WeiboAuthListener {
        AuthInfo mAuthInfo;
        Oauth2AccessToken mAccessToken;
        SsoHandler mSsoHandler;

        private void checkAuth() {
            // 从 SharedPreferences 中读取上次已保存好 AccessToken 等信息，
            // 第一次启动本应用，AccessToken 不可用
            mAccessToken = AccessTokenKeeper.readAccessToken(getApplicationContext());
            if (mAccessToken.isSessionValid()) return;
            mAuthInfo = new AuthInfo(getApplicationContext(), Constant.APP_KEY,
                    Constant.REDIRECT_URL, Constant.SCOPE);
            mSsoHandler = new SsoHandler(MainActivity.this, mAuthInfo);
            mSsoHandler.authorize(this);
        }

        @Override
        public void onComplete(final Bundle bundle) {
            Logger.e(TAG, "onComplete>>>>>>>>>>>>" + Thread.currentThread());
            RxUtils.applyBase(Observable.empty()).doOnCompleted(new Action0() {
                @Override
                public void call() {
                    Logger.e(TAG, "call>>>>>>>>>>>>" + Thread.currentThread());
                    mAccessToken = Oauth2AccessToken.parseAccessToken(bundle);
                    if (mAccessToken.isSessionValid()) {
                        // 保存 Token 到 SharedPreferences
                        AccessTokenKeeper.writeAccessToken(getApplicationContext(), mAccessToken);
                        MsgUtils.longToast(getApplicationContext(),
                                getString(R.string.auth_success));
                    } else {
                        // 以下几种情况，您会收到 Code：
                        // 1. 当您未在平台上注册的应用程序的包名与签名时；
                        // 2. 当您注册的应用程序包名与签名不正确时；
                        // 3. 当您在平台上注册的包名和签名与您当前测试的应用的包名和签名不匹配时。
                        String code = bundle.getString("code");
                        String message = getString(R.string.auth_failed);
                        if (!TextUtils.isEmpty(code)) {
                            message = message + "\nObtained the code: " + code;
                        }
                        MsgUtils.longToast(getApplicationContext(), message);
                    }
                }
            }).subscribe();
        }

        @Override
        public void onWeiboException(WeiboException e) {
            Logger.e(TAG, "onWeiboException>>>>>>>>>>>>" + e.getMessage());
            MsgUtils.longToast(getApplicationContext(),
                    String.format(getString(R.string.auth_exception), e.getMessage()));
        }

        @Override
        public void onCancel() {
            Logger.e(TAG, "onCancel>>>>>>>>>>>>");
            MsgUtils.longToast(getApplicationContext(), getString(R.string.auth_canceled));
        }
    }
}

