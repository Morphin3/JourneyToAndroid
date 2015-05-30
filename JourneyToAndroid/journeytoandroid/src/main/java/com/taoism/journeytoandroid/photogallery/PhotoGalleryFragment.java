package com.taoism.journeytoandroid.photogallery;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private  static final String TAG = "PhotoGalleryFragment";

    RecyclerView rv_content;

    ArrayList<GalleryItem> mItems;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        new FetchItemsTask().execute();

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

    void setupAdapter(){
        if(getActivity() == null || rv_content == null)
            return;
        if(mItems != null){
            PhotoGalleryAdapter photoGalleryAdapter =new PhotoGalleryAdapter(getActivity());
            photoGalleryAdapter.setData(mItems);
            rv_content.setAdapter(photoGalleryAdapter);
        } else{
            rv_content.setAdapter(null);
        }
    }


    private class FetchItemsTask extends AsyncTask<Void,Void,ArrayList<GalleryItem>> {

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
}
