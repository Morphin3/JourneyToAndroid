package com.taoism.journeytoandroid.view.nestedrecycleview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
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
public class RecyclerViewAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private String[] mTitles;

    public interface ItemType {
        int TYPE_NESTED = 0;
        int TYPE_NORMAL = 1;
    }

    public RecyclerViewAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mTitles = context.getResources().getStringArray(R.array.titles);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ItemType.TYPE_NESTED) {
            return new NestedViewHolder(mLayoutInflater.inflate(R.layout.item_nested_recyclerview,parent,false));
        }else if(viewType == ItemType.TYPE_NORMAL){
            return new NormalViewHolder(mLayoutInflater.inflate(R.layout.item_sort,parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof NestedViewHolder){
            ((NestedViewHolder) holder).rv_nested.setVisibility(View.VISIBLE);
        }else if(holder instanceof NormalViewHolder){
            ((NormalViewHolder) holder).title.setText("标题");
        }
    }

    @Override
    public int getItemCount() {
        return mTitles.length;
//        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return ItemType.TYPE_NESTED;
        }else{
            return ItemType.TYPE_NORMAL;
        }
    }

    public  class NestedViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView rv_nested;

        public NestedViewHolder(View itemView) {
            super(itemView);
            rv_nested = (RecyclerView)itemView.findViewById(R.id.rv_nested);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
            rv_nested.setLayoutManager(linearLayoutManager);
            rv_nested.setAdapter(new NestedRecyclerViewAdapter(mContext));

        }
    }

    public  class NormalViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        public NormalViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
        }
    }

}
