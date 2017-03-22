package com.github.huajianjiang.net.util;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/24
 * <br>Email: developer.huajianjiang@gmail.com
 */

public interface Config {
    boolean DEBUG = true;

    String BASE_URL = "https://api.weibo.com/2/";

    int RESULT_CODE_FAILURE = -1;
    int RESULT_CODE_NO_DATA = 3;
    int RESULT_CODE_SUCCESS = 1;
    int RESULT_CODE_TOKEN_EXPIRED = 1024;

}
