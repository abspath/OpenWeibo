package com.abspath.openweibo.net;

import com.abspath.openweibo.data.model.Weibo;
import com.abspath.openweibo.util.Constant;
import com.github.huajianjiang.net.Echo;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Title: 服务器端接口描述类
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/3/22
 * <br>Email: developer.huajianjiang@gmail.com
 */

public interface WeiboApi {

    @POST()
    @FormUrlEncoded
    <T> Observable<Echo<T>> post(@Url String url, @FieldMap Map<String, String> params);

    @GET()
    <T> Observable<Echo<T>> get(@Url String url, @QueryMap Map<String, String> params);

    @GET(Constant.PUB_TIMELINE_URL)
    Observable<Weibo> getWeiboList(@Query("access_token") String access_token);

}
