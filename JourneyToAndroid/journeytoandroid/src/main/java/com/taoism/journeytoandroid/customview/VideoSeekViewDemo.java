package com.taoism.journeytoandroid.customview;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.meetme.android.horizontallistview.HorizontalListView;
import com.taoism.journeytoandroid.BaseActivity;
import com.taoism.journeytoandroid.R;
import com.taoism.journeytoandroid.customview.videocutseekview.VideoSeekView;

/**
 * Date: 2017-04-06
 * Time: 15:36
 * Author: cf
 * -----------------------------
 */

public class VideoSeekViewDemo extends BaseActivity {

    private HorizontalListView hlv;

    private TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_seek_view_demo);

        VideoSeekView vsv = (VideoSeekView) findViewById(R.id.vsv);
        tvTime = (TextView) findViewById(R.id.tv_time);

        vsv.setSeekListener(new VideoSeekView.SeekListener() {
            @Override
            public void onSeek(int currentTime, int totalTime, int minTime, int currentTimeAxisWidth, int totalTimeAxisWidth, int minTimeAxisWidth) {
                tvTime.setText(currentTime + "/" + totalTime);
            }
        });

        hlv = (HorizontalListView) findViewById(R.id.hlv);

        hlv.setAdapter(new HorizontalAdapter(this));

    }

    class HorizontalAdapter extends BaseAdapter {

        private LayoutInflater mInflater;

        public HorizontalAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return 100;
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
            if(convertView == null){
                convertView = mInflater.inflate(R.layout.item_photo_gallery, null);

                viewHolder = new ViewHolder();
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_picture);
                viewHolder.textView = (TextView)convertView.findViewById(R.id.tv_title);

                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            return convertView;
        }

        class ViewHolder {
            private ImageView imageView;
            private TextView textView;
        }
    }


}
