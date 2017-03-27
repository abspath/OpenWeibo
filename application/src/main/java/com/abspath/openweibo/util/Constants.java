package com.abspath.openweibo.util;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/27
 * <br>Email: developer.huajianjiang@gmail.com
 */

public interface Constants {

    String APP_KEY = "3812601579";

    String APP_SECRET = "a9a31fde34f68df750524cd8701c53e4";

    String REDIRECT_URL = "https://huajianjiang.github.io/";

    String SCOPE = null;

    /**
     * 授权
     */
    String AUTH_URL = "https://api.weibo.com/oauth2/authorize";
    /**
     * 请求 Token
     */
    String ACCESS_TOKEN_URL = "https://api.weibo.com/oauth2/access_token";

    /**
     * 获得公共微博
     */
    String PUB_TIMELINE_URL = "statuses/public_timeline.json";


    String TOKEN_KEY = "token";


}
