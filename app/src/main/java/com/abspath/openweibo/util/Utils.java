package com.abspath.openweibo.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/28
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class Utils {
    private static final String TAG = Utils.class.getSimpleName();

    public static void shortToast(Context ctxt, int resId) {
        if (ctxt == null) return;
        Toast.makeText(ctxt, resId, Toast.LENGTH_SHORT).show();
    }

    public static void shortToast(Context ctxt, CharSequence txt) {
        if (ctxt == null) return;
        Toast.makeText(ctxt, txt, Toast.LENGTH_SHORT).show();
    }

    public static void longToast(Context ctxt, int resId) {
        if (ctxt == null) return;
        Toast.makeText(ctxt, resId, Toast.LENGTH_LONG).show();
    }

    public static void longToast(Context ctxt, CharSequence txt) {
        if (ctxt == null) return;
        Toast.makeText(ctxt, txt, Toast.LENGTH_LONG).show();
    }
}
