package com.taoism.journeytoandroid.customview.copyabletextview;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.taoism.journeytoandroid.R;

/**
 * Date: 2015-07-09
 * Time: 22:31
 * Author: cf
 * -----------------------------
 */
public class CopyableTextViewDemoActivity extends Activity {

    // Content View Elements

    private com.taoism.journeytoandroid.customview.copyabletextview.CopyableTextView mTv_copyable;
    private com.taoism.journeytoandroid.customview.copyabletextview.CopyableTextView mTv_uncopyable;

    // End Of Content View Elements

    private ImageView iv_uri_test;
//    private SimpleDraweeView sdv_uri_test;

    private void bindViews() {

        mTv_copyable = (com.taoism.journeytoandroid.customview.copyabletextview.CopyableTextView) findViewById(R.id.tv_copyable);
        mTv_uncopyable = (com.taoism.journeytoandroid.customview.copyabletextview.CopyableTextView) findViewById(R.id.tv_uncopyable);

        iv_uri_test =(ImageView)findViewById(R.id.iv_uri_test);
//        sdv_uri_test = (SimpleDraweeView)findViewById(R.id.sdv_uri_test);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copyable_textview_demo);
        bindViews();

        mTv_copyable.setIsCopyable(true);
        mTv_uncopyable.setIsCopyable(false);



        Uri uri = Uri.parse("android.resource://com.taoism.journeytoandroid/drawable/ic_dial");
//        try {
//            InputStream stream = getContentResolver().openInputStream(uri);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        iv_uri_test.setImageURI(uri);
//        sdv_uri_test.setImageURI(uri,this);


//        mTv_copyable.setCopyable(true);
//
////        mTv_uncopyable.setText(mTv_uncopyable.getText(), TextView.BufferType.SPANNABLE);
//
//
//        mTv_uncopyable.setFocusableInTouchMode(true);
//        mTv_uncopyable.setFocusable(true);
//        mTv_uncopyable.setClickable(true);
//        mTv_uncopyable.setLongClickable(true);
//        mTv_uncopyable.setMovementMethod(ArrowKeyMovementMethod.getInstance());
//        mTv_uncopyable.setCopyable(false);
    }
}
