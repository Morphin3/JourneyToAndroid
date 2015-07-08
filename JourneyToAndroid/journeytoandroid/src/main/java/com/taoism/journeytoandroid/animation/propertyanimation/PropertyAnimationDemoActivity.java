package com.taoism.journeytoandroid.animation.propertyanimation;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.taoism.journeytoandroid.R;
import com.taoism.journeytoandroid.utils.screenutil.ScreenUtil;
import com.taoism.journeytoandroid.utils.viewsizeutil.ViewSizeUtil;

/**
 * Date: 2015-06-30
 * Time: 23:20
 * Author: cf
 * -----------------------------
 * FIXME
 */
public class PropertyAnimationDemoActivity extends Activity {

    private EditText et_bottom;

    private Button btn_begin_animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation_demo);
        et_bottom = (EditText) findViewById(R.id.et_bottom);
        btn_begin_animation = (Button) findViewById(R.id.btn_begin_animation);


        btn_begin_animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation();
            }
        });


    }


    private void startAnimation() {
        ViewWrapper wrapper = new ViewWrapper(et_bottom);
        int screenWidth = ScreenUtil.getDisplayWidth();
        int viewWidth = ViewSizeUtil.getViewWidth(et_bottom);
        float start = (screenWidth + viewWidth);
        float end = screenWidth;

//        float start =(float)(screenWidth + et_bottom.getWidth());
//        float end = (float)screenWidth;
//        ObjectAnimator.ofFloat(wrapper, "translationX", 5000f, 0f).setDuration(5000).start();
        ObjectAnimator.ofFloat(wrapper, "translationX", screenWidth, 0).setDuration(1000).start();
//        ObjectAnimator.ofInt(wrapper, "X", 500).setDuration(5000).start();
    }


    private static class ViewWrapper {
        private View mTarget;


        public ViewWrapper(View target) {
            mTarget = target;
        }

        public float getTranslationX() {
            return mTarget.getTranslationX();
        }

        public void setTranslationX(float translationX) {
            mTarget.setTranslationX(translationX);
        }

        public float getTranslationY() {
            return mTarget.getTranslationY();
        }

        public void setTranslationY(float translationY) {
            mTarget.setTranslationY(translationY);
        }


        public float getX() {
            return mTarget.getX();
        }

        public void setX(float X) {
            mTarget.setX(X);
        }


//        public int getWidth() {
//            return mTarget.getLayoutParams().width;
//        }
//
//        public void setWidth(int width) {
//            mTarget.getLayoutParams().width = width;
//            mTarget.requestLayout();
//        }
    }

}




