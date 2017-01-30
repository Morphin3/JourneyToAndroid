package com.taoism.journeytoandroid.canvas;

import android.os.Bundle;

import com.taoism.journeytoandroid.BaseActivity;
import com.taoism.journeytoandroid.R;

/**
 * Date: 2017-01-28
 * Time: 21:33
 * Author: cf
 * -----------------------------
 */

public class CanvasDemo extends BaseActivity {

    private CheckView mCheckView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_demo);

//        mCheckView = (CheckView) findViewById(R.id.cv);
//
//        findViewById(R.id.btn_check).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mCheckView.check();
//            }
//        });
//
//        findViewById(R.id.btn_uncheck).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mCheckView.unCheck();
//            }
//        });
    }
}
