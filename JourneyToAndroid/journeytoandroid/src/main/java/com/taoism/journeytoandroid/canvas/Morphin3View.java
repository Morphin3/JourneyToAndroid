package com.taoism.journeytoandroid.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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

    private int mWidth, mHeight;

    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};


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
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.argb(255, 255, 255, 255));

        drawNormal(canvas);

        drawText(canvas);


    }

    private void drawNormal(Canvas canvas) {
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

//        mPaint.setStyle(Paint.Style.FILL);
//        RectF rectF1 = new RectF(300, 300, 600, 600);
//        canvas.drawArc(rectF1, 0, 90, false, mPaint);
//
//
//        mPaint.setStyle(Paint.Style.STROKE);
//        RectF rectF2 = new RectF(300, 700, 600, 1000);
//        canvas.drawArc(rectF2, 0, 90, true, mPaint);
//
//        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
//        RectF rectF3 = new RectF(300, 1100, 600, 1400);
//        canvas.drawArc(rectF3, 0, 90, false, mPaint);


        // 省略了创建画笔的代码

//// 在坐标原点绘制一个黑色圆形
//        mPaint.setColor(Color.BLACK);
//        canvas.translate(200, 200);
//        canvas.drawCircle(0, 0, 100, mPaint);
//
//// 在坐标原点绘制一个蓝色圆形
//        mPaint.setColor(Color.BLUE);
//        canvas.translate(200, 200);
//        canvas.drawCircle(0, 0, 100, mPaint);


//        mPaint.setStyle(Paint.Style.STROKE);

//        canvas.translate(mWidth / 2, mHeight / 2);

//        RectF rectF = new RectF(0, -400, 400, 0);
//        mPaint.setColor(Color.BLACK);
//        canvas.drawRect(rectF, mPaint);

//        canvas.scale(0.5f, 0.5f);
//        mPaint.setColor(Color.BLUE);
//        canvas.drawRect(rectF, mPaint);
//
//        canvas.scale(-1f, -1f);
//        mPaint.setColor(Color.RED);
//        canvas.drawRect(rectF, mPaint);

//        canvas.scale(-0.5f, -0.5f, 200, 0);
//        mPaint.setColor(Color.GREEN);
//        canvas.drawRect(rectF, mPaint);

//        RectF rectF = new RectF(-400, -400, 400, 400);
//
//        for (int i = 0; i <= 20; i++) {
//            canvas.scale(0.9f, 0.9f);
//            canvas.drawRect(rectF, mPaint);
//        }


//        RectF rectF = new RectF(0, -400, 400, 0);
//        canvas.drawRect(rectF, mPaint);
//
//        canvas.rotate(180,200,0);
//        mPaint.setColor(Color.BLUE);
//        canvas.drawRect(rectF, mPaint);


//        mPaint.setStrokeWidth(10);
//        canvas.drawCircle(0, 0, 400, mPaint);          // 绘制两个圆形
//        canvas.drawCircle(0, 0, 380, mPaint);
//
//        for (int i = 0; i <= 360; i += 10) {               // 绘制圆形之间的连接线
//            canvas.drawLine(0, 380, 0, 420, mPaint);
//            mPaint.setColor(mColors[i / 10 / mColors.length]);
//            canvas.rotate(10);
//        }


//        RectF rect = new RectF(0,0,200,200);   // 矩形区域
//
//        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
//        canvas.drawRect(rect,mPaint);
//
//        canvas.skew(0.57f,0);                       // 水平错切 <- 45度
//
//        mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
//        canvas.drawRect(rect,mPaint);
    }

    private void drawText(Canvas canvas) {

        Paint textPaint = new Paint();          // 创建画笔
        textPaint.setColor(Color.BLACK);        // 设置颜色
        textPaint.setStyle(Paint.Style.FILL);   // 设置样式
        textPaint.setTextSize(50);              // 设置字体大小

        String str = "ABCDEFG";

        char[] charArray = str.toCharArray();

        canvas.drawText(str, 1, 3, 200, 500, textPaint);

        canvas.drawText(charArray, 1, 3, 200, 800, textPaint);

        canvas.drawPosText(str,
                new float[]{
                        100, 100,
                        200, 200,
                        300, 300,
                        400, 400,
                        500, 500,
                        600, 600,
                        700, 700
                },
                textPaint);


    }


}
