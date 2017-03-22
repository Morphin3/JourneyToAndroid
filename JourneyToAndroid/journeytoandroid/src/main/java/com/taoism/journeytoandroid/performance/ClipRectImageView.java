package com.taoism.journeytoandroid.performance;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Date: 2017-03-20
 * Time: 14:34
 * Author: cf
 * -----------------------------
 */

public class ClipRectImageView extends ImageView {

    public ClipRectImageView(Context context) {
        super(context);
    }

    public ClipRectImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ClipRectImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        float scale = getResources().getDisplayMetrics().density;
        int pixel = (int) ((20 * scale) + 0.5);

        canvas.clipRect(0,0, pixel, getHeight());

        super.onDraw(canvas);


    }

    @Override
    public boolean hasOverlappingRendering() {
        return super.hasOverlappingRendering();
    }
}
