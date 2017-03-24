package com.abspath.openweibo.interfaze;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/3/8
 * <br>Email: developer.huajianjiang@gmail.com
 */

public interface NetIView {

    int TYPE_REFRESH = 0;

    int TYPE_LOAD_MORE = 1;

    void showLoadingUI();

    void showSuccessUI();

    void showFailureUI();

    void clearUI();
}
