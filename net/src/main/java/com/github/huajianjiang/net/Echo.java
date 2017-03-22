package com.github.huajianjiang.net;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/24
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class Echo<T> {
    private static final String TAG = Echo.class.getSimpleName();

    private int code;

    private String msg;

    private T result;

    public int getKey() {
        return code;
    }

    public void setKey(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
