package com.github.huajianjiang.net.okhttp;

import android.content.Context;

import com.github.huajianjiang.net.Exp;
import com.github.huajianjiang.net.R;
import com.github.huajianjiang.net.util.ErrorParser;
import com.github.huajianjiang.net.util.Logger;
import com.github.huajianjiang.net.util.NetUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Title: 网络请求前的 检查拦截器
 * <p>Description: 比如请求前检查网络连接是否正常,如果不正常就短路请求
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/23
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class CheckingInterceptor implements Interceptor {
    private static final String TAG = CheckingInterceptor.class.getSimpleName();
    private Context mCtxt;

    public CheckingInterceptor(Context ctxt) {
        this.mCtxt = ctxt.getApplicationContext();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!NetUtils.checkNetwork(mCtxt)) {
            Logger.e(TAG, "intercept===> NONet!!!!!!");
            throw new Exp(ErrorParser.E.NO_NET,
                    mCtxt.getString(R.string.ERROR_NETWORK_CONNECTION));
        }
        return chain.proceed(chain.request());
    }
}
