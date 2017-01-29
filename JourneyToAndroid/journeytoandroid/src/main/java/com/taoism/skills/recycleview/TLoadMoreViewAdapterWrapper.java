package com.taoism.skills.recycleview;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * loadmore 包装类，load more viewtype为9999
 * Created by zhengwen on 15-6-29.
 */
public class TLoadMoreViewAdapterWrapper extends RecyclerView.Adapter<TRecycleViewHolder> {

    private boolean mHasMore;

    //正在加载更多
    public static final int LOADINGMORETYPE = 9999;

    private  TRecycleViewAdapter mInnerRecycleViewAdapter;
    private LoadMoreRecycleView mLoadMoreRecycleView;
    public TLoadMoreViewAdapterWrapper(TRecycleViewAdapter innerAdapter){
        mInnerRecycleViewAdapter = innerAdapter;
    }

    public void setLoadingMoreRecycleView(LoadMoreRecycleView recycleView){
        mLoadMoreRecycleView = recycleView;
    }

    @Override
    public TRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == LOADINGMORETYPE){
            return new BaseLoadMoreViewHolder(mLoadMoreRecycleView.getLoadingMoreView());
        }else{
            return mInnerRecycleViewAdapter.onCreateViewHolder(parent,viewType);
        }
    }

    @Override
    public void onBindViewHolder(TRecycleViewHolder holder, int position) {
        if(mHasMore&&position== mInnerRecycleViewAdapter.getItemCount()&&mLoadMoreRecycleView.getLoadingMoreView()!=null){
            return ;
        }else{
            mInnerRecycleViewAdapter.bindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        if(mHasMore&&mLoadMoreRecycleView.getLoadingMoreView()!=null&&mLoadMoreRecycleView.getLoadingMoreView()!=null)
            return mInnerRecycleViewAdapter.getItemCount()+1;
        return mInnerRecycleViewAdapter.getItemCount();
    }

    public void setRecycleViewAdapter(TRecycleViewAdapter adapter){
        this.mInnerRecycleViewAdapter = adapter;
    }

    public void setHasMore(boolean bHasMore){
        //设置
       this.mHasMore =true;
    }

    public boolean getHasMore(){
        return mHasMore;
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);

    }

    @Override
    public long getItemId(int position) {
        if(mHasMore&&position== mInnerRecycleViewAdapter.getItemCount()&&mLoadMoreRecycleView.getLoadingMoreView()!=null){
            return 0;
        }
        return mInnerRecycleViewAdapter.getItemId(position);
    }


    @Override
    public int getItemViewType(int position) {
        if(mHasMore&&position== mInnerRecycleViewAdapter.getItemCount()&&mLoadMoreRecycleView.getLoadingMoreView()!=null){
            return LOADINGMORETYPE;
        }
        return mInnerRecycleViewAdapter.getItemViewType(position);
    }



}
