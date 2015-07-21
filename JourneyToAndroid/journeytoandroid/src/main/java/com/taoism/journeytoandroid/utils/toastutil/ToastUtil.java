package com.taoism.journeytoandroid.utils.toastutil;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.taoism.journeytoandroid.R;
import com.taoism.journeytoandroid.utils.applicationutil.AppProfile;
import com.taoism.journeytoandroid.utils.resourceutil.ResourcesUtil;

/**
 * Date: 2015-07-21
 * Time: 21:35
 * Author: cf
 * -----------------------------
 */
public class ToastUtil {
    // 显示短提示
    public static void makeShortToast(String content) {
        Toast.makeText(AppProfile.getContext(), content, Toast.LENGTH_SHORT).show();
    }

    // 显示短提示
    public static void makeShortToast(int stringId) {
        String content = ResourcesUtil.getString(stringId);
        Toast.makeText(AppProfile.getContext(), content, Toast.LENGTH_SHORT).show();
    }

    // 显示短提示
    public static void makeShortToast(int stringId, Object... objects) {
        String content = ResourcesUtil.stringFormat(stringId, objects);
        Toast.makeText(AppProfile.getContext(), content, Toast.LENGTH_SHORT).show();
    }

    // 显示长提示
    public static void makeLongToast(String content) {
        Toast.makeText(AppProfile.getContext(), content, Toast.LENGTH_LONG).show();
    }

    // 显示长提示
    public static void makeLongToast(int stringId) {
        String content = ResourcesUtil.getString(stringId);
        Toast.makeText(AppProfile.getContext(), content, Toast.LENGTH_LONG).show();
    }

    // 显示长提示
    public static void makeLongToast(int stringId, Object... objects) {
        String content = ResourcesUtil.stringFormat(stringId, objects);
        Toast.makeText(AppProfile.getContext(), content, Toast.LENGTH_LONG).show();
    }


/**
 * Date: 2015-06-17
 * Time: 10:28
 * Author: cf
 * -----------------------------
 * FIXME
 */
    /**
     * 显示Toast
     *
     * @param context
     * @param message
     */
    public static void showToast(Context context, String message) {
        ToastUtil.showToast(context, message, Gravity.BOTTOM);
    }


    /**
     * 在中央显示Toast
     *
     * @param context
     * @param message
     */
    public static void showToastInCenter(Context context, String message) {
        ToastUtil.showToast(context, message, Gravity.CENTER);
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


    public static void makeDrawableToastPositive(String msg) {
        makeDrawableToast(msg,R.drawable.ic_toast_positive,Gravity.CENTER,Toast.LENGTH_SHORT);

//        Toast toast;
//        toast = Toast.makeText(AppProfile.getContext(),msg, Toast.LENGTH_LONG);
//        toast.setGravity(Gravity.CENTER, 0, 0);
//        LinearLayout toastView = (LinearLayout) toast.getView();
//        ImageView imageCodeProject = new ImageView(AppProfile.getContext());
//        imageCodeProject.setImageResource(R.drawable.ic_toast_positive);
//        toastView.addView(imageCodeProject,0);
//        toast.show();
    }

    public static void makeDrawableToastNegative(String msg) {
        makeDrawableToast(msg,R.drawable.ic_toast_negative,Gravity.CENTER,Toast.LENGTH_SHORT);
    }



    public static void makeDrawableToast(String msg,int leftDrawRes,int gravity,int duration){
        LayoutInflater layoutInflater = LayoutInflater.from(AppProfile.getContext());
        View view = layoutInflater.inflate(R.layout.view_custom_toast, null, false);

        ImageView drawableLeft = (ImageView) view.findViewById(R.id.iv_drawable_left);
        TextView content = (TextView) view.findViewById(R.id.tv_content);
        drawableLeft.setImageResource(leftDrawRes);
        content.setText(msg);
        Toast toast = new Toast(AppProfile.getContext());
        toast.setGravity(gravity, 0, 0);
        toast.setDuration(duration);
        toast.setView(view);
        toast.show();
    }


}
