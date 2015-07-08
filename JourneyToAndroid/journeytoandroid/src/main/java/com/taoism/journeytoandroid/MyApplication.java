package com.taoism.journeytoandroid;

import android.app.Application;
import android.content.Context;

import com.activeandroid.ActiveAndroid;
import com.taoism.journeytoandroid.utils.applicationutil.AppProfile;

/**
 * Created by chenfei on 14-10-22.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);

        Context context = getApplicationContext();
        AppProfile.sContext = context;

    }


}
