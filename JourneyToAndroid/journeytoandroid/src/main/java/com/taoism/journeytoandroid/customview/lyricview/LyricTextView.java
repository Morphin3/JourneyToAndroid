package com.taoism.journeytoandroid.customview.lyricview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Date: 2017-11-12
 * Time: 11:37
 * Author: cf
 * -----------------------------
 */

public class LyricTextView extends TextView {

    private PorterDuffXfermode xformode;
    private TextPaint          mSrcPaint;
    private TextPaint          mCoverPaint;


    private float lineSpacingMultiplier;
    private float lineSpacingExtra;

    public LyricTextView(Context context) {
        super(context);
    }

    public LyricTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LyricTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {

        lineSpacingMultiplier = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                ? getLineSpacingMultiplier()
                : 1.0f;
        lineSpacingExtra = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                ? getLineSpacingExtra()
                : 0.0f;

        final Resources res = getResources();
//        final CompatibilityInfo compat = res.getCompatibilityInfo();

        mSrcPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mSrcPaint.setColor(Color.CYAN);
        mSrcPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mSrcPaint.density = res.getDisplayMetrics().density;
//        mSrcPaint.setCompatibilityScaling(compat.applicationScale);
        mSrcPaint.setTextSize(getTextSize());
        mSrcPaint.setTextScaleX(getTextScaleX());
        mSrcPaint.setTypeface(getTypeface());
        xformode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        mSrcPaint.setXfermode(null);


        mCoverPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mCoverPaint.setColor(Color.RED);
        mCoverPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mCoverPaint.density = res.getDisplayMetrics().density;
//        mCoverPaint.setCompatibilityScaling(compat.applicationScale);
        mCoverPaint.setTextSize(getTextSize());
        mCoverPaint.setTextScaleX(getTextScaleX());
        mCoverPaint.setTypeface(getTypeface());

        xformode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        mCoverPaint.setXfermode(xformode);

    }

    private StaticLayout staticLayout = null;
    private int wantWidth;

    int totalLineCount;


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        wantWidth = getMeasuredWidth() - getCompoundPaddingLeft() - getCompoundPaddingRight();

        staticLayout = new StaticLayout(getText(),
                                        mSrcPaint,
                                        wantWidth,
                                        Layout.Alignment.ALIGN_NORMAL,
                                        lineSpacingMultiplier,
                                        lineSpacingExtra,
                                        true);

        totalLineCount = staticLayout.getLineCount();

    }


    private int lineIndex = 0;
    private int postIndex;


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//
//        mSrcPaint.setColor(Color.RED);
//        mSrcPaint.setXfermode(xformode);
//        StaticLayout staticLayout1 = new StaticLayout(getText(),
//                                                      0,
//                                                      staticLayout.getLineStart(lineIndex),
//                                                      mSrcPaint,
//                                                      wantWidth,
//                                                      Layout.Alignment.ALIGN_NORMAL,
//                                                      lineSpacingMultiplier,
//                                                      lineSpacingExtra,
//                                                      true);
//        staticLayout1.draw(canvas);

        // MISSION:  内存优化

        mSrcPaint.setXfermode(null);
        Bitmap srcBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas srcCanvas = new Canvas(srcBitmap);
        staticLayout.draw(srcCanvas);

        mCoverPaint.setXfermode(xformode);

        StaticLayout staticLayout2 = new StaticLayout(getText(),
                                                      0,
                                                      staticLayout.getLineStart(lineIndex),
                                                      mCoverPaint,
                                                      wantWidth,
                                                      Layout.Alignment.ALIGN_NORMAL,
                                                      lineSpacingMultiplier,
                                                      lineSpacingExtra,
                                                      true);

        staticLayout2.draw(srcCanvas);


        RectF rectF1 = new RectF(0,
                                 0,
                                 getMeasuredWidth(),
                                 (float) lineIndex / totalLineCount * getMeasuredHeight());
        srcCanvas.drawRect(rectF1, mCoverPaint);

        RectF rectF2 = new RectF(0,
                                 (float) lineIndex / totalLineCount * getMeasuredHeight(),
                                 postIndex,
                                 (float) (lineIndex + 1) / totalLineCount * getMeasuredHeight());
        srcCanvas.drawRect(rectF2, mCoverPaint);

        canvas.drawBitmap(srcBitmap, 0, 0, null);


        if (postIndex < getMeasuredWidth()) {
            postIndex += 10;
        } else {
            if (lineIndex < totalLineCount) {
                lineIndex++;
                postIndex = 0;
            }
        }

        if (lineIndex < totalLineCount) {
            postInvalidateDelayed(30);
        }

    }
}
