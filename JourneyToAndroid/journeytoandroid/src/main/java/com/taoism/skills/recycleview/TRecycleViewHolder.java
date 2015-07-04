package com.taoism.skills.recycleview;

import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * recycle需要的viewholder
 * Created by zhengwen on 15-6-16.
 */
@TRecycleViewHolderAnnotation()
public abstract class TRecycleViewHolder extends RecyclerView.ViewHolder {
    protected View view;
    public TRecycleViewHolder(View itemView) {
        super(itemView);
        view= itemView;
//        viewHolderProxy.inflate();
        inflate();
    }

    public abstract void inflate();
    public abstract void refresh(TAdapterItem item);




}
