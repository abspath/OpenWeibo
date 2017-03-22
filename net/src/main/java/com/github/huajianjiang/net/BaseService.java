package com.github.huajianjiang.net;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/24
 * <br>Email: developer.huajianjiang@gmail.com
 */

public interface BaseService {

    /**
     *
     * @param url
     * @param queryMap
     * @param <T>
     * @return
     */
    @GET()
    <T> Observable<Echo<T>> get(@Url String url, @QueryMap Map<String, String> queryMap);

    /**
     * POST 表单形式提交数据 CONTENT_TYPE="application/x-www-form-urlencoded"
     * @param url
     * @param fieldMap
     * @param <T>
     * @return
     */
    @FormUrlEncoded
    @POST()
    <T> Observable<Echo<T>> post(@Url String url, @FieldMap Map<String, String> fieldMap);

}
