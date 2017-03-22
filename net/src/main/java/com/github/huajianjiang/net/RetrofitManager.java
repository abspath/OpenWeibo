package com.github.huajianjiang.net;

import android.content.Context;

import com.github.huajianjiang.net.okhttp.CheckingInterceptor;
import com.github.huajianjiang.net.okhttp.LoggingInterceptor;
import com.github.huajianjiang.net.okhttp.ParamInterceptor;
import com.github.huajianjiang.net.util.Config;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/23
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class RetrofitManager {
    private static final int CONNECT_TIMEOUT = 10; //sec
    private static final int READ_TIMEOUT = 15; //sec
    private static final int WRITE_TIMEOUT = 15; //sec

    private static RetrofitManager instance;
    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;

    public static RetrofitManager getInstance(Context ctxt) {
        synchronized (RetrofitManager.class) {
            if (instance == null) {
                instance = new RetrofitManager(ctxt);
            }
            return instance;
        }
    }

    private RetrofitManager(Context ctxt) {
        setupOKHttpClient(ctxt);
        setupRetrofit();
    }

    private void setupOKHttpClient(Context ctxt) {
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new CheckingInterceptor(ctxt))
                .addInterceptor(
                        new ParamInterceptor(ParamInterceptor.TYPE_HEADER, null))
                .addInterceptor(new LoggingInterceptor())
                .build();
    }

    private void setupRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Config.BASE_URL)
                .build();
    }

    public <T> T createService(Class<T> clazzOfService) {
        return mRetrofit.create(clazzOfService);
    }

    /**
     * 请求服务器必须返回 json 格式数据
     * @return
     */
    private Map<String,String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return headers;
    }
}
