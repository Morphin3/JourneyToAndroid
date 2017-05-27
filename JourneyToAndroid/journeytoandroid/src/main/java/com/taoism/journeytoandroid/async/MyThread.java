package com.taoism.journeytoandroid.async;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Date: 2017-05-26
 * Time: 11:32
 * Author: cf
 * -----------------------------
 */

public class MyThread extends Thread {

    private ThreadLocal<Integer> mThreadLocal;

    private Handler mBackgroundHandler;

    private Looper mLooper;


    private static Map<String, MyThread> mThreads = new HashMap<>();

    public static MyThread getMyThread(String name) {
        return mThreads.get(name);
    }


    public MyThread() {
        super();


        mThreads.put("async", this);
    }

    public MyThread(Runnable runnable) {
        super(runnable);
    }

    public Handler getBackgroundHandler() {
        return mBackgroundHandler;
    }

    @Override
    public void run() {
        Log.i("async", "后台线程在工作");

        Looper.prepare();


        synchronized (this) {
            mLooper = Looper.myLooper();
            notifyAll();
        }

        mBackgroundHandler =
                new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        Log.e("async", Thread.currentThread().getName());
                        Log.i("async", "后台线程收到消息");
                    }
                };

        Process.setThreadPriority(Process.THREAD_PRIORITY_DEFAULT);

        mLooper.loop();

    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public boolean quitSafely() {
        Looper looper = getLooper();
        if (looper != null) {
            looper.quitSafely();
            return true;
        }
        return false;
    }

    public boolean quit() {
        Looper looper = getLooper();
        if (looper != null) {
            looper.quit();
            return true;
        }
        return false;
    }

    public Looper getLooper() {
        if (!isAlive()) {
            return null;
        } else {
            while (isAlive() && mLooper == null) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return mLooper;
        }

    }
}

