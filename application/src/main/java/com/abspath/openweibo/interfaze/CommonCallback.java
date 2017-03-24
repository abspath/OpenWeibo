package com.abspath.openweibo.interfaze;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/3/24
 * <br>Email: developer.huajianjiang@gmail.com
 */
public interface CommonCallback<R, T> {
    R onFinished(T... args);
}
