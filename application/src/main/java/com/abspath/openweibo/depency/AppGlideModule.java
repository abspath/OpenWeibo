package com.abspath.openweibo.depency;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.GlideModule;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/3/20
 * <br>Email: developer.huajianjiang@gmail.com
 */

public class AppGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        MemorySizeCalculator msc = new MemorySizeCalculator(context);
        int defaultMcs = msc.getMemoryCacheSize();
        int defaultBps = msc.getBitmapPoolSize();
        int customMcs = (int) (defaultMcs * 1.2);
        int customBps = (int) (defaultBps * 1.2);
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888)
                .setMemoryCache(new LruResourceCache(customMcs))
                .setBitmapPool(new LruBitmapPool(customBps));

    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
