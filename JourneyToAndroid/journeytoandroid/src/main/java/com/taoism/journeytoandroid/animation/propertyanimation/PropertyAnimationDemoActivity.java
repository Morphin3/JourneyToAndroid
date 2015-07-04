package com.taoism.journeytoandroid.animation.propertyanimation;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.taoism.journeytoandroid.R;

/**
 * Date: 2015-06-30
 * Time: 23:20
 * Author: cf
 * -----------------------------
 * FIXME
 */
public class PropertyAnimationDemoActivity extends Activity {

    private EditText et_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation_demo);
        et_bottom =(EditText)findViewById(R.id.et_bottom);
        ViewWrapper wrapper = new ViewWrapper(et_bottom);
        ObjectAnimator.ofInt(wrapper, "translationY", 500).setDuration(5000).start();

    }

    private static class ViewWrapper{
        private View mTarget;

        public ViewWrapper(View target) {
            mTarget = target;
        }

        public float getTranslationY(){
            return mTarget.getTranslationY();
        }

        public void setScrollY(float translationY){
            mTarget.setTranslationY(translationY);

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




