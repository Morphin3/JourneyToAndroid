package com.taoism.journeytoandroid.utils.toastutil;

import android.os.Bundle;
import android.view.View;

import com.taoism.journeytoandroid.BaseActivity;
import com.taoism.journeytoandroid.R;

/**
 * Date: 2015-07-21
 * Time: 22:14
 * Author: cf
 * -----------------------------
 */
public class ToastUtilDemoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_util_demo);

        findViewById(R.id.btn_show_postive_toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.makeDrawableToastPositive("勾勾");
            }
        });

        findViewById(R.id.btn_show_negative_toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.makeDrawableToastNegative("叉叉");
            }
        });

    }


}
