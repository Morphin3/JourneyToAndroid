package com.taoism.journeytoandroid.aop.javassist;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Date: 2018-05-10
 * Time: 13:14
 * Author: chenfei
 * -----------------------------
 * MISSION
 */
public class JavassistDemoActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("AOP", "Javassist 原始 Log");
    }
}
