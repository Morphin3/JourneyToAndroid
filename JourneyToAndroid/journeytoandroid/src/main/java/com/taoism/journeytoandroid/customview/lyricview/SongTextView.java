package com.taoism.journeytoandroid.customview.lyricview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

public class SongTextView extends View {
    private int       postIndex;
    private TextPaint mSrcPaint;
    private TextPaint mCoverPaint;
    private int delta = 15;
    private float mTextHeight;
    private float mTextWidth;
    private String mText = "梦 里 面 看 我 七 十 二 变 梦 里 面 看 我 七 十 二 变梦 里 面 看 我 七 十 二 变梦 里 面 看 我 七 十 二 变梦 里 面 看 我 七 十 二 变";
    private PorterDuffXfermode xformode;


    private int mWidth;
    private int mHeight;

    public SongTextView(Context ctx) {
        this(ctx, null);
    }

    public SongTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SongTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        mSrcPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        xformode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        mSrcPaint.setColor(Color.CYAN);
        mSrcPaint.setTextSize(60.0f);
        mSrcPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mSrcPaint.setXfermode(null);
        mSrcPaint.setTextAlign(Paint.Align.LEFT);

        //文字精确高度
        Paint.FontMetrics fontMetrics = mSrcPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom - fontMetrics.descent - fontMetrics.ascent;
        mTextWidth = mSrcPaint.measureText(mText);

        mCoverPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        xformode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        mCoverPaint.setColor(Color.CYAN);
        mCoverPaint.setTextSize(60.0f);
        mCoverPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mCoverPaint.setXfermode(null);
        mCoverPaint.setTextAlign(Paint.Align.LEFT);
    }

    /**
     * 计算 控件的宽高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 设置宽度
         */
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY)// match_parent , accurate
            mWidth = widthSize;
        else {
            // 由图片决定的宽
            int desireByImg = getPaddingLeft() + getPaddingRight()
                    + getMeasuredWidth();
            if (widthMode == MeasureSpec.AT_MOST)// wrap_content
                mWidth = Math.min(desireByImg, widthSize);
            else
                mWidth = desireByImg;
        }
        /***
         * 设置高度
         */
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            // match_parent , accurate
            mHeight = heightSize;
        } else {
            int desire = getPaddingTop() + getPaddingBottom()
                    + getMeasuredHeight();
            if (heightMode == MeasureSpec.AT_MOST) {
                // wrap_content
                mHeight = Math.min(desire, heightSize);
            } else {
                mHeight = desire;
            }
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    private int lineCount = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap srcBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas srcCanvas = new Canvas(srcBitmap);
//        srcCanvas.drawText(mText, 0, mTextHeight, mSrcPaint);

        StaticLayout staticLayout = new StaticLayout(mText,
                                                     mSrcPaint,
                                                     srcCanvas.getWidth(),
                                                     Layout.Alignment.ALIGN_NORMAL,
                                                     1.0f,
                                                     0.0f,
                                                     false);
        staticLayout.draw(srcCanvas);


//        staticLayout = new StaticLayout(mText,
//                                        0,
//                                        staticLayout.getLineStart(lineCount),
//                                        mSrcPaint,
//                                        srcCanvas.getWidth(),
//                                        Layout.Alignment.ALIGN_NORMAL,
//                                        1.0f,
//                                        0.0f,
//                                        false);
//
//        mSrcPaint.setColor(Color.YELLOW);
//        staticLayout.draw(srcCanvas);

        int totalLineCount = staticLayout.getLineCount();

        mCoverPaint.setXfermode(xformode);
        mCoverPaint.setColor(Color.RED);
        RectF rectF = new RectF(0,
                                (float) lineCount / totalLineCount * getMeasuredHeight(),
                                postIndex,
                                (float) (lineCount + 1) / totalLineCount * getMeasuredHeight());
        srcCanvas.drawRect(rectF, mCoverPaint);

        canvas.drawBitmap(srcBitmap, 0, 0, null);
//        init();
        if (postIndex < getMeasuredWidth()) {
            postIndex += 10;
        } else {
            lineCount++;
            postIndex = 0;
        }
        postInvalidateDelayed(30);
    }
}