package com.taoism.journeytoandroid.customview.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.taoism.journeytoandroid.R;

/**
 * Date: 2015-06-01
 * Time: 22:06
 * Author: Morphin3
 * WeChat: 398788401
 * E-mail: morphin333@gmail.com
 * -----------------------------
 * FIXME
 */
public class DialView extends View {
    private static final int OVER_DEGREE = 22;
    private static final int MARGIN_HORIZONTAL = 40;
    private static final int COLOR_GRAY = 0xffdddddd;
    private static final int COLOR_RED = 0xfff04243;
    private static final int COLOR_ORANGE = 0xfff4814a;
    private static final int COLOR_YELLOW = 0xfffbcf54;
    private static final int COLOR_GREEN = 0xff4cd762;

    private Paint mPaint = new Paint();
    private RectF mRectF = new RectF();

    private Bitmap dialBmp;
//    private Bitmap dotBmp;

    private int mCurrentColor = COLOR_RED;
    private int mDisplayWidth;
    private int mDisplayHeight;
    private int mTargetDegree = 0;
    private int mDegree = 0;
    private int mDrawSpeed = 3;

    private int mLeft;
    private int mTop;
    private int mCenterX;
    private int mCenterY;
    private int mDialWidth;
    private int mDiameter;

    private Handler mHandler = new Handler();
    private Runnable drawR = new Runnable() {
        @Override
        public void run() {
            if (mDegree <= mTargetDegree) {
                invalidate();
                mDegree += mDrawSpeed;
                mHandler.postDelayed(this, 1);
            }
        }
    };

    public DialView(Context context) {
        this(context, null, 0);
    }

    public DialView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DialView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        mDisplayWidth = point.x;
        mDisplayHeight = point.y;

        Bitmap tmpBmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_dial);
        int bmpWidth = mDisplayWidth - MARGIN_HORIZONTAL * 2;
        int bmpHeight = bmpWidth * tmpBmp.getHeight() / tmpBmp.getWidth();
        dialBmp = Bitmap.createScaledBitmap(tmpBmp, bmpWidth, bmpHeight, false);
//        dotBmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_dial_dot);

        mLeft = MARGIN_HORIZONTAL;
        mTop = 0;
        mCenterX = mDisplayWidth / 2;
        mCenterY = dialBmp.getHeight() / 2;
        mDialWidth = 110;
        mDiameter = mDisplayWidth - (mLeft + mDialWidth) * 2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(widthMeasureSpec, dialBmp.getHeight()|MeasureSpec.EXACTLY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAntiAlias(true);
        mPaint.setColor(COLOR_GRAY);
        mPaint.setStrokeWidth(12);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawBitmap(dialBmp, mLeft, mTop, mPaint);
        mRectF.set((mDisplayWidth - mDiameter) / 2, mTop + mDialWidth, (mDisplayWidth - mDiameter) / 2 + mDiameter, mTop + mDialWidth + mDiameter);
        canvas.drawArc(mRectF, OVER_DEGREE, -(180 + OVER_DEGREE * 2), false, mPaint);

        mPaint.setColor(mCurrentColor);
        canvas.drawArc(mRectF, mDegree - (180 + OVER_DEGREE), -mDegree, false, mPaint);
//        canvas.drawBitmap(dotBmp, (mDisplayWidth - dotBmp.getWidth()) / 2, mTop + mDialWidth + 40, mPaint);
    }

    public void setDegree(int rate) {
        if (rate <= 25) {
            mCurrentColor = COLOR_RED;
        } else if (rate <= 50) {
            mCurrentColor = COLOR_ORANGE;
        } else if (rate <= 75) {
            mCurrentColor = COLOR_YELLOW;
        } else {
            mCurrentColor = COLOR_GREEN;
        }
        mTargetDegree = rate * (180 + OVER_DEGREE * 2) / 100;
        mDegree = 0;
        mHandler.removeCallbacks(drawR);
        mHandler.post(drawR);
    }

    public int getCurrentColor() {
        return mCurrentColor;
    }
}