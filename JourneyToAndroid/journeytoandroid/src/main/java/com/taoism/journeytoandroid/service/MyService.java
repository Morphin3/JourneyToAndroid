package com.taoism.journeytoandroid.service;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import android.util.Log;

/**
 * Date: 2015-05-23
 * Time: 23:37
 * Author: Morphin3
 * WeChat: 398788401
 * E-mail: morphin333@gmail.com
 * -----------------------------
 * FIXME
 */
public class MyService extends Service {

    public static final String TAG = "MyService";

//    private MyBinder mBinder = new MyBinder();


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() executed");
        Log.d("MyService","MyService thread id is" + Thread.currentThread().getId());
        Log.d("TAG", "process id is " + android.os.Process.myPid());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() executed");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    MyAIDLService.Stub mBinder  = new MyAIDLService.Stub() {
        @Override
        public int plus(int a, int b) throws RemoteException {
            return a + b;
        }

        @Override
        public String toUpperCase(String str) throws RemoteException {
            if(str != null){
                return str.toUpperCase();
            }
            return null;
        }
    };


//    class MyBinder extends Binder {
//
//        public void startDownload(){
//            Log.d("TAG", "startDownload() executed");
//            // 执行具体的下载任务
//        }
//    }


}
