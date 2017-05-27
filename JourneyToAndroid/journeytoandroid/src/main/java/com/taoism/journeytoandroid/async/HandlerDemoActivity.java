package com.taoism.journeytoandroid.async;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.taoism.journeytoandroid.BaseActivity;
import com.taoism.journeytoandroid.R;
import com.taoism.journeytoandroid.utils.toastutil.ToastUtil;

/**
 * Date: 2017-05-08
 * Time: 13:57
 * Author: cf
 * -----------------------------
 */


public class HandlerDemoActivity extends BaseActivity implements View.OnClickListener {


    private Handler mMainHandler;
    private Handler mBackgroundHandler;

    private MyThread mMyThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_demo);

        findViewById(R.id.btn_async).setOnClickListener(this);


        mMainHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.e("async", Thread.currentThread().getName());
                ToastUtil.makeLongToast("后台线程发送到主线程");
            }
        };


        mMyThread = getABackgroundThread();
        mMyThread.start();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_async:
                mBackgroundHandler = mMyThread.getBackgroundHandler();
                Message message = Message.obtain();
                message.what = 1;
                mBackgroundHandler.sendMessage(message);
                break;

        }
    }


    public static MyThread getABackgroundThread() {
        return new MyThread();
    }

    class MyRunnable implements Runnable {
        @Override
        public void run() {
            Log.i("async", "后台线程在工作");
        }
    }


}
