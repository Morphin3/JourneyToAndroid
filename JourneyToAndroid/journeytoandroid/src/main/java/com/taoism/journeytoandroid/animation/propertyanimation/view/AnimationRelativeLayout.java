package com.taoism.journeytoandroid.animation.propertyanimation.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Date: 2015-08-08
 * Time: 16:51
 * Author: cf
 * -----------------------------
 */
public class AnimationRelativeLayout extends RelativeLayout {
    public AnimationRelativeLayout(Context context) {
        super(context);
    }

    public AnimationRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimationRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AnimationRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


}
