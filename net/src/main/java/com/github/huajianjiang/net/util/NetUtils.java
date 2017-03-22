package com.github.huajianjiang.net.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/24
 * <br>Email: developer.huajianjiang@gmail.com
 */
public final class NetUtils {
    private static final String TAG = NetUtils.class.getSimpleName();
    private NetUtils(){}

    public static boolean checkNetwork(Context context) {
        if (context == null) return false;
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ani = cm.getActiveNetworkInfo();
        return ani != null && ani.isConnected();
    }

    public static boolean checkWifiConnection(Context context) {
        if (context == null) return false;
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ani = cm.getActiveNetworkInfo();
        return ani != null && ani.isConnected() && ani.getType() == ConnectivityManager.TYPE_WIFI;
    }
}
