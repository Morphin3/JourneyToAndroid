package com.taoism.journeytoandroid.customview;

import android.app.Activity;
import android.os.Bundle;

import com.taoism.journeytoandroid.R;
import com.taoism.journeytoandroid.customview.widget.DialView;

/**
 * Date: 2015-06-01
 * Time: 22:09
 * Author: Morphin3
 * WeChat: 398788401
 * E-mail: morphin333@gmail.com
 * -----------------------------
 * FIXME
 */
public class DialViewDemoActivity extends Activity {

    private DialView dv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialview_demo);

        dv = (DialView)findViewById(R.id.dv);
        dv.setDegree(60);

    }
}
