package com.abspath.openweibo.util;

import java.util.Collection;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/3/28
 * <br>Email: developer.huajianjiang@gmail.com
 */

public class Ifs {

    public static boolean isNullOrEmpty(Object... a) {
        return a == null || a.length == 0;
    }

    public static boolean isNullOrEmpty(Collection c) {
        return c == null || c.isEmpty();
    }
}
