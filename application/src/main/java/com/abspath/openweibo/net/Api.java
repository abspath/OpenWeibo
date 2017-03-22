package com.abspath.openweibo.net;

import com.abspath.openweibo.data.model.Auth;
import com.abspath.openweibo.data.model.PubTimeline;
import com.abspath.openweibo.util.Constant;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Title: 服务器端接口描述类
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/3/22
 * <br>Email: developer.huajianjiang@gmail.com
 */

public interface Api {

    @POST()
    @FormUrlEncoded
    Call<Auth> post(@Url String url, @FieldMap Map<String, String> params);

    @GET()
    Call<Auth> get(@Url String url, @QueryMap Map<String, String> params);

    @GET(Constant.PUB_TIMELINE_URL)
    Call<PubTimeline> getPubTimelime(@QueryMap Map<String, String> params);

}
