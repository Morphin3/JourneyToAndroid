package com.taoism.journeytoandroid.photogallery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.taoism.journeytoandroid.R;
import com.taoism.journeytoandroid.baseclass.SingleFragmentActivity;

/**
 * Date: 2015-05-28
 * Time: 15:04
 * Author: Morphin3
 * WeChat: 398788401
 * E-mail: morphin333@gmail.com
 * -----------------------------
 * FIXME
 */
public class PhotoGalleryActivity extends SingleFragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

    }


    //返回fragment实例
    @Override
    protected Fragment createFragment() {
        return new PhotoGalleryFragment();
    }
}
