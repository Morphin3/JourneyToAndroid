package com.taoism.journeytoandroid.animation.propertyanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

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

    private LinearLayout ll_container;
    private EditText et_2;
    private Button btn_2;

    private LayoutTransition mTransitioner;

    int mScreenWidth = ScreenUtil.getDisplayWidth();

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

        ll_container = (LinearLayout)findViewById(R.id.ll_container);
        et_2 = (EditText)findViewById(R.id.et_2);
        btn_2 = (Button)findViewById(R.id.btn_2);

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_2.setVisibility(et_2.getVisibility()==View.VISIBLE?View.INVISIBLE:View.VISIBLE);
            }
        });


        mTransitioner = new LayoutTransition();
        ll_container.setLayoutTransition(mTransitioner);


        // Adding
//        ObjectAnimator animIn = ObjectAnimator.ofFloat(null, "rotationY", 90f, 0f).
        ObjectAnimator animIn = ObjectAnimator.ofFloat(null, "translationX", mScreenWidth, 0f).
                setDuration(mTransitioner.getDuration(1000));
        animIn.setInterpolator(new DecelerateInterpolator());
        mTransitioner.setAnimator(LayoutTransition.APPEARING, animIn);
        animIn.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setRotationY(0f);
            }
        });

        // Removing
//        ObjectAnimator animOut = ObjectAnimator.ofFloat(null, "rotationX", 0f, 90f).
        ObjectAnimator animOut = ObjectAnimator.ofFloat(null, "translationX", 0f, mScreenWidth).
                setDuration(mTransitioner.getDuration(1000));
        mTransitioner.setAnimator(LayoutTransition.DISAPPEARING, animOut);
        animOut.setInterpolator(new AccelerateInterpolator());
        animOut.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setRotationX(0f);
            }
        });




    }

    private void startAnimation() {
        ViewWrapper wrapper = new ViewWrapper(et_bottom);
        int viewWidth = ViewSizeUtil.getViewWidth(et_bottom);
        float start = (mScreenWidth + viewWidth);
        float end = mScreenWidth;

//        float start =(float)(screenWidth + et_bottom.getWidth());
//        float end = (float)screenWidth;
//        ObjectAnimator.ofFloat(wrapper, "translationX", 5000f, 0f).setDuration(5000).start();


        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(wrapper, "translationX", mScreenWidth, 0).setDuration(1000);
        objectAnimator.setInterpolator(new DecelerateInterpolator());
        objectAnimator.start();
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




