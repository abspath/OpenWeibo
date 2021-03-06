package com.abspath.openweibo.interfaze;

import com.abspath.openweibo.data.model.Weibo;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/27
 * <br>Email: developer.huajianjiang@gmail.com
 */
public interface WeiboContract {

    interface IView extends BaseContract.BaseIView<IPresenter>, PreIView {
        // extra view api
        void showWeibos(Weibo weibos, UpdateType updateType);

        void showRefreshFailureHint();

        void showNoMoreWeiboHint();

        void showExceptionHint(String msg);
    }

    interface IPresenter extends BaseContract.BaseIPresenter<IView> {
        // extra presenter api
        void loadWeibos(UpdateType updateType);
    }
}
