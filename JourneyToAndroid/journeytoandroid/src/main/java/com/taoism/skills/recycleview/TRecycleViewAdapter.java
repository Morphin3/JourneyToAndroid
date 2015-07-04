package com.taoism.skills.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * 用于RecycleView的adatper
 * Created by zhengwen on 15-6-16.
 */
public class TRecycleViewAdapter extends RecyclerView.Adapter<TRecycleViewHolder> {
    private List<? extends TAdapterItem> mItems;
    private Context mContext;
    private LayoutInflater mInflater;
    private SparseArray<Class> viewHolders;

    public TRecycleViewAdapter(Context context, SparseArray<Class> viewHolders, List<? extends TAdapterItem> items){
        this.viewHolders = viewHolders;
        this.mItems = items;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(this.mContext);
    }



    @Override
    public void onViewRecycled(TRecycleViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);

    }

    @Override
    public long getItemId(int position) {
        return mItems.get(position).hashCode();
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getViewType();
    }

    @Override
    public TRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TRecycleViewHolder recycleViewHolder  = null;
        try{
            Class viewHolder = viewHolders.get(viewType);
            TRecycleViewHolderAnnotation annotation = (TRecycleViewHolderAnnotation) viewHolder.getAnnotation(TRecycleViewHolderAnnotation.class);
            int resId = annotation.resId();
            View v = LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
            Constructor constructor = viewHolder.getConstructor(android.view.View.class);
            recycleViewHolder = (TRecycleViewHolder) (constructor.newInstance(v));
        }catch(Exception e){
            e.printStackTrace();
        }
        return recycleViewHolder;
    }

    @Override
    public void onBindViewHolder(TRecycleViewHolder holder, int position) {
        TAdapterItem item = mItems.get(position);
        holder.refresh(item);
    }

    @Override
    public int getItemCount() {
        int count = (mItems==null ? 0 : mItems.size());
        return count;
    }
}
