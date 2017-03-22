package com.abspath.openweibo.data;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/28
 * <br>Email: developer.huajianjiang@gmail.com
 */
public interface DataSource<T> {

    interface GetDataCallback<T> {

        void onDataLoaded(T data);

        void onDataNotAvailable();
    }

    void getData(GetDataCallback<T> callback);

    void refreshData();
}
