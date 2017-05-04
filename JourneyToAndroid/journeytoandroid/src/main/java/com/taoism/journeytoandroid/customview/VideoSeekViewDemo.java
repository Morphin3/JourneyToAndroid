package com.taoism.journeytoandroid.customview;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taoism.journeytoandroid.BaseActivity;
import com.taoism.journeytoandroid.R;
import com.taoism.journeytoandroid.customview.list.HorizontalListView;
import com.taoism.journeytoandroid.customview.videocutseekview.VideoSeekView;
import com.taoism.journeytoandroid.utils.screenutil.ScreenUtil;

import static com.taoism.journeytoandroid.R.id.vsv;

/**
 * Date: 2017-04-06
 * Time: 15:36
 * Author: cf
 * -----------------------------
 */

public class VideoSeekViewDemo extends BaseActivity {

    private VideoSeekView mVSV;
    private HorizontalListView hlv;

    private TextView tvTime;
    private Button mBtnRefresh;

    private boolean mReachListEdge = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_seek_view_demo);

        mVSV = (VideoSeekView) findViewById(vsv);
        tvTime = (TextView) findViewById(R.id.tv_time);

        mBtnRefresh = (Button) findViewById(R.id.btn_refresh);

        mVSV.setSeekListener(new VideoSeekView.SeekListener() {

            @Override
            public void onStartSeek() {

            }

            @Override
            public void onSeek(VideoSeekView videoSeekView, int currentTime, int totalTime, int minTime, float currentTimeAxisWidth, float totalTimeAxisWidth, float minTimeAxisWidth) {
                tvTime.setText(currentTime + "/" + totalTime);

                refreshScrollMode(true);
            }

            @Override
            public void onStopSeek() {

            }
        });

        mVSV.setActualDuration(90000);

        hlv = (HorizontalListView) findViewById(R.id.hlv);
        hlv.setAdapter(new HorizontalAdapter(this, 90000));
        hlv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view,
                                 int firstVisibleItem,
                                 int visibleItemCount,
                                 int totalItemCount) {


            }
        });

        hlv.setOnContainerScrollListener(new HorizontalListView.OnContainerScrollListener() {
            @Override
            public void onContainerScroll() {

                mVSV.adjustDurationTimeAxisWidth(hlv.getScrollX());

            }

            @Override
            public void onReachContainerEdge() {

                mReachListEdge = true;
                refreshScrollMode(false);

            }

            @Override
            public void onLeaveContainerEdge() {

                mReachListEdge = false;
                refreshScrollMode(false);
            }
        });

        mBtnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams lp = mBtnRefresh.getLayoutParams();
                lp.width = (int) (lp.width * Math.random() * 10);
                mBtnRefresh.setLayoutParams(lp);
            }
        });


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        refreshScrollMode(true);
    }

    private void refreshScrollMode(boolean setMax) {
        if (mReachListEdge && hlv.getCurrentX() + mVSV.getCurrentTimeAxisWidth() < (int)mVSV.getActualTimeWidth()){
            hlv.setScrollMode(HorizontalListView.MODE_SCROLL_CONTAINER);
            if(setMax){
                hlv.setMaxScrollX((int) (mVSV.getActualTimeWidth() - hlv.getCurrentX() - mVSV.getCurrentTimeAxisWidth()));
                Log.i(TAG,"max scrollX:" + (int) (mVSV.getActualTimeWidth() - hlv.getCurrentX() - mVSV.getCurrentTimeAxisWidth()));
            }
        } else{
            hlv.setScrollMode(HorizontalListView.MODE_SCROLL_CONTENT);
        }
    }

    class HorizontalAdapter extends BaseAdapter {

        private LayoutInflater mInflater;

        private long mDuration;

        private int mCount;// 数量


        private int mItemWidth;

        public HorizontalAdapter(Context context,long duration) {
            mInflater = LayoutInflater.from(context);
            mDuration = duration;

            this.mCount = mDuration % (5000) > 0 ? 1 : 0;
            this.mCount += mDuration / (5000) ;

            mItemWidth = (int) (ScreenUtil.getDisplayWidth() / 12F);
        }

        @Override
        public int getCount() {
            return mCount;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_video_frame, null);

                viewHolder = new ViewHolder();
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_picture);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_title);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            LinearLayout.LayoutParams lp =  new LinearLayout.LayoutParams(mItemWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            viewHolder.imageView.setLayoutParams(lp);
            viewHolder.imageView.setBackgroundResource(R.color.blue_light);
            viewHolder.textView.setText(position + 1 + "");
            return convertView;
        }

        class ViewHolder {
            private ImageView imageView;
            private TextView textView;
        }
    }


}
