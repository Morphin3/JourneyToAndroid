package com.taoism.journeytoandroid.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by chenfei on 14-8-22.
 * 宽度适应屏幕，高度等比例缩放的ImageView
 */
public class AspectRatioImageView extends ImageView {

    public AspectRatioImageView(Context context) {
        super(context);
    }

    public AspectRatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AspectRatioImageView(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int diw = drawable.getIntrinsicWidth();
            if (diw > 0) {
                int height = width * drawable.getIntrinsicHeight() / diw;
                setMeasuredDimension(width, height);
            } else
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
