package com.abspath.openweibo.interfaze;

import android.content.Context;

import com.abspath.openweibo.data.model.PubTimeline;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/27
 * <br>Email: developer.huajianjiang@gmail.com
 */

public interface MovieTop250Contract {

    interface IView extends BaseContract.BaseIView<IPresenter>, NetIView {
        // extra view api
        void showMovies(PubTimeline top250);

        boolean isActive();

        Context getCtxt();
    }

    interface IPresenter extends BaseContract.BaseIPresenter<IView> {
        int TYPE_REFRESH = 0;
        int TYPE_LOAD_MORE = 1;

        boolean needRestart();

        // extra presenter api
        void loadMovies(boolean forceUpdate, int updateType);
    }
}
