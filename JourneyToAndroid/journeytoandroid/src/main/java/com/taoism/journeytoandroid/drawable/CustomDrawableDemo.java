package com.taoism.journeytoandroid.drawable;

import android.os.Bundle;
import android.widget.ImageView;

import com.taoism.journeytoandroid.BaseActivity;
import com.taoism.journeytoandroid.R;

/**
 * Date: 2017-06-11
 * Time: 19:16
 * Author: cf
 * -----------------------------
 */

public class CustomDrawableDemo extends BaseActivity {


    private ImageView ivCustomDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_drawable_demo);

        ivCustomDrawable = (ImageView) findViewById(R.id.iv_custom_drawable);


        ivCustomDrawable.setImageDrawable(new CustomDrawable());
    }


}
