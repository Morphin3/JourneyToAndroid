package com.taoism.journeytoandroid.animation.original;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.taoism.journeytoandroid.R;

/**
 * Created by Morphin3 on 1/29/15.
 */
public class AnimationTestActivity extends Activity {

    private ImageView iv_countdown_front;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_test);

        initCustom();
    }

    private void initCustom(){
        iv_countdown_front=(ImageView)findViewById(R.id.iv_countdown_front);

        Animation countdown= AnimationUtils.loadAnimation(this,R.anim.countdown);
        iv_countdown_front.startAnimation(countdown);
    }
}
