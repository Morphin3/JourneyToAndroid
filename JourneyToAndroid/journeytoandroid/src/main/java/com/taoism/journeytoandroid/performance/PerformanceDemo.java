package com.taoism.journeytoandroid.performance;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;

import com.taoism.journeytoandroid.BaseActivity;
import com.taoism.journeytoandroid.R;

/**
 * Date: 2017-03-20
 * Time: 14:01
 * Author: cf
 * -----------------------------
 */

public class PerformanceDemo extends BaseActivity {


    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;

    private BitmapFactory.Options mBitmapOptions;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance_demo);

        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv3 = (ImageView) findViewById(R.id.iv3);

        ViewPropertyAnimator viewPropertyAnimator;



        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.morphin3);


        mBitmapOptions = new BitmapFactory.Options();

        mBitmapOptions.inSampleSize = 2;
        mBitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;

        mBitmapOptions.inBitmap = bitmap;
//        mBitmapOptions.inTargetDensity







    }


}
