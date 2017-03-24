package com.abspath.openweibo.interfaze;

import android.content.Context;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/27
 * <br>Email: developer.huajianjiang@gmail.com
 */

public interface BaseContract {

    interface BaseIView<P> {
        void onBindPresenter(P presenter);
        Context getCtxt();
    }

    interface BaseIPresenter<V> {
        void onBindView(V view);
        void onStart();
        void onStop();
        void onUnbindView();
    }
}
