package com.taoism.journeytoandroid.animation.propertyanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.taoism.journeytoandroid.R;
import com.taoism.journeytoandroid.utils.screenutil.ScreenUtil;

/**
 * Date: 2015-08-07
 * Time: 22:31
 * Author: cf
 * -----------------------------
 */
public class BounceAnimationActivity extends Activity {

    private ImageView mIvCancelPublish;
    private RelativeLayout mRlTopic, mRlIllustration, mRlCos, mRlArticle;
    private ImageView mIvTopic, mIvIllustration, mIvCos, mIvArticle;
    private TextView mTvTitle;
    private TextView mTvTopic, mTvIllustration, mTvCos, mTvArticle;

    private View mVFake;

    ValueAnimator animInTopic = null;
    ValueAnimator animInIllustration = null;
    ValueAnimator animInCos = null;
    ValueAnimator animInArticle = null;

    ValueAnimator animOutTopic = null;
    ValueAnimator animOutIllustration = null;
    ValueAnimator animOutCos = null;
    ValueAnimator animOutArticle = null;

    ValueAnimator animPressedTopic = null;
    ValueAnimator animPressedIllustration = null;
    ValueAnimator animPressedCos = null;
    ValueAnimator animPressedArticle = null;

    ValueAnimator animUnPressedTopic = null;
    ValueAnimator animUnPressedIllustration = null;
    ValueAnimator animUnPressedCos = null;
    ValueAnimator animUnPressedArticle = null;

