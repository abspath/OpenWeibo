package com.abspath.openweibo.interfaze;

import com.github.huajianjiang.net.Exp;
import com.github.huajianjiang.net.NetCallback;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/3/27
 * <br>Email: developer.huajianjiang@gmail.com
 */

public abstract class AppNetCallback<T> implements NetCallback<T> {
    private NetIView mView;

    public AppNetCallback(NetIView view) {
        mView = view;
    }

    @Override
    public void onBefore() {
        mView.showLoadingUI();
    }

    @Override
    public void onFailure(Exp exp) {
        mView.showFailureUI();
    }

    @Override
    public void onEnd() {
        mView.clearUI();
    }
}
