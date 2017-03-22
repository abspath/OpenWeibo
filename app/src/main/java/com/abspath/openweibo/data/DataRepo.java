package com.abspath.openweibo.data;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/3/1
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class DataRepo<T> implements DataSource<T> {
    private static final String TAG = DataRepo.class.getSimpleName();

    private T mCachedData = null;

    private boolean mCacheIsDirty = false;

    public void cacheData(T data) {
        mCachedData = data;
    }

    public boolean hasCachedData() {
        return mCachedData != null;
    }

    @Override
    public void getData(GetDataCallback<T> callback) {
        if (callback == null) return;
        final T data = mCachedData;
        if (data != null && !mCacheIsDirty) {
            callback.onDataLoaded(data);
        } else {
            callback.onDataNotAvailable();
        }
    }

    @Override
    public void refreshData() {
        mCacheIsDirty = true;
    }
}
