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

/**
 * Date: 2017-04-05
 * Time: 18:46
 * Author: cf
 * -----------------------------
 */

public class HorizontalListViewDemo extends BaseActivity {

    private HorizontalListView hlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_list_view_demo);

        hlv = (HorizontalListView) findViewById(R.id.hlv);

        hlv.setAdapter(new HorizontalAdapter(this));

        hlv.post(new Runnable() {
            @Override
            public void run() {
                hlv.scrollTo(100);
            }
        });

    }


    class HorizontalAdapter extends BaseAdapter {

        private LayoutInflater mInflater;

        public HorizontalAdapter(Context context) {
            mInflater = mInflater = LayoutInflater.from(context);
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
