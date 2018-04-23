package com.taoism.journeytoandroid.customview.lyricview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
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
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.taoism.journeytoandroid.R;


/**
 * Date: 2017-11-12
 * Time: 11:37
 * Author: chenfei
 * -----------------------------
 */

public class LyricTextView extends TextView {


    private final int default_original_color = Color.rgb(0x00, 0x00, 0x00);
    private final int default_overlay_color  = Color.rgb(0x49, 0xC1, 0x20);
    private final int default_duration       = 10 * 1000;

    private int          originalColor;
    private int          overlayColor;
    private int          duration;
    private CharSequence lyricText;

    private PorterDuffXfermode xformode;
    private TextPaint          mSrcPaint;
    private TextPaint          mCoverPaint;


    private float         lineSpacingMultiplier;
    private float         lineSpacingExtra;
    private ValueAnimator valueAnimator;
    private boolean animating = false;

    public LyricTextView(Context context) {
        super(context);
    }

    public LyricTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.lyricTextViewStyle);
    }

    public LyricTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray typedArray = context.obtainStyledAttributes(attrs,
                                                                     R.styleable.LyricTextView,
                                                                     defStyleAttr,
                                                                     R.style.LyricTextView);

        try {
            originalColor = typedArray.getColor(R.styleable.LyricTextView_originalColor, default_original_color);
            overlayColor = typedArray.getColor(R.styleable.LyricTextView_overlayColor, default_overlay_color);
            duration = typedArray.getInteger(R.styleable.LyricTextView_duration, default_duration);
            lyricText = typedArray.getString(R.styleable.LyricTextView_lyricText);

        } finally {
            typedArray.recycle();
        }

        init();
    }

    public void init() {


        totalTime = 10 * 1000;

        lineSpacingMultiplier = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                ? getLineSpacingMultiplier()
                : 1.0f;
        lineSpacingExtra = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                ? getLineSpacingExtra()
                : 0.0f;

        final Resources res = getResources();
//        final CompatibilityInfo compat = res.getCompatibilityInfo();

        mSrcPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mSrcPaint.setColor(originalColor);
        mSrcPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mSrcPaint.density = res.getDisplayMetrics().density;
//        mSrcPaint.setCompatibilityScaling(compat.applicationScale);
        mSrcPaint.setTextSize(getTextSize());
        mSrcPaint.setTextScaleX(getTextScaleX());
        mSrcPaint.setTypeface(getTypeface());
        xformode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        mSrcPaint.setXfermode(null);


        mCoverPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mCoverPaint.setColor(overlayColor);
        mCoverPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mCoverPaint.density = res.getDisplayMetrics().density;
//        mCoverPaint.setCompatibilityScaling(compat.applicationScale);
        mCoverPaint.setTextSize(getTextSize());
        mCoverPaint.setTextScaleX(getTextScaleX());
        mCoverPaint.setTypeface(getTypeface());

        xformode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        mCoverPaint.setXfermode(xformode);

        setLyricText(getText());
    }


    public void setLyricText(CharSequence text) {
        setText(text);
        this.lyricText = text;

        totalWordWidth = mSrcPaint.measureText(text,0, text.length()) + 300;

        initAnimation();
    }

    private void initAnimation() {
        valueAnimator = ValueAnimator.ofFloat(0f, totalWordWidth);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(totalTime);
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                animating = true;
            }

            @Override
            public void onAnimationEnd(final Animator animation) {
                super.onAnimationEnd(animation);
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        animating = false;
                    }
                }, 500);

            }
        });
    }

    public void reset() {
        postIndex = 0;
        lineIndex = 0;
        initAnimation();
        postInvalidate();
    }

    public void startAnimation() {
        valueAnimator.start();
        postInvalidate();
    }

    private StaticLayout staticLayout = null;
    private int wantWidth;
    private int totalLineCount;
    private int totalTime           = 10 * 1000;
    private int invalidateFrequency = 30;
    private float totalWordWidth;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        wantWidth = getMeasuredWidth() - getCompoundPaddingLeft() - getCompoundPaddingRight();


        staticLayout = new StaticLayout(getText(),
                                        mSrcPaint,
                                        getMeasuredWidth(),
                                        Layout.Alignment.ALIGN_NORMAL,
                                        lineSpacingMultiplier,
                                        lineSpacingExtra,
                                        true);

        totalLineCount = staticLayout.getLineCount();

    }


    private int lineIndex = 0;
    private float postIndex;


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // MISSION:  内存优化

        Bitmap srcBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas srcCanvas = new Canvas(srcBitmap);
        staticLayout.draw(srcCanvas);

        mCoverPaint.setXfermode(xformode);

//        StaticLayout staticLayout2 = new StaticLayout(getText(),
//                                                      0,
//                                                      staticLayout.getLineStart(lineIndex),
//                                                      mCoverPaint,
//                                                      wantWidth,
//                                                      Layout.Alignment.ALIGN_NORMAL,
//                                                      lineSpacingMultiplier,
//                                                      lineSpacingExtra,
//                                                      true);
//
//        staticLayout2.draw(srcCanvas);


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

        float currentValue = ((float) valueAnimator.getAnimatedValue());

        postIndex = currentValue % getMeasuredWidth();

        if (currentValue - lineIndex * getMeasuredWidth() >= getMeasuredWidth()) {
            if (lineIndex < totalLineCount) {
                lineIndex++;
            }
        }

        if (animating && (lineIndex < totalLineCount)) {
            postInvalidateDelayed(invalidateFrequency);
        }

    }

}
