package com.taoism.journeytoandroid.customview.videocutseekview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.taoism.journeytoandroid.R;

/**
 * Date: 2017-04-01
 * Time: 10:20
 * Author: cf
 * -----------------------------
 */

public class VideoSeekView extends ViewGroup {

    public interface SeekListener {

        void onStartSeek();

        void onSeek(VideoSeekView videoSeekView,
                    int currentTime,
                    int totalTime,
                    int minTime,
                    float currentTimeAxisWidth,
                    float totalTimeAxisWidth,
                    float minTimeAxisWidth);

        void onStopSeek();
    }

    private static final String TAG = VideoSeekView.class.getSimpleName();

//    private FrameRangeView mFrameRangeView;

    private RecyclerView mRecyclerView;

    private FrameRangeView mFrameRangeView;

    private Drawable mLeftThumbDrawable;

    private Drawable mRightThumbDrawable;

    private int mBorderColor;

    private float mBorderWidth;

    private int mTotalTime;
    private int mCurrentTime;
    private int mMinTime;
    private int mDuration;

    private final int default_frame_color = Color.rgb(0x49, 0xC1, 0x20);
    private final float default_frame_width;
    private Drawable default_right_thumb_drawable;
    private int default_total_time = 60 * SECOND;
    private int default_min_time = 2 * SECOND;
    private int default_duration = default_total_time;
    private int default_current_time = default_duration;

    private static final int SECOND = 1000;

    private static final int TOP = 0x15;
    private static final int LEFT = 0x16;
    private static final int BOTTOM = 0x17;
    private static final int RIGHT = 0x18;
    private static final int LEFT_TOP = 0x11;
    private static final int RIGHT_TOP = 0x12;
    private static final int LEFT_BOTTOM = 0x13;
    private static final int RIGHT_BOTTOM = 0x14;
    private static final int CENTER = 0x19;
    private static final int NONE = 0x20;


    private int dragDirection;

    protected int lastX;
    protected int lastY;

    private int oriLeft;
    private int oriRight;
    private int oriTop;
    private int oriBottom;

    private int offset = 0;

    private int mDefualtWidth = 500, mDefaultHeight = 100;

    private float mTotalTimeAxisWidth;
    private float mCurrentTimeAxisWidth;
    private float mMinTimeAxisWidth;
    private float mDurationTimeAxisWidth;
    private float mActualTimeAxisWidth;

    private float mInitialTimeAxisWidth = 0;


    private SeekListener mInnerSeekListener;

    private SeekListener mSeekListener;

    private int mFrameRangeViewOldWidth = 0;
    private int mFrameRangeViewOldHeight = 0;

    private float mAdjustedDurationTimeAxisWidth = -1;

    public VideoSeekView(Context context) {
        this(context, null);
    }

