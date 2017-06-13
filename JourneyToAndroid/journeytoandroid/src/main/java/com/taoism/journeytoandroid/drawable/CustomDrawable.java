package com.taoism.journeytoandroid.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.taoism.journeytoandroid.JourneyApplicationLike;
import com.taoism.journeytoandroid.R;

/**
 * Date: 2017-06-11
 * Time: 19:18
 * Author: cf
 * -----------------------------
 */

public class CustomDrawable extends Drawable {

    private Paint mPaint;

    public CustomDrawable() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(JourneyApplicationLike.context.getResources().getColor(R.color.red1));

    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        final Rect rect = getBounds();

        final float cx = rect.centerX();
        final float cy = rect.centerY();
        canvas.drawCircle(cx, cy, Math.min(cx, cy), mPaint);
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
        mPaint.setAlpha(alpha);
        invalidateSelf();

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override
    public int getOpacity() {
        // not sure, so be safe
        return PixelFormat.TRANSLUCENT;
    }
}
