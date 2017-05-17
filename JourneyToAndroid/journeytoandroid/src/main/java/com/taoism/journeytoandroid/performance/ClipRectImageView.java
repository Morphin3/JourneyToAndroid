package com.taoism.journeytoandroid.performance;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.taoism.journeytoandroid.utils.toastutil.ToastUtil;

/**
 * Date: 2017-03-20
 * Time: 14:34
 * Author: cf
 * -----------------------------
 */

public class ClipRectImageView extends ImageView {

//    private RectF mRectF;

    private float mRectLeft;
    private float mRectRight;

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

//        float scale = getResources().getDisplayMetrics().density;
//        int pixel = (int) ((20 * scale) + 0.5);

//        if (mRectF == null) {
//            mRectF = new RectF(0, 0, getWidth(), getHeight());
//        }

        canvas.clipRect(mRectLeft == 0 ? 0 : mRectLeft,
                0,
                mRectRight == 0 ? getWidth() : mRectRight,
                getHeight());

        Log.i("performance", "canvas.quickReject(0, 0f, 200f, (float) getHeight(), Canvas.EdgeType.AA):" + canvas.quickReject(0, 0f, 200f, (float) getHeight(), Canvas.EdgeType.AA));
        Log.i("performance", "canvas.quickReject(200f, 0f, getWidth(), (float) getHeight(), Canvas.EdgeType.AA)" + canvas.quickReject(200f, 0f, getWidth(), (float) getHeight(), Canvas.EdgeType.AA));

        super.onDraw(canvas);

    }

    public void setClipRectLeft(float rectLeft) {
        mRectLeft = rectLeft;
    }

    public void setClipRectRight(float rectRight) {
        mRectRight = rectRight;
    }

    @Override
    public boolean hasOverlappingRendering() {
        return super.hasOverlappingRendering();
    }
}
