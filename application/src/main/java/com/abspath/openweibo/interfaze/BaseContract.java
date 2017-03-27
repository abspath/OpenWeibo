package com.abspath.openweibo.interfaze;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/27
 * <br>Email: developer.huajianjiang@gmail.com
 */
public interface BaseContract {

    interface BaseIView<P> {
        void bindPresenter(P presenter);
    }

    interface BaseIPresenter<V> {
        void bindView(V view);
        void start();
        void stop();
        void unbindView();
    }
}
