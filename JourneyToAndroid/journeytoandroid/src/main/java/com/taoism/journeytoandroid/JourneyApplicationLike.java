package com.taoism.journeytoandroid;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;

import com.activeandroid.ActiveAndroid;
import com.taoism.journeytoandroid.tinker.Log.MyLogImp;
import com.taoism.journeytoandroid.tinker.util.TinkerManager;
import com.taoism.journeytoandroid.utils.applicationutil.AppProfile;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Date: 2017-03-15
 * Time: 10:29
 * Author: cf
 * -----------------------------
 */


@SuppressWarnings("unused")
@DefaultLifeCycle(application = "com.taoism.journeytoandroid.JourneyApplication",
        flags = ShareConstants.TINKER_ENABLE_ALL,
        loadVerifyFlag = false)
public class JourneyApplicationLike extends DefaultApplicationLike {


    public static Application application = null;
    public static Context context = null;

    public JourneyApplicationLike(Application application,
                                  int tinkerFlags,
                                  boolean tinkerLoadVerifyFlag,
                                  long applicationStartElapsedTime,
                                  long applicationStartMillisTime,
                                  Intent tinkerResultIntent) {

        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);

    }

    @Override
    public void onCreate() {
        super.onCreate();

        ActiveAndroid.initialize(getApplication());
        Context context = getApplication().getApplicationContext();
        AppProfile.sContext = context;

    }


    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);

        //you must install multiDex whatever tinker is installed!
        MultiDex.install(base);

        application = getApplication();
        context = getApplication();

        TinkerManager.setTinkerApplicationLike(this);

        TinkerManager.initFastCrashProtect();
        //should set before tinker is installed
        TinkerManager.setUpgradeRetryEnable(true);

        //optional set logIml, or you can use default debug log
        TinkerInstaller.setLogIml(new MyLogImp());

        //installTinker after load multiDex
        //or you can put com.tencent.tinker.** to main dex
        TinkerManager.installTinker(this);

        Tinker tinker = Tinker.with(getApplication());





    }


    public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback) {
        getApplication().registerActivityLifecycleCallbacks(callback);
    }
}
