package com.taoism.journeytoandroid.photogallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.taoism.journeytoandroid.R;

import java.util.ArrayList;

/**
 * Date: 2015-05-28
 * Time: 16:26
 * Author: Morphin3
 * WeChat: 398788401
 * E-mail: morphin333@gmail.com
 * -----------------------------
 * FIXME
 */
public class PhotoGalleryFragment extends Fragment {
    private static final String TAG = "PhotoGalleryFragment";

    RecyclerView rv_content;

    ArrayList<GalleryItem> mItems;

    ThumbnailDownLoader<ImageView> mThumbnailThread;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        new FetchItemsTask().execute();

        mThumbnailThread = new ThumbnailDownLoader<>(new Handler());
        mThumbnailThread.setListener(new ThumbnailDownLoader.Listener<ImageView>() {
            @Override
            public void onThumbnailDownloaded(ImageView imageView, Bitmap thumbnail) {
                if (isVisible()) {
                    imageView.setImageBitmap(thumbnail);
                }
            }
        });
        mThumbnailThread.start();
        mThumbnailThread.getLooper();
        Log.i(TAG, "Backgroud thread started");
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_photo_gallery, container, false);

        rv_content = (RecyclerView) v.findViewById(R.id.rv_content);
//        rv_content.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_content.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));

        setupAdapter();
        return v;
    }

    void setupAdapter() {
        if (getActivity() == null || rv_content == null)
            return;
        if (mItems != null) {
            PhotoGalleryAdapter photoGalleryAdapter = new PhotoGalleryAdapter(getActivity());
            photoGalleryAdapter.setData(mItems);
            rv_content.setAdapter(photoGalleryAdapter);
        } else {
            rv_content.setAdapter(null);
        }
    }


    private class FetchItemsTask extends AsyncTask<Void, Void, ArrayList<GalleryItem>> {

        @Override
        protected ArrayList<GalleryItem> doInBackground(Void... params) {
//            try{
//                String result = new FlickrFetchr().getUrl("http://www.baidu.com");
//                Log.i(TAG, "Fetched contents of URL: " + result);
//            }catch (IOException ioe){
//                Log.e(TAG,"Failed to fetch URL:", ioe);
//            }

//            --------------------------------
//            new FlickrFetchr().fetchItems();
//            return null;
//            --------------------------------

            return new FlickrFetchr().fetchItems();
        }

        @Override
        protected void onPostExecute(ArrayList<GalleryItem> items) {
            mItems = items;
            setupAdapter();
//            super.onPostExecute(items);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mThumbnailThread.clearQueue();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mThumbnailThread.quit();
        Log.i(TAG, "Backgroud thread destroyed");
    }


    /**
     * Date: 2015-05-28
     * Time: 16:47
     * Author: Morphin3
     * WeChat: 398788401
     * E-mail: morphin333@gmail.com
     * -----------------------------
     * FIXME
     */
    private class PhotoGalleryAdapter extends RecyclerView.Adapter<PhotoGalleryAdapter.ViewHolder> {

        private Context mContext;
        private String[] mTitles;
        private ArrayList<GalleryItem> mItems = new ArrayList<GalleryItem>();

        public PhotoGalleryAdapter(Context mContext) {
            this.mContext = mContext;
            mTitles = mContext.getResources().getStringArray(R.array.titles);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_gallery, parent, false);
            ViewHolder vh = new ViewHolder(v);

            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            holder.iv_picture.setImageResource(R.drawable.ic_nav_0);
            GalleryItem item = mItems.get(position);
            mThumbnailThread.queenThumbnail(holder.iv_picture, item.getmUrl());

            holder.tv_title.setText(item.getmCaption());
//        holder.tv_title.setText(mTitles[position]);
        }

        @Override
        public int getItemCount() {
            return mItems.size();
//        return mTitles == null? 0:mTitles.length;
        }


        public class ViewHolder extends RecyclerView.ViewHolder {

            public ImageView iv_picture;
            public TextView tv_title;

            public ViewHolder(View itemView) {
                super(itemView);
                this.iv_picture = (ImageView) itemView.findViewById(R.id.iv_picture);
                this.tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            }
        }


        public void setData(ArrayList<GalleryItem> items) {
            mItems.clear();
            mItems.addAll(items);
//        notifyDataSetChanged();
        }


    }


}
