package com.taoism.journeytoandroid.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

/**
 * Date: 2017-01-28
 * Time: 21:35
 * Author: cf
 * -----------------------------
 */

public class Morphin3View extends View {

    public Morphin3View(Context context) {
        super(context);
    }

    public Morphin3View(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Morphin3View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.argb(127, 255, 0, 0));
    }
}
