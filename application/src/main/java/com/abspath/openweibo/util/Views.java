package com.abspath.openweibo.util;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/3/23
 * <br>Email: developer.huajianjiang@gmail.com
 */

public class Views {

    private Views(){}

    @SuppressWarnings("unchecked")
    public static <T extends View> T find(@NonNull View parent, int id) {
        return (T) parent.findViewById(id);
    }

    @SuppressWarnings("unchecked")
    public static <T extends View> T find(@NonNull Activity activity, int id) {
        return (T) activity.findViewById(id);
    }

    @SuppressWarnings("unchecked")
    public static <T extends View> T find(@NonNull Fragment fragment, int id) {
        View root = fragment.getView();
        return root != null ? (T) root.findViewById(id) : null;
    }

}
