package com.taoism.journeytoandroid.async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Date: 2017-03-01
 * Time: 15:34
 * Author: cf
 * -----------------------------
 */

public class ThreadPoolUtil {


    private static volatile ExecutorService mExecutorService;

    private ThreadPoolUtil() {

    }

    public static ExecutorService getInstance() {
        if (mExecutorService == null) {
            synchronized (ThreadPoolUtil.class) {
                if (mExecutorService == null) {
//                    Executors.newFixedThreadPool(5, new ThreadFactory() {
//                        @Override
//                        public Thread newThread(Runnable r) {
//                            return null;
//                        }
//                    });
                    mExecutorService = Executors.newCachedThreadPool();
                }
            }
        }
        return mExecutorService;
    }


    public static void execute(Runnable runnable) {
        getInstance().execute(runnable);
    }


}
