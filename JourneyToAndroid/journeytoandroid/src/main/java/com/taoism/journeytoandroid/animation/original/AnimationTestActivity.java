package com.taoism.journeytoandroid.animation.original;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.taoism.journeytoandroid.R;
import com.taoism.journeytoandroid.utils.Constant;

/**
 * Created by Morphin3 on 1/29/15.
 */
public class AnimationTestActivity extends Activity {

    private FrameLayout fl_countdown;
    private ImageView iv_countdown_front;
    private TextView tv_countdown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_test);

        initCustom();
    }

    private void initCustom(){
        fl_countdown=(FrameLayout)findViewById(R.id.fl_countdown);
        iv_countdown_front=(ImageView)findViewById(R.id.iv_countdown_front);
        tv_countdown=(TextView)findViewById(R.id.tv_countdown);

        Animation countdown= AnimationUtils.loadAnimation(this,R.anim.countdown);
        iv_countdown_front.startAnimation(countdown);

        new CountDownTimer(Constant.Time.SECOND*60,Constant.Time.SECOND){
            @Override
            public void onTick(long millisUntilFinished) {
                tv_countdown.setText(millisUntilFinished/1000+"");
            }

            @Override
            public void onFinish() {
                fl_countdown.setVisibility(View.GONE);
            }
        }.start();
    }
}
