package com.taoism.journeytoandroid.utils.resourceutil;

import android.content.ContentResolver;
import android.content.res.Resources;
import android.net.Uri;

import com.taoism.journeytoandroid.utils.applicationutil.AppProfile;

/**
 * Date: 2015-07-21
 * Time: 21:35
 * Author: cf
 * -----------------------------
 */
public class ResourcesUtil {
    /**
     * Created by zyl06 on 6/18/15.
     */
    public static String getString(int stringId) {
        return AppProfile.getContext().getResources().getString(stringId);
    }

    public static String[] getStringArray(int arrayId) {
        return AppProfile.getContext().getResources().getStringArray(arrayId);
    }

    public static String stringFormat(int stringId, Object... objects) {
        String formatString = getString(stringId);
        return String.format(formatString, objects);
    }

    public static int getColor(int colorId) {
        return AppProfile.getContext().getResources().getColor(colorId);
    }

    public static float getDimen(int dimenId) {
        return AppProfile.getContext().getResources().getDimension(dimenId);
    }

    public static int getDimenPxSize(int dimenId) {
        return AppProfile.getContext().getResources().getDimensionPixelSize(dimenId);
    }

//    public static Drawable getDrawable(int drawableID) {
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
//            return SDK22.getDrawable(drawableID);
//        } else {
//            return AppProfile.getContext().getResources().getDrawable(drawableID);
//        }
//    }
//
//    @TargetApi(Build.VERSION_CODES.LOLLIPOP_MR1)
//    static class SDK22 {
//        public static Drawable getDrawable(int drawableID) {
//            return AppProfile.getContext().getResources().getDrawable(drawableID, null);
//        }
//    }

    public static Uri getUri(int resourceId) {
        Resources r = AppProfile.getContext().getResources();
        String url = ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + r.getResourcePackageName(resourceId) + "/"
                + r.getResourceTypeName(resourceId) + "/"
                + r.getResourceEntryName(resourceId);
        return Uri.parse(url);
    }

    public static Resources getResources() {
        return AppProfile.getContext().getResources();
    }
}


