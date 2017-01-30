package com.taoism.journeytoandroid.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Date: 2017-01-28
 * Time: 21:35
 * Author: cf
 * -----------------------------
 */

public class Morphin3View extends View {


    //1.创建一个画笔
    private Paint mPaint = new Paint();


    //2. 初始化画笔
    private void initPaint() {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(30f);
    }


    public Morphin3View(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.argb(255, 255, 255, 255));

//        canvas.drawPoint(200, 200, mPaint);
//
//        canvas.drawPoints(new float[]{
//                500, 500,
//                600, 600,
//                700, 700
//        }, mPaint);
//
//
//        canvas.drawLine(400, 400, 800, 900, mPaint);
//        canvas.drawLines(new float[]{
//                100, 300, 300, 300,
//                100, 500, 300, 500
//        }, mPaint);

//        canvas.drawRect(100, 100, 800, 400, mPaint);
//
//        Rect rect = new Rect(200, 200, 900, 500);
//        canvas.drawRect(rect, mPaint);
//
//        RectF rectF = new RectF(300.5f, 300.5f, 1000.5f, 600.5f);
//        canvas.drawRect(rectF, mPaint);


//        RectF recfF = new RectF(300,300,1000,1200);
//        canvas.drawRoundRect(recfF,400,500,mPaint);

//        RectF rectF = new RectF(300, 300, 1000, 1000);
//        canvas.drawOval(rectF, mPaint);


//        canvas.drawCircle(700,700,700,mPaint);


//        RectF rectF1 = new RectF(300,300,1000,1000);
//        canvas.drawArc(rectF1,0,90,true,mPaint);

//        RectF rectF2 = new RectF(300,1200,1000,1900);
//        canvas.drawArc(rectF2,0,90,false,mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        RectF rectF1 = new RectF(300, 300, 600, 600);
        canvas.drawArc(rectF1, 0, 90, false, mPaint);


        mPaint.setStyle(Paint.Style.STROKE);
        RectF rectF2 = new RectF(300, 700, 600, 1000);
        canvas.drawArc(rectF2, 0, 90, true, mPaint);

        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        RectF rectF3 = new RectF(300, 1100, 600, 1400);
        canvas.drawArc(rectF3, 0, 90, false, mPaint);


    }


}
