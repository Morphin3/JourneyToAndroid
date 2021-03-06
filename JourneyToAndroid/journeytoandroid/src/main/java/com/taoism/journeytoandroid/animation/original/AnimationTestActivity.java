package com.taoism.journeytoandroid.animation.original;

import android.app.Activity;
import android.graphics.Interpolator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.taoism.journeytoandroid.R;
import com.taoism.journeytoandroid.interfaces.InitializeInterface;
import com.taoism.journeytoandroid.utils.Constant;

/**
 * Created by Morphin3 on 1/29/15.
 */
public class AnimationTestActivity extends Activity implements InitializeInterface,View.OnClickListener {

    private Button btn_start;
    private Button btn_stop;
    private FrameLayout fl_countdown;
    private ImageView iv_countdown_back;
    private ImageView iv_countdown_front;
    private TextView tv_countdown;

    private CountDownTimer mCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_test);

        initCustom();
        initListener();
    }

    @Override
    public void initCustom(){
        btn_start=(Button)findViewById(R.id.btn_start);
        btn_stop=(Button)findViewById(R.id.btn_stop);
        fl_countdown=(FrameLayout)findViewById(R.id.fl_countdown);
        iv_countdown_back=(ImageView)findViewById(R.id.iv_countdown_back);
        iv_countdown_front=(ImageView)findViewById(R.id.iv_countdown_front);
        tv_countdown=(TextView)findViewById(R.id.tv_countdown);

    }

    @Override
    public void initListener() {
        btn_start.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int rid=v.getId();
        if(rid==R.id.btn_start){
            fl_countdown.setVisibility(View.VISIBLE);

            Animation alphaCountdown=AnimationUtils.loadAnimation(this,R.anim.alpha_countdown);
            CycleInterpolator cycleInterpolator=new CycleInterpolator(0.5f);
            alphaCountdown.setInterpolator(cycleInterpolator);
            iv_countdown_back.startAnimation(alphaCountdown);

            Animation rotateCountdown= AnimationUtils.loadAnimation(this,R.anim.rotate_countdown);
            LinearInterpolator linearInterpolator=new LinearInterpolator();
            rotateCountdown.setInterpolator(linearInterpolator);
            rotateCountdown.setStartOffset(0);
            iv_countdown_front.startAnimation(rotateCountdown);

            mCountDownTimer=new CountDownTimer(Constant.Time.SECOND*60,Constant.Time.SECOND){
                @Override
                public void onTick(long millisUntilFinished) {
                    tv_countdown.setText(millisUntilFinished/1000+"");
                }

                @Override
                public void onFinish() {
                    fl_countdown.setVisibility(View.GONE);
                }
            }.start();
        }else if(rid==R.id.btn_stop){
            iv_countdown_back.clearAnimation();
            iv_countdown_front.clearAnimation();
            mCountDownTimer.cancel();
        }
    }
}
