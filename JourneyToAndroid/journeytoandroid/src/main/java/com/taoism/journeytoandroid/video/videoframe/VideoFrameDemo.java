package com.taoism.journeytoandroid.video.videoframe;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.taoism.journeytoandroid.BaseActivity;
import com.taoism.journeytoandroid.R;
import com.taoism.journeytoandroid.utils.TimeUtil;
import com.taoism.journeytoandroid.utils.toastutil.ToastUtil;
import com.taoism.journeytoandroid.video.VideoUtil;

/**
 * Date: 2017-04-27
 * Time: 11:59
 * Author: cf
 * -----------------------------
 */

public class VideoFrameDemo extends BaseActivity implements View.OnClickListener {

    static {
        TAG = VideoFrameDemo.class.getSimpleName();
    }

    private String mPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_frame_demo);

        findViewById(R.id.btn_pick_media).setOnClickListener(this);
        findViewById(R.id.btn_get1).setOnClickListener(this);
        findViewById(R.id.btn_get10).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_pick_media:
                pickMedia();
                break;
            case R.id.btn_get1:

                VideoFrameTask videoFrameTask = new VideoFrameTask();
                videoFrameTask.execute();

                break;
            case R.id.btn_get10:
                for (int i = 0; i < 10; i++) {
                    VideoFrameTask videoFrameTask1 = new VideoFrameTask();
                    videoFrameTask1.execute();
                }
                break;
        }
    }


    private void pickMedia() {
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
////        photoPickerIntent.setType("*/*");
//        photoPickerIntent.setType("video/*");
//        startActivityForResult(photoPickerIntent, 1);

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 选取图片的返回值
        if (requestCode == 1) {
            //
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                Cursor cursor = getContentResolver().query(uri, null, null,
                        null, null);
                cursor.moveToFirst();
                // String imgNo = cursor.getString(0); // 图片编号
                String v_path = cursor.getString(1); // 图片文件路径
                String v_size = cursor.getString(2); // 图片大小
                String v_name = cursor.getString(3); // 图片文件名

                mPath = v_path;

                Log.i(TAG, "v_path=" + v_path);
                Log.i(TAG, "v_size=" + v_size);
                Log.i(TAG, "v_name=" + v_name);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private class VideoFrameTask extends AsyncTask<Object, Object, Bitmap> {

        long start;
        long end;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            start = System.currentTimeMillis();
        }

        @Override
        protected Bitmap doInBackground(Object... params) {

            if (!isCancelled()) {


                Bitmap bitmap = VideoUtil.getScaleVideoFrame(mPath, 1, 100, 100);
                return bitmap;
            }

            Log.i(TAG, "被取消咯");

            return null;

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            end = System.currentTimeMillis();

            ToastUtil.makeShortToast(TimeUtil.ms(end - start));

//            asycTaskMap.remove(viewPosition);
//			VideoCoverAdapter.this.notifyDataSetChanged();
        }
    }


}
