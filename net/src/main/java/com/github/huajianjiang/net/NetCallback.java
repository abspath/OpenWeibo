package com.github.huajianjiang.net;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/24
 * <br>Email: developer.huajianjiang@gmail.com
 */

public interface NetCallback<T> {
    void onBefore();

    void onSuccess(T result);

    void onFailure(Exp exp);

    void onEnd();
}
