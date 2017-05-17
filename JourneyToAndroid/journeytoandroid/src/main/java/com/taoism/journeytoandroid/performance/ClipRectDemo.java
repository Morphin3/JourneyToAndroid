package com.taoism.journeytoandroid.performance;

import android.annotation.TargetApi;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import com.taoism.journeytoandroid.BaseActivity;
import com.taoism.journeytoandroid.R;

/**
 * Date: 2017-03-20
 * Time: 14:01
 * Author: cf
 * -----------------------------
 */

public class ClipRectDemo extends BaseActivity {


    private ImageView         iv1;
    private ClipRectImageView iv2;
    private ClipRectImageView iv3;
    private ClipRectImageView iv4;

    private BitmapFactory.Options mBitmapOptions;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_rect_demo);

        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ClipRectImageView) findViewById(R.id.iv2);
        iv3 = (ClipRectImageView) findViewById(R.id.iv3);
        iv4 = (ClipRectImageView) findViewById(R.id.iv4);

        iv2.setClipRectLeft(200);
        iv2.setClipRectRight(0);

        iv3.setClipRectLeft(0);
        iv3.setClipRectRight(200);

        iv4.setClipRectLeft(200);
        iv4.setClipRectRight(0);


//        ViewPropertyAnimator viewPropertyAnimator;
//
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.morphin3);
//
//        mBitmapOptions = new BitmapFactory.Options();
//
//        mBitmapOptions.inSampleSize = 2;
//        mBitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
////        mBitmapOptions.inTargetDensity;
//
////        mBitmapOptions.inTargetDensity

    }


}
