package com.github.huajianjiang.net.util;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/24
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class Util {
    private static final String TAG = Util.class.getSimpleName();

    public static <T> T checkNotNull(T clazzOfT, String eMsg) {
        if (clazzOfT == null) throw new NullPointerException(eMsg);
        return clazzOfT;
    }
}
