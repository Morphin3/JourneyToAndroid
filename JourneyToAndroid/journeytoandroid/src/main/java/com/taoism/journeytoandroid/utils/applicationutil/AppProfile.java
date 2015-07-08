package com.taoism.journeytoandroid.utils.applicationutil;

import android.content.Context;

/**
 * Created by zyl06 on 6/2/15.
 */
public abstract class AppProfile {
    /* package */ public static Context sContext;
//    static private RequestQueue sRequestQueue;

    public static final Context getContext() {
        return sContext;
    }

//    public static RequestQueue getRequestQueue() {
//        if (sRequestQueue == null) {
//            sRequestQueue = Volley.newRequestQueue(sContext, new MultiPartStack());
//            sRequestQueue.start();
//        }
//        return sRequestQueue;
//    }
}