package com.taoism.skills.recycleview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhengwen on 15-6-17.
 */
public class LoadMoreRecycleView extends RecyclerView {

    public interface OnLoadMoreListener {
        public void loadMore();
    }

    /**
     *

     public RecyclerView(Context context) {
     this(context, null);
     }

     public RecyclerView(Context context, @Nullable AttributeSet attrs) {
     this(context, attrs, 0);
     }

     public RecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {


     */

    private TLoadMoreViewAdapterWrapper mLoadingMoreViewAdapter;
    private OnLoadMoreListener mLoadMoreListenr;
    private int mLastRecycleViewState;
    private int mLastVisibleItemPosition;
    private View mLoadingMoreView;

    public LoadMoreRecycleView(Context context) {
        super(context);
        initListener();
    }

    public LoadMoreRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context,attrs);
        initListener();
    }

    public LoadMoreRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
        initListener();

    }

    private void initListener(){
        this.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mLastVisibleItemPosition = newState;
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                //包含loadmore的情况


                    if (mLoadingMoreViewAdapter!=null&&!mLoadingMoreViewAdapter.getHasMore() && (visibleItemCount > 0 && newState == RecyclerView.SCROLL_STATE_IDLE && (mLastVisibleItemPosition) >= totalItemCount - 1)) {
                        //scrooltobottom
                        if (mLoadMoreListenr != null) {
                            //正在加载更多
                            mLoadMoreListenr.loadMore();
                        }
                    }



            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                mLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            }


        });
    }




    public void setLoadingMoreView(View loadingMoreView){
        mLoadingMoreView = loadingMoreView;

    }
    public View getLoadingMoreView(){
        return mLoadingMoreView;
    }


    public void setAdapter (Adapter adapter){
        if(adapter instanceof  TLoadMoreViewAdapterWrapper){
            mLoadingMoreViewAdapter= (TLoadMoreViewAdapterWrapper)adapter;
        }else{
            super.setAdapter(adapter);
        }
    }

    public void setLoadMoreListener(OnLoadMoreListener onLoadMoreListener){
        this.mLoadMoreListenr  = onLoadMoreListener;
    }



}
