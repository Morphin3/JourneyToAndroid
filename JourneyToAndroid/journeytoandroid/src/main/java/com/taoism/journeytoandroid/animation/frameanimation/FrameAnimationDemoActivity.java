package com.taoism.journeytoandroid.animation.frameanimation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.TextView;

import com.taoism.journeytoandroid.R;

/**
 * Created by Morphin3 on 5/5/15.
 */
public class FrameAnimationDemoActivity extends Activity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_animation_demo);
        tv=(TextView)findViewById(R.id.tv);


        AnimationDrawable anim = (AnimationDrawable)getResources().getDrawable(R.drawable.anim_frame);
        tv.setText("背景渐变动画效果");
        tv.setBackgroundDrawable(anim);
        anim.start();

    }
}
