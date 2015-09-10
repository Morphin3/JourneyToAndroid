package com.taoism.journeytoandroid.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.MessageQueue;

/**
 * Created by zyl06 on 6/19/15.
 */
public class HandlerUtil {
    public static void doIdleHandler(MessageQueue.IdleHandler idleHandler) {
        Looper.getMainLooper().myQueue().addIdleHandler(idleHandler);
    }
    public static void doDelay(Runnable runable, long delayMillis) {
        Handler handler = new Handler();
        handler.postDelayed(runable, delayMillis);
    }
}