    public VideoSeekView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.videoSeekViewStyle);
    }

    public VideoSeekView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        default_frame_width = dp2px(0.5f);

        final TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.VideoSeekView,
                defStyleAttr,
                R.style.VideoSeekView);

        try {

            mLeftThumbDrawable = typedArray.getDrawable(R.styleable.VideoSeekView_leftThumbDrawable);
            mRightThumbDrawable = typedArray.getDrawable(R.styleable.VideoSeekView_rightThumbDrawable);
            mBorderColor = typedArray.getColor(R.styleable.VideoSeekView_borderColor, default_frame_color);
            mBorderWidth = typedArray.getDimension(R.styleable.VideoSeekView_borderWidth, default_frame_width);
            mTotalTime = typedArray.getInteger(R.styleable.VideoSeekView_totalTime, default_total_time);
            mCurrentTime = typedArray.getInteger(R.styleable.VideoSeekView_currentTime, default_current_time);
            mMinTime = typedArray.getInteger(R.styleable.VideoSeekView_minTime, default_min_time);
            mDuration = typedArray.getInteger(R.styleable.VideoSeekView_actualDuration, default_duration);

        } finally {
            typedArray.recycle();
        }


        init();

    }


    private void init() {

        if (mLeftThumbDrawable == null) {
            mLeftThumbDrawable = default_right_thumb_drawable = getResources().getDrawable(R.drawable.ic_right_thumb);
        }

        addFrameRangeView();

        mFrameRangeView.setOnTouchListener(mFrameRangeView);
        mInnerSeekListener = new SeekListener() {

            @Override
            public void onStartSeek() {
                if (mSeekListener != null) {
                    mSeekListener.onStartSeek();
                }
            }

            @Override
            public void onSeek(VideoSeekView videoSeekView, int currentTime, int totalTime, int minTime, float currentTimeAxisWidth, float totalTimeAxisWidth, float minTimeAxisWidth) {
                Log.i(TAG, "mCurrentTimeAxisWidth:" + mCurrentTimeAxisWidth + "/ mTotalTimeAxisWidth:" + mTotalTimeAxisWidth);
                Log.i(TAG, "current:" + mCurrentTime + "/ total:" + mTotalTime);
                if (mSeekListener != null) {
                    mSeekListener.onSeek(videoSeekView, currentTime, totalTime, minTime, currentTimeAxisWidth, totalTimeAxisWidth, minTimeAxisWidth);
                }
            }

            @Override
            public void onStopSeek() {
                if (mSeekListener != null) {
                    mSeekListener.onStopSeek();
                }
            }
        };
    }

    private void addFrameRangeView() {
        mFrameRangeView = new FrameRangeView(getContext());
        addView(mFrameRangeView);
    }

    private FrameRangeView getFrameRangeView() {
        return mFrameRangeView;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i(TAG, "VideoSeekView onMeasure");

        int width = 0;
        int height = 0;

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        final int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);
            width = Math.max(child.getMeasuredWidth(), width);
            height = Math.max(child.getMeasuredHeight(), height);
        }


        setMeasuredDimension(width, height);

    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        Log.i(TAG, "VideoSeekView onLayout");

        final int parentLeft = getPaddingLeft();
        final int parentRight = r - l - getPaddingRight();
        final int parentTop = getPaddingTop();
        final int parentBottom = b - t - getPaddingBottom();

        int childLeft = parentLeft;
        int childTop = parentTop;

        int rowMaxHeight = 0;

        final int count = getChildCount();

        for (int i = 0; i < count; i++) {

            final View child = getChildAt(i);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();

            if (child instanceof FrameRangeView) {

                if (mFrameRangeViewOldWidth != 0) {
                    width = mFrameRangeViewOldWidth;
                }

                if (mFrameRangeViewOldHeight != 0) {
                    height = mFrameRangeViewOldHeight;
                }

            }

            if (child.getVisibility() != GONE) {
                child.layout(childLeft, childTop, childLeft + width, childTop + height);

                rowMaxHeight = Math.max(rowMaxHeight, height);

            }

        }


    }


    /**
     * 获取触摸点flag
     *
     * @param v
     * @param x
     * @param y
     * @return
     */
    protected int getDirection(View v, int x, int y) {
        int left = v.getLeft();
        int right = v.getRight();
        int bottom = v.getBottom();
        int top = v.getTop();
//        if (x < 40 && y < 40) {
//            return LEFT_TOP;
//        }
//        if (y < 40 && right - left - x < 40) {
//            return RIGHT_TOP;
//        }
//        if (x < 40 && bottom - top - y < 40) {
//            return LEFT_BOTTOM;
//        }
//        if (right - left - x < 40 && bottom - top - y < 40) {
//            return RIGHT_BOTTOM;
//        }
//        if (x < 40) {
//            return LEFT;
//        }
//        if (y < 40) {
//            return TOP;
//        }
//        if (right - left - x < mRightThumbDrawable.getIntrinsicWidth() ||
        if (x > mCurrentTimeAxisWidth &&
                x < mCurrentTimeAxisWidth + mRightThumbDrawable.getIntrinsicWidth()) {
            return RIGHT;
        }

//        if (bottom - top - y < 40) {
//            return BOTTOM;
//        }
        return CENTER;
    }


    public static class LayoutParams extends FrameLayout.LayoutParams {
        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }
    }


    /**
     * 处理拖动事件
     *
     * @param v
     * @param event
     */
    protected void dealDragEvent(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:

                int dx = (int) event.getRawX() - lastX;
                int dy = (int) event.getRawY() - lastY;

                switch (dragDirection) {
//                    case LEFT: // 左边缘
//                        left(v, dx);
//                        break;
                    case RIGHT: // 右边缘
                        right(v, dx);
                        break;
//                    case BOTTOM: // 下边缘
//                        bottom(v, dy);
//                        break;
//                    case TOP: // 上边缘
//                        top(v, dy);
//                        break;
//                    case CENTER: // 点击中心-->>移动
//                        center(v, dx, dy);
//                        break;
//                    case LEFT_BOTTOM: // 左下
//                        left(v, dx);
//                        bottom(v, dy);
//                        break;
//                    case LEFT_TOP: // 左上
//                        left(v, dx);
//                        top(v, dy);
//                        break;
//                    case RIGHT_BOTTOM: // 右下
//                        right(v, dx);
//                        bottom(v, dy);
//                        break;
//                    case RIGHT_TOP: // 右上
//                        right(v, dx);
//                        top(v, dy);
//                        break;
                }
                if (dragDirection != CENTER) {
                    v.layout(oriLeft, oriTop, oriRight, oriBottom);
                    mFrameRangeViewOldWidth = oriRight - oriLeft;
                    mFrameRangeViewOldHeight = oriBottom - oriTop;
                }
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                if (dragDirection == RIGHT) {
                    mInnerSeekListener.onStopSeek();
                }
                dragDirection = NONE;
                break;
        }
    }

    /**
     * 触摸点为右边缘
     *
     * @param v
     * @param dx
     */
    private void right(View v, int dx) {

        mCurrentTimeAxisWidth += dx;
//        View parent = (View) getParent();
        int parentWidth = getWidth();

        if(mAdjustedDurationTimeAxisWidth < 0){
            if (mCurrentTimeAxisWidth > mDurationTimeAxisWidth + offset) {
                mCurrentTimeAxisWidth = mDurationTimeAxisWidth + offset;
            }
        }else{
            if (mCurrentTimeAxisWidth > mAdjustedDurationTimeAxisWidth + offset) {
                mCurrentTimeAxisWidth = mAdjustedDurationTimeAxisWidth + offset;
            }
        }



        if (mCurrentTimeAxisWidth - 2 * offset < mMinTimeAxisWidth) {
            mCurrentTimeAxisWidth = 2 * offset + mMinTimeAxisWidth;
        }

    }


    public void setTotalTime(int totalTime) {
        mTotalTime = totalTime;
    }

    public void setMinTime(int minTime) {
        mMinTime = minTime;
    }

    public void setActualDuration(int duration) {
        mDuration = duration;
    }

    public void setSeekListener(SeekListener seekListener) {
        mSeekListener = seekListener;
    }

    public int getCurrentTime() {
        return mCurrentTime;
    }

    public int getDuration() {
        return mDuration;
    }

    public int getTotalTime() {
        return mTotalTime;
    }

    public float getCurrentTimeAxisWidth() {
        return mCurrentTimeAxisWidth;
    }

    public float getDurationTimeAxisWidth() {
        return mDurationTimeAxisWidth;
    }

    public float getTotalTimeAxisWidth() {
        return mTotalTimeAxisWidth;
    }

    class FrameRangeView extends View implements View.OnTouchListener {


        private Paint mBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        private Paint mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


        private Path mBorderPath = new Path();


        {
            mBorderPaint.setStyle(Paint.Style.STROKE);
            mBorderPaint.setStrokeWidth(mBorderWidth);
            mBorderPaint.setColor(mBorderColor);
            mBackgroundPaint.setStyle(Paint.Style.FILL);
        }


        public FrameRangeView(Context context) {
            super(context);

            setLayoutParams(new VideoSeekView.LayoutParams(
                    VideoSeekView.LayoutParams.MATCH_PARENT,
                    VideoSeekView.LayoutParams.MATCH_PARENT
            ));


        }

//        public FrameRangeView(Context context, @Nullable AttributeSet attrs) {
//            super(context, attrs);
//        }
//
//        public FrameRangeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//            super(context, attrs, defStyleAttr);
//        }
//
//        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//        public FrameRangeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//            super(context, attrs, defStyleAttr, defStyleRes);
//        }


        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

            Log.i(TAG, "FrameRangeView onMeasure");

            final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
            final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
            final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
            final int heightSize = MeasureSpec.getSize(heightMeasureSpec);

            int width = 0;
            int height = 0;

            //Measure Width
            if (widthMode == MeasureSpec.EXACTLY) {
                //Must be this size
                width = widthSize;
            } else if (widthMode == MeasureSpec.AT_MOST) {
                //Can't be bigger than...
                width = Math.min(mDefualtWidth, widthSize);
            } else {
                //Be whatever you want
                width = mDefualtWidth;
            }


            //Measure Height
            if (heightMode == MeasureSpec.EXACTLY) {
                //Must be this size
                height = heightSize;
            } else if (heightMode == MeasureSpec.AT_MOST) {
                //Can't be bigger than...
                height = Math.min(mRightThumbDrawable.getIntrinsicHeight(), heightSize);
            } else {
                //Be whatever you want
                height = mRightThumbDrawable.getIntrinsicHeight();
            }

            setMeasuredDimension(width, height);

            mTotalTimeAxisWidth = mFrameRangeView.getMeasuredWidth() - mRightThumbDrawable.getIntrinsicWidth() - mBorderWidth;
            mMinTimeAxisWidth = ((float) mMinTime / mTotalTime) * mTotalTimeAxisWidth;

            mActualTimeAxisWidth = (float) mDuration / mTotalTime * mTotalTimeAxisWidth;

            if (mDuration > 0 && mDuration < mTotalTime) {

                mDurationTimeAxisWidth = (int) (mActualTimeAxisWidth);
            } else {
                mDurationTimeAxisWidth = mTotalTimeAxisWidth;
            }

            mCurrentTime = getCorrectCurrentTime();

            mCurrentTimeAxisWidth = (int) (
                    ((float) mCurrentTime /
                            mTotalTime)
                            * mTotalTimeAxisWidth);

            if(mInitialTimeAxisWidth == 0){
                mInitialTimeAxisWidth = mCurrentTimeAxisWidth;
            }

            Log.i(TAG, "mMinTimeAxisWidth:" + mMinTimeAxisWidth);


        }


        @Override
        protected void onDraw(Canvas canvas) {

            Log.i(TAG, "FrameRangeView onDraw");

            mBorderPath.reset();

            mBorderPath.moveTo(0, 0);
            mBorderPath.lineTo(mCurrentTimeAxisWidth, 0);
            mBorderPath.moveTo(0, 0);
            mBorderPath.lineTo(0, getHeight());
            mBorderPath.lineTo(mCurrentTimeAxisWidth, getHeight());

            canvas.drawPath(mBorderPath, mBorderPaint);

//            NinePatchDrawable ninePatchRightThumb;
//
//            ninePatchRightThumb = (NinePatchDrawable) mRightThumbDrawable;

            if (mRightThumbDrawable != null) {
                canvas.save();
                canvas.translate(mCurrentTimeAxisWidth, 0);
                mRightThumbDrawable.setBounds(0, 0, mRightThumbDrawable.getIntrinsicWidth(), getHeight());
                mRightThumbDrawable.draw(canvas);
                canvas.restore();
            }

            super.onDraw(canvas);

        }


        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);

            Log.i(TAG, "FrameRangeView onSizeChanged");

