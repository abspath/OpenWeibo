package com.abspath.openweibo.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.abspath.openweibo.AppManager;
import com.abspath.openweibo.R;
import com.abspath.openweibo.view.activity.SignInActivity;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/28
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class Apps {
    private static final String TAG = Apps.class.getSimpleName();

    public static boolean checkLoginStatus(Context ctxt, boolean force) {
        Oauth2AccessToken accessToken = AppManager.getInstance().getAccessToken();
        boolean isValid = accessToken != null && accessToken.isSessionValid();
        if (!isValid || force) {
            Intent signInIntent = new Intent(ctxt, SignInActivity.class);
            signInIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            ctxt.startActivity(signInIntent);
        }
        return isValid;
    }

    public static String getAccessToken() {
        Oauth2AccessToken accessToken = AppManager.getInstance().getAccessToken();
        return accessToken != null ? accessToken.getToken() : "";
    }

    public static void showOfflineSnackbar(Activity parent, final AuthHelper authHelper) {
        final Snackbar snackbar = Snackbar.make(parent.getWindow().getDecorView().getRootView()
                .findViewById(android.R.id.content), R.string.offline, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.weibo_sign_in, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        snackbar.show();
    }

}
