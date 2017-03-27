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

public abstract class PreCallback<T> implements NetCallback<T> {
    private PreIView mView;

    public PreCallback(PreIView view) {
        mView = view;
    }

    @Override
    public void onBefore() {
        if (mView != null) mView.showLoadingUi();
    }

    @Override
    public void onFailure(Exp exp) {
        if (mView != null) mView.showFailureUi();
    }

    @Override
    public void onEnd() {
        if (mView != null) mView.clearPreUi();
    }
}
