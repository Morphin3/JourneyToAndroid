package com.taoism.journeytoandroid.photogallery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.taoism.journeytoandroid.R;

/**
 * Date: 2015-05-28
 * Time: 16:47
 * Author: Morphin3
 * WeChat: 398788401
 * E-mail: morphin333@gmail.com
 * -----------------------------
 * FIXME
 */
public class PhotoGalleryAdapter extends RecyclerView.Adapter<PhotoGalleryAdapter.ViewHolder> {

    private Context mContext;
    private String[] mTitles;

    public PhotoGalleryAdapter(Context mContext) {
        this.mContext = mContext;
        mTitles= mContext.getResources().getStringArray(R.array.titles);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_gallery, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_title.setText(mTitles[position]);
    }

    @Override
    public int getItemCount() {
        return mTitles == null? 0:mTitles.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv_picture;
        public TextView tv_title;

        public ViewHolder(View itemView) {
            super(itemView);
            this.iv_picture = (ImageView) itemView.findViewById(R.id.iv_picture);
            this.tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }


}
