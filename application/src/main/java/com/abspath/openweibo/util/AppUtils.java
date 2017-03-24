package com.abspath.openweibo.util;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.abspath.openweibo.R;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/28
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class AppUtils {
    private static final String TAG = AppUtils.class.getSimpleName();

    public static void showOfflineSnackbar(Activity parent, final AuthHelper authHelper) {
        final Snackbar snackbar = Snackbar.make(parent.getWindow().getDecorView().getRootView()
                .findViewById(android.R.id.content), R.string.offline, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.weibo_sign_in, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        authHelper.doAuth();
                    }
                });
        snackbar.show();
    }

}
