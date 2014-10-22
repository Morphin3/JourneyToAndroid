package com.taoism.journeytoandroid;

import android.app.Application;
import com.activeandroid.ActiveAndroid;

/**
 * Created by chenfei on 14-10-22.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }


}
