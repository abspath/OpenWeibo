package com.github.huajianjiang.net;

import com.github.huajianjiang.net.util.Config;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/24
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class Exp extends RuntimeException {
    private static final String TAG = Exp.class.getSimpleName();
    /**
     * 错误码
     */
    private int errorCode;
    /**
     * 错误码对应的错误消息
     */
    private String errorMsg;

    public Exp(int errorCodee, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCodee;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean ok() {
        return errorCode == Config.RESULT_CODE_SUCCESS || errorCode == Config.RESULT_CODE_NO_DATA;
    }

    public boolean tokenExpired() {
        return errorCode == Config.RESULT_CODE_TOKEN_EXPIRED;
    }

}
