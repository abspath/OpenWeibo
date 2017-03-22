package com.abspath.openweibo.depency;

import com.abspath.openweibo.util.Logger;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.Locale;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/3/21
 * <br>Email: developer.huajianjiang@gmail.com
 */

public class GlideLoggingListener<T, R> implements RequestListener<T, R> {
    private static final String TAG = GlideLoggingListener.class.getSimpleName();

    @Override
    public boolean onException(Exception e, T model, Target<R> target, boolean isFirstResource)
    {
        Logger.e(TAG, String.format(Locale.ROOT, "onException(%s, %s, %s, %s)", e, model, target,
                isFirstResource), e);
        return false;
    }

    @Override
    public boolean onResourceReady(R resource, T model, Target<R> target, boolean isFromMemoryCache,
            boolean isFirstResource)
    {
        Logger.e(TAG,
                String.format(Locale.ROOT, "onResourceReady(%s, %s, %s, %s, %s)", resource, model,
                        target, isFromMemoryCache, isFirstResource));
        return false;
    }

}
