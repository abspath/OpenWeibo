package com.github.huajianjiang.net.rxjava;

import com.github.huajianjiang.net.Exp;
import com.github.huajianjiang.net.NetCallback;
import com.github.huajianjiang.net.util.ErrorParser;
import com.github.huajianjiang.net.util.Logger;

import rx.Subscriber;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/24
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class MySubscriber<T> extends Subscriber<T> {
    private static final String TAG = MySubscriber.class.getSimpleName();

    private NetCallback<T> mCallback;

    public MySubscriber(NetCallback<T> callback) {
        this.mCallback = callback;
    }

    @Override
    public void onStart() {
        Logger.e(TAG, "onStart===>" + Thread.currentThread().getName());
        if (mCallback != null) mCallback.onBefore();
    }

    @Override
    public void onNext(T echo) {
        Logger.e(TAG, "onNext===>");
        if (mCallback != null) {
            mCallback.onSuccess(echo);
        }
    }

    @Override
    public void onCompleted() {
        Logger.e(TAG, "onCompleted===>");
        if (mCallback != null) mCallback.onEnd();
    }

    @Override
    public void onError(Throwable t) {
        Exp exp = ErrorParser.parseError(t);
        Logger.e(TAG, "exp===>" + t.getMessage() + ",,," + exp.getErrorMsg());
        if (mCallback != null) {
            mCallback.onEnd();
            mCallback.onFailure(exp);
        }
    }
}
