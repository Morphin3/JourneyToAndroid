package com.taoism.journeytoandroid.customview.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by chenfei on 14-8-22.
 * 宽度始终保持和高度相同的ImageView
 */
public class WidthAdaptiveImageView extends ImageView {

    public WidthAdaptiveImageView(Context context) {
        super(context);
    }

    public WidthAdaptiveImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WidthAdaptiveImageView(Context context, AttributeSet attrs,
                                  int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec, heightMeasureSpec);
//        Drawable drawable = getDrawable();
//        if (drawable != null) {
//            int width = MeasureSpec.getSize(widthMeasureSpec);
//            int diw = drawable.getIntrinsicWidth();
//            if (diw > 0) {
//                int height = width * drawable.getIntrinsicHeight() / diw;
//                setMeasuredDimension(width, height);
//            } else
//                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        } else
//            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
