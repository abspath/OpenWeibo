package com.abspath.openweibo.presenter;

import com.abspath.openweibo.interfaze.BaseContract;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/27
 * <br>Email: developer.huajianjiang@gmail.com
 */
public abstract class BasePresenter<V> implements BaseContract.BaseIPresenter<V> {
    private static final String TAG = BasePresenter.class.getSimpleName();
    protected V view;

    @Override
    public void bindView(V view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        view = null;
    }
}
