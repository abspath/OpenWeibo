package com.github.huajianjiang.net.okhttp;

import android.net.Uri;
import android.text.TextUtils;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Title: 服务器接口全局请求参数拦截器
 * <p>Description: 主要用于拦截应用对服务器的请求,附加全局请求参数
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/23
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class ParamInterceptor implements Interceptor {
    private static final String TAG = ParamInterceptor.class.getSimpleName();
    /**
     * 全局请求参数插入的位置类型
     */
    public static final int TYPE_NONE = -1;
    public static final int TYPE_URL = 0;
    public static final int TYPE_HEADER = 1;
    public static final int TYPE_BODY = 2;

    private int mType = TYPE_NONE;
    private Map<String, String> mParams;

    public ParamInterceptor(int type, Map<String, String> params) {
        mType = type;
        mParams = params;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        if (mType == TYPE_NONE || mParams == null || mParams.isEmpty())
            return chain.proceed(originalRequest);

        Request.Builder builder = originalRequest.newBuilder();
        String url = originalRequest.url().toString();
        RequestBody requestBody = originalRequest.body();
        if (mType == TYPE_URL) {
            //参数添加到请求 url 的 query 部分
            url = mergeParamsForUrl(url, mParams);
        } else if (mType == TYPE_HEADER) {
            //附加额外的 header
            mergeParamsForHeader(builder, mParams);
        } else if (mType == TYPE_BODY) {
            //post 提交表单 CONTENT_TYPE = application/x-www-form-urlencoded key-value 对形式
            requestBody = mergeParamsForBody(requestBody, mParams);
        }
        builder.url(url).method(originalRequest.method(), requestBody);
        return chain.proceed(builder.build());
    }

    public static String mergeParamsForUrl(String url, Map<String, String> params) {
        if (TextUtils.isEmpty(url) || params == null || params.isEmpty()) return url;
        Uri.Builder builder = Uri.parse(url).buildUpon();
        Iterator<Map.Entry<String, String>> paramEntries = params.entrySet().iterator();
        while (paramEntries.hasNext()) {
            Map.Entry<String, String> paramEntry = paramEntries.next();
            builder.appendQueryParameter(paramEntry.getKey(), paramEntry.getValue());
        }
        return builder.build().toString();
    }

    public static String mergeParamsForUrl(String url, JSONObject params) {
        if (TextUtils.isEmpty(url) || params == null) return url;
        Iterator<String> paramNames = params.keys();
        StringBuilder sb = new StringBuilder();
        sb.append(url).append("?");
        while (paramNames.hasNext()) {
            String name = paramNames.next();
            String value = params.optString(name);
            sb.append(name).append("=").append(value).append("&");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static Request.Builder mergeParamsForHeader(Request.Builder builder,
            Map<String, String> params)
    {
        if (builder == null || params == null || params.isEmpty()) return builder;
        Iterator<Map.Entry<String, String>> paramEntries = params.entrySet().iterator();
        while (paramEntries.hasNext()) {
            Map.Entry<String, String> paramEntry = paramEntries.next();
            builder.addHeader(paramEntry.getKey(), paramEntry.getValue());
        }
        return builder;
    }

    public static RequestBody mergeParamsForBody(RequestBody original, Map<String, String> params) {
        if (!(original instanceof FormBody) || params == null || params.isEmpty()) return original;
        FormBody originalBody = (FormBody) original;
        FormBody.Builder builder = new FormBody.Builder();
        for (int i = 0; i < originalBody.size(); i++) {
            builder.addEncoded(originalBody.encodedName(i), originalBody.encodedValue(i));
        }
        Iterator<Map.Entry<String, String>> paramEntries = params.entrySet().iterator();
        while (paramEntries.hasNext()) {
            Map.Entry<String, String> paramEntry = paramEntries.next();
            builder.add(paramEntry.getKey(), paramEntry.getValue());
        }
        return builder.build();
    }

    public static RequestBody mergeParamsForBody(RequestBody original, JSONObject params) {
        if (params == null) return original;
        FormBody.Builder builder = new FormBody.Builder();
        Iterator<String> paramNames = params.keys();
        StringBuilder sb = new StringBuilder();
        while (paramNames.hasNext()) {
            String name = paramNames.next();
            String value = params.optString(name);
            sb.append(name).append("=").append(value).append("&");
        }
        sb.deleteCharAt(sb.length() - 1);
        return builder.build();
    }
}
