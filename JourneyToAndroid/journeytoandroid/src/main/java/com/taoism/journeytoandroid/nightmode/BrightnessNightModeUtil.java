package com.taoism.journeytoandroid.nightmode;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.Settings;

import com.taoism.journeytoandroid.utils.applicationutil.AppProfile;

/**
 * Date: 2016-05-19
 * Time: 11:33
 * Author: cf
 * -----------------------------
 */
public class BrightnessNightModeUtil {

    public static int getSystemBrightness() {
        int brightnessValue = -1;
        try {
            brightnessValue = Settings.System.getInt(AppProfile.getContext().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return brightnessValue;
    }

    public static boolean isAutoBrightness() {
        boolean autoBrightness = false;
        try {
            autoBrightness = Settings.System.getInt(AppProfile.getContext().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE) ==
                    Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return autoBrightness;
    }


    /**
     * 关闭自动亮度
     */
    public static void closeAutoBrightness() {
        Settings.System.putInt(AppProfile.getContext().getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
    }

    /**
     * 开启自动亮度
     */
    public static void openAutoBrightness() {
        Settings.System.putInt(AppProfile.getContext().getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
    }

    public static void setBrightness(int brightnessValue) {
        ContentResolver contentResolver = AppProfile.getContext().getContentResolver();
        Uri uri = android.provider.Settings.System.getUriFor("screen_brightness");
        Settings.System.putInt(contentResolver,
                "screen_brightness",
                brightnessValue);
        contentResolver.notifyChange(uri, null);
    }


}
