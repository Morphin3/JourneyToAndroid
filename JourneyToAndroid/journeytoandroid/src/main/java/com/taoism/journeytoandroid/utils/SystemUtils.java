package com.taoism.journeytoandroid.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Morphin3 on 12/18/14.
 */
public class SystemUtils {
    /**
     * 显示Toast
     *
     * @param context
     * @param message
     */
    public static void showToast(Context context, String message) {
        SystemUtils.showToast(context, message, Gravity.BOTTOM);
    }

    /**
     * 显示Toast
     *
     * @param context
     * @param message
     * @param gravity
     */
    public static void showToast(Context context, String message, int gravity) {
        if (context == null || TextUtils.isEmpty(message)) {
            return;
        }

        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setGravity(gravity, 0, 0);
        toast.show();
    }

    /**
     * @param context
     * @param tel
     */
    public static void makeCall(Context context, String tel) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel));
        if (isIntentAvailable(context, intent)) {
            context.startActivity(intent);
        } else {
            SystemUtils.showToast(context, "请检查您的手机是否有拨号应用");
        }
    }

    /**
     * 检查是否存在相应的Intent
     *
     * @param context
     * @param intent
     * @return
     */
    public static boolean isIntentAvailable(Context context, Intent intent) {
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);

        return list.size() > 0;
    }

    /**
     * dp转为px
     *
     * @param dp
     * @return
     */
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }


//    public static boolean checkPhoneInUse(Context context){
//        boolean phoneInUse = false;
//        try {
//            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//
//            Method m1 = tm.getClass().getDeclaredMethod("getITelephony");
//            m1.setAccessible(true);
//            Object iTelephony = m1.invoke(tm);
//
//            Method m2 = iTelephony.getClass().getDeclaredMethod("silenceRinger");
//            Method m3 = iTelephony.getClass().getDeclaredMethod("endCall");
//            Method m4 = iTelephony.getClass().getDeclaredMethod("Stub");
//
//            m2.invoke(iTelephony);
//            m3.invoke(iTelephony);
//
//            iTelephony
//
//            ITelephony phone = ITelephony.Stub.asInterface(ServiceManager.checkService("phone"));
//            if (phone != null) phoneInUse = !phone.isIdle();
//
////            ITelephony phone = ITelephony.Stub.asInterface(ServiceManager.checkService("phone"));
////            if (phone != null) phoneInUse = !phone.isIdle();
//        } catch (RemoteException e) {
//            Log.w(Constant.APP_TAG, "phone.isIdle() failed", e);
//        } catch (NoSuchMethodException e){
//            Log.w(Constant.APP_TAG, "No Such Method", e);
//        }catch(Exception e){
//
//        }
//        return phoneInUse;
//    }
}
