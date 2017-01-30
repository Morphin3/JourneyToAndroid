package com.taoism.journeytoandroid.canvas;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Date: 2017-01-30
 * Time: 22:14
 * Author: cf
 * -----------------------------
 */

public class Moorphin3PathView extends View {

    private int mWidth, mHeight;

    private Paint mPaint = new Paint();

    public Moorphin3PathView(Context context) {
        this(context, null);
    }

    public Moorphin3PathView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);

        canvas.translate(mWidth / 2, mHeight / 2);

//        Path path = new Path();
//        path.lineTo(200, 200);
//        path.lineTo(200, 0);
//        path.lineTo(400, 200);
//
////        path.moveTo(400, 0);
//        path.setLastPoint(400, 0);
//
//        path.lineTo(600, 200);
//
//        path.close();
//
//        canvas.drawPath(path, mPaint);

//
//        Path path = new Path();
//
//        path.addRect(-200, -200, 200, 200, Path.Direction.CCW);
//
//        path.setLastPoint(-300, 300);
//
//        canvas.drawPath(path, mPaint);

//
//        canvas.scale(1, -1);                         // <-- 注意 翻转y坐标轴
//
//        Path path = new Path();
//        path.lineTo(100, 100);
//
//        RectF oval = new RectF(0, 0, 300, 300);
//
////        path.arcTo(oval,0,270);
//        path.arcTo(oval, 0, 270, true);             // <-- 和上面一句作用等价
//
//        canvas.drawPath(path, mPaint);



//        mPaint.setStyle(Paint.Style.FILL);
//
//        Path path = new Path();                                     // 创建Path
//
//// 添加小正方形 (通过这两行代码来控制小正方形边的方向,从而演示不同的效果)
//// path.addRect(-200, -200, 200, 200, Path.Direction.CW);
//        path.addRect(-200, -200, 200, 200, Path.Direction.CCW);
//
//// 添加大正方形
//        path.addRect(-400, -400, 400, 400, Path.Direction.CCW);
//
//        path.setFillType(Path.FillType.WINDING);                    // 设置Path填充模式为非零环绕规则
//
//        canvas.drawPath(path, mPaint);                       // 绘制Path




        canvas.translate(mWidth / 2, mHeight / 2);

        Path path1 = new Path();
        Path path2 = new Path();
        Path path3 = new Path();
        Path path4 = new Path();

        path1.addCircle(0, 0, 200, Path.Direction.CW);
        path2.addRect(0, -200, 200, 200, Path.Direction.CW);
        path3.addCircle(0, -100, 100, Path.Direction.CW);
        path4.addCircle(0, 100, 100, Path.Direction.CCW);


        path1.op(path2, Path.Op.DIFFERENCE);
        path1.op(path3, Path.Op.UNION);
        path1.op(path4, Path.Op.DIFFERENCE);

        canvas.drawPath(path1, mPaint);


    }
}