    Animator mAnimator;
    AnimatorSet mAnimatorSet = new AnimatorSet();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bounce_animation);

        mRlTopic = (RelativeLayout) findViewById(R.id.rl_topic);
        mRlIllustration = (RelativeLayout) findViewById(R.id.rl_illustration);
        mRlCos = (RelativeLayout) findViewById(R.id.rl_cos);
        mRlArticle = (RelativeLayout) findViewById(R.id.rl_article);
        mIvTopic = (ImageView) findViewById(R.id.iv_topic);
        mIvIllustration = (ImageView) findViewById(R.id.iv_illustration);
        mIvCos = (ImageView) findViewById(R.id.iv_cos);
        mIvArticle = (ImageView) findViewById(R.id.iv_article);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mIvCancelPublish = (ImageView) findViewById(R.id.iv_cancel_publish);
        mTvTopic = (TextView) findViewById(R.id.tv_topic);
        mTvIllustration = (TextView) findViewById(R.id.tv_illustration);
        mTvCos = (TextView) findViewById(R.id.tv_cos);
        mTvArticle = (TextView) findViewById(R.id.tv_article);
        mVFake = (View) findViewById(R.id.v_fake);


        findViewById(R.id.iv_cancel_publish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startOutAnimation();
            }
        });


        mIvTopic.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    animPressedTopic.start();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    animUnPressedTopic.start();
                }
                return true;
            }
        });

        mIvIllustration.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    animPressedIllustration.start();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    animUnPressedIllustration.start();
                }
                return true;
            }
        });

        mIvCos.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    animPressedCos.start();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    animUnPressedCos.start();
                }
                return true;
            }
        });

        mIvArticle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    animPressedArticle.start();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    animUnPressedArticle.start();
                }
                return true;
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            if (mRlArticle.getVisibility() == View.INVISIBLE) {
                startInAnimation();
//            Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
//
//                /**
//
//                 * 返回值boolean 意思是needKeep
//
//                 * true，表示要保留保留， 代表不移除这个idleHandler，可以反复执行
//
//                 * false代表执行完毕之后就移除这个idleHandler, 也就是只执行一次
//
//                 */
//
//                @Override
//
//                public boolean queueIdle() {
//                    startInAnimation();
//                    return false;
//
//                }
//            });
            }
            createPressedAnimation();
        }
    }


    private void createInAnimation() {
//        float yOff = ScreenUtil.getDisplayHeight() / 2;
        float yOffFull = ScreenUtil.getDisplayHeight();
        if (animInTopic == null) {
            animInTopic = ObjectAnimator.ofFloat(mRlTopic, "translationY",
                    -yOffFull, 0).setDuration(700);
            animInTopic.setStartDelay(180);
//            animInTopic.setInterpolator(new AccelerateInterpolator());
            animInTopic.setInterpolator(new OvershootInterpolator(1.5f));
            animInTopic.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    mRlTopic.setVisibility(View.VISIBLE);
                }

            });
        }

        if (animInIllustration == null) {
            animInIllustration = ObjectAnimator.ofFloat(mRlIllustration, "translationY",
                    -yOffFull, 0).setDuration(700);
            animInIllustration.setStartDelay(120);
//            animInIllustration.setInterpolator(new AccelerateInterpolator());
            animInIllustration.setInterpolator(new OvershootInterpolator(1.5f));
            animInIllustration.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    mRlIllustration.setVisibility(View.VISIBLE);
                }
            });
        }

        if (animInCos == null) {
            animInCos = ObjectAnimator.ofFloat(mRlCos, "translationY",
                    -yOffFull, 0).setDuration(700);
            animInCos.setStartDelay(60);
//            animInCos.setInterpolator(new AccelerateInterpolator());
            animInCos.setInterpolator(new OvershootInterpolator(1.5f));
            animInCos.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    mRlCos.setVisibility(View.VISIBLE);
                }
            });
        }

        if (animInArticle == null) {
            animInArticle = ObjectAnimator.ofFloat(mRlArticle, "translationY",
                    -yOffFull, 0).setDuration(700);
            animInArticle.setStartDelay(0);
//            animInArticle.setInterpolator(new AccelerateInterpolator());
            animInArticle.setInterpolator(new OvershootInterpolator(1.5f));
            animInArticle.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    mRlArticle.setVisibility(View.VISIBLE);

                }
            });
        }

    }

    private void startInAnimation() {
        createInAnimation();
//        animInTopic.start();
//        animInIllustration.start();
//        animInCos.start();
//        animInArticle.start();

        mAnimator = new AnimatorSet();
        ((AnimatorSet) mAnimator).playTogether(animInArticle, animInCos, animInIllustration, animInTopic);
        mAnimator.start();
//        mAnimatorSet = new AnimatorSet();
//        mAnimatorSet.playTogether(animInTopic, animInIllustration, animInCos, animInArticle, animPressedTopic, animPressedTopic);
    }



    private void createOutAnimation() {
        if (animOutTopic == null) {
            animOutTopic = ObjectAnimator.ofFloat(mRlTopic, "translationY",
                    0, -ScreenUtil.getDisplayHeight()).setDuration(500);
            animOutTopic.setStartDelay(0);
            animOutTopic.setInterpolator(new AccelerateInterpolator());
        }

        if (animOutIllustration == null) {
            animOutIllustration = ObjectAnimator.ofFloat(mRlIllustration, "translationY",
                    0, -ScreenUtil.getDisplayHeight()).setDuration(500);
            animOutIllustration.setStartDelay(30);
            animOutIllustration.setInterpolator(new AccelerateInterpolator());
        }

        if (animOutCos == null) {
            animOutCos = ObjectAnimator.ofFloat(mRlCos, "translationY",
                    0, -ScreenUtil.getDisplayHeight()).setDuration(500);
            animOutCos.setStartDelay(0);
            animOutCos.setInterpolator(new AccelerateInterpolator());
        }

        if (animOutArticle == null) {
            animOutArticle = ObjectAnimator.ofFloat(mRlArticle, "translationY",
                    0, -ScreenUtil.getDisplayHeight()).setDuration(500);
            animOutArticle.setStartDelay(30);
            animOutArticle.setInterpolator(new AccelerateInterpolator());
            animOutArticle.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    finish();
                }
            });
        }
    }

    private void startOutAnimation() {
        createOutAnimation();
        mAnimator = new AnimatorSet();
        ((AnimatorSet) mAnimator).playTogether(animOutTopic, animOutIllustration, animOutCos,animOutArticle);
        mAnimator.start();

    }

    private void createPressedAnimation() {
        float scale = mIvTopic.getScaleX();
        float scaleBig = mIvTopic.getScaleX() * 1.1f;

        PropertyValuesHolder pvhScaleXBigger = PropertyValuesHolder.ofFloat("scaleX", scale,
                scaleBig);
        PropertyValuesHolder pvhScaleYBigger = PropertyValuesHolder.ofFloat("scaleY", scale,
                scaleBig);


        PropertyValuesHolder pvhScaleXSmaller = PropertyValuesHolder.ofFloat("scaleX", scaleBig,
                scale);
        PropertyValuesHolder pvhScaleYSmaller = PropertyValuesHolder.ofFloat("scaleY", scaleBig,
                scale);

        if (animPressedTopic == null) {
            animPressedTopic = ObjectAnimator.ofPropertyValuesHolder(mIvTopic, pvhScaleXBigger, pvhScaleYBigger)
                    .setDuration(100);
        }
        if (animPressedIllustration == null) {

            animPressedIllustration = ObjectAnimator.ofPropertyValuesHolder(mIvIllustration, pvhScaleXBigger, pvhScaleYBigger)
                    .setDuration(100);
        }

        if (animPressedCos == null) {
            animPressedCos = ObjectAnimator.ofPropertyValuesHolder(mIvCos, pvhScaleXBigger, pvhScaleYBigger)
                    .setDuration(100);
        }

        if (animPressedArticle == null) {

            animPressedArticle = ObjectAnimator.ofPropertyValuesHolder(mIvArticle, pvhScaleXBigger, pvhScaleYBigger)
                    .setDuration(100);
        }


        if (animUnPressedTopic == null) {
            animUnPressedTopic = ObjectAnimator.ofPropertyValuesHolder(mIvTopic, pvhScaleXSmaller, pvhScaleYSmaller)
                    .setDuration(100);
        }

        if (animUnPressedIllustration == null) {
            animUnPressedIllustration = ObjectAnimator.ofPropertyValuesHolder(mIvIllustration, pvhScaleXSmaller, pvhScaleYSmaller)
                    .setDuration(100);
        }


        if (animUnPressedCos == null) {
            animUnPressedCos = ObjectAnimator.ofPropertyValuesHolder(mIvCos, pvhScaleXSmaller, pvhScaleYSmaller)
                    .setDuration(100);
        }


        if (animUnPressedArticle == null) {
            animUnPressedArticle = ObjectAnimator.ofPropertyValuesHolder(mIvArticle, pvhScaleXSmaller, pvhScaleYSmaller)
                    .setDuration(100);
        }

    }



}
