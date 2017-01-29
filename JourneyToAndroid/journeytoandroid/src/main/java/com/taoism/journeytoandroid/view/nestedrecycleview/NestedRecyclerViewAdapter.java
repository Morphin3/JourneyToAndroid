package com.taoism.journeytoandroid.view.nestedrecycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.taoism.journeytoandroid.R;

/**
 * Date: 2015-07-05
 * Time: 22:01
 * Author: cf
 * -----------------------------
 */
public class NestedRecyclerViewAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private String[] mTitles;


    public NestedRecyclerViewAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mTitles = context.getResources().getStringArray(R.array.titles);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new NormalViewHolder(mLayoutInflater.inflate(R.layout.item_sort, parent, false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalViewHolder) {
            ((NormalViewHolder) holder).title.setText("标题");
        }
    }

    @Override
    public int getItemCount() {
        return mTitles.length;
    }


    public static class NormalViewHolder extends RecyclerView.ViewHolder {

        private TextView title;

        public NormalViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }

}
