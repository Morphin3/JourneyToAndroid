package com.taoism.journeytoandroid.async;

import android.os.AsyncTask;
import android.os.Bundle;

import com.taoism.journeytoandroid.BaseActivity;

/**
 * Date: 2017-03-01
 * Time: 15:22
 * Author: cf
 * -----------------------------
 */

public class AsyncDemoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }


    class MyAsyncTask extends AsyncTask<Object, Object, Integer> {



        @Override
        protected Integer doInBackground(Object... params) {
            return null;
        }
    }
}
