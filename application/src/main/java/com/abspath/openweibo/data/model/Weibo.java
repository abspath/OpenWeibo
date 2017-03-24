package com.abspath.openweibo.data.model;

import java.util.List;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/3/22
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class Weibo {
    /**
     * 当前获取的最新微博
     */
    public List<WeiboItem> statuses;
    /**
     *
     */
    public boolean hasvisible;
    /**
     *
     */
    public int previous_cursor;
    /**
     *
     */
    public int next_cursor;
    /**
     * 当前返回的微博个数
     */
    public int total_number;
    /**
     *
     */
    public int interval;

    public static class WeiboItem {
        /**
         * 用户UID
         */
        public long id;
        /**
         * 字符串型的用户UID
         */
        public String idstr;
        /**
         * 微博MID
         */
        public long mid;
        /**
         * 微博创建时间
         */
        public String created_at;
        /**
         * 微博信息内容
         */
        public String text;
        /**
         * 微博来源
         */
        public String source;
        /**
         * 缩略图片地址，没有时不返回此字段
         */
        public String thumbnail_pic;
        /**
         * 中等尺寸图片地址，没有时不返回此字段
         */
        public String bmiddle_pic;
        /**
         * 原始图片地址，没有时不返回此字段
         */
        public String original_pic;
        /**
         * 微博作者的用户信息字段
         */
        public User user;
        /**
         * 是否已收藏，true：是，false：否
         */
        public boolean favorited;
        /**
         * 是否被截断，true：是，false：否
         */
        public boolean truncated;
        /**
         * 转发数
         */
        public int reposts_count;
        /**
         * 评论数
         */
        public int comments_count;
        /**
         * 表态数
         */
        public int attitudes_count;
    }

}
