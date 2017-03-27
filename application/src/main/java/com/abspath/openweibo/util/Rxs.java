package com.abspath.openweibo.util;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/27
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class Rxs {
    private static final String TAG = Rxs.class.getSimpleName();

    public static <T> Observable<T> applyBase(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static void unSubscribe(Subscription consumer) {
        if (consumer != null && !consumer.isUnsubscribed()) {
            consumer.unsubscribe();
        }
    }
}
