package com.abspath.openweibo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.abspath.openweibo.R;
import com.abspath.openweibo.util.Constant;
import com.abspath.openweibo.util.MsgUtils;
import com.abspath.openweibo.view.fragment.MainFragment;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

public class MainActivity extends BaseActivity {

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
        public void onComplete(Bundle bundle) {

        }

        @Override
        public void onWeiboException(WeiboException e) {
            MsgUtils.longToast(getApplicationContext(),
                    String.format(getString(R.string.auth_exception), e.getMessage()));
        }

        @Override
        public void onCancel() {
            MsgUtils.longToast(getApplicationContext(), getString(R.string.auth_canceled));
        }
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
}

