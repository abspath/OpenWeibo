package com.abspath.openweibo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;

/**
 * Title: 自定义内容居中的 {@link CenteredEditText},{@code} TextView 同理
 * <p>Description: 居中的内容包括 四个方向的 drawable 和 text
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/3/1
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class CenteredEditText extends android.support.v7.widget.AppCompatEditText {
    private static final String TAG = CenteredEditText.class.getSimpleName();

    public CenteredEditText(Context context) {
        super(context);
    }

    public CenteredEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CenteredEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        // 只关心设置一个居中，其他的忽略
        for (int i = 0; i < drawables.length; i++) {
            Drawable dr = drawables[i];
            if (dr != null) {
                int drPadding = getCompoundDrawablePadding();
                int drWidth = dr.getIntrinsicWidth();
                int drHeight = dr.getIntrinsicHeight();
                CharSequence txt = getHint();//只处理 Hint 文本
                TextPaint txtPaint = getPaint();
                float txtWidth = txtPaint.measureText(txt, 0, txt.length());
                float txtHeight = txtPaint.descent() - txtPaint.ascent();
                float tx = 0;
                float ty = 0;
                if (i == 0 || i == 2) { // 左右
                    float rw = (getWidth() - drWidth - txtWidth - drPadding - getPaddingLeft() -
                                getPaddingRight());
                    tx = i == 0 ? rw / 2 : -rw / 2;
                } else { // 上下
                    float rw = (getHeight() - drHeight - txtHeight - drPadding - getPaddingTop() -
                                getPaddingBottom());
                    ty = i == 1 ? rw / 2 : -rw / 2;
                }
                canvas.translate(tx, ty);
                break;
            }
        }
        super.onDraw(canvas);
    }
}
