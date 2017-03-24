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

    interface IView extends BaseContract.BaseIView<IPresenter>, NetIView {
        // extra view api
        void showWeiboList(Weibo weibos);

        boolean isActive();
    }

    interface IPresenter extends BaseContract.BaseIPresenter<IView> {
        // extra presenter api
        void loadMovies(boolean forceUpdate, UpdateType updateType);
    }
}
