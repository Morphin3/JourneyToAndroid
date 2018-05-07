package com.taoism.journeytoandroid.aop.aspectj;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Date: 2018-05-04
 * Time: 15:53
 * Author: chenfei
 * -----------------------------
 * MISSION
 */
public class AOPDemoActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("AOP", "onCreate");


        testAOP();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("AOP", "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("AOP", "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("AOP", "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("AOP", "onDestroy");
    }


    private void testAOP() {
        Log.i("AOP", "testAOP");
    }
}