//            mBorderPath.reset();
//
//            mBorderPath.moveTo(0, 0);
//            mBorderPath.lineTo(w, 0);
//            mBorderPath.moveTo(0, 0);
//            mBorderPath.lineTo(0, h);
//            mBorderPath.lineTo(w, h);

        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int action = event.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:

                    oriLeft = mFrameRangeView.getLeft();
                    oriRight = mFrameRangeView.getRight();
                    oriTop = mFrameRangeView.getTop();
                    oriBottom = mFrameRangeView.getBottom();

                    lastY = (int) event.getRawY();
                    lastX = (int) event.getRawX();

                    dragDirection = getDirection(mFrameRangeView,
                            (int) event.getX(),
                            (int) event.getY());

                    if(dragDirection == RIGHT){
                        mInnerSeekListener.onStartSeek();
                    }
                    break;
            }

            dealDragEvent(mFrameRangeView, event);

            mFrameRangeView.invalidate();

            if (dragDirection != CENTER) {
                mCurrentTime = (int) ((mCurrentTimeAxisWidth / mTotalTimeAxisWidth * mTotalTime));
                if (mCurrentTime < mMinTime) {
                    mCurrentTime = mMinTime;
                }

                mCurrentTime = getCorrectCurrentTime();

                mInnerSeekListener.onSeek(VideoSeekView.this, mCurrentTime, mTotalTime, mMinTime, mCurrentTimeAxisWidth, mTotalTimeAxisWidth, mMinTimeAxisWidth);
            }

            return dragDirection != CENTER ? true : false;
        }


    }

    private int getCorrectCurrentTime() {
        int minTime = Math.min(mDuration, mTotalTime);

        if (mCurrentTime > minTime) {
            mCurrentTime = minTime;
        }
        return mCurrentTime;
    }


    public float getActualTimeWidth(){
        return mActualTimeAxisWidth;
    }

    public void adjustDurationTimeAxisWidth(float adjustWidth){
        if(adjustWidth == 0){
            mAdjustedDurationTimeAxisWidth = -1;
        }else{
            mAdjustedDurationTimeAxisWidth = mDurationTimeAxisWidth - adjustWidth;
        }
    }



    public float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    public float sp2px(float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                getResources().getDisplayMetrics());
    }
}