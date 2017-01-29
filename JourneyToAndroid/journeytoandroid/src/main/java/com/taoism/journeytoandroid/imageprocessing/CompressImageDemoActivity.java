package com.taoism.journeytoandroid.imageprocessing;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.taoism.journeytoandroid.BaseActivity;
import com.taoism.journeytoandroid.R;

import java.io.File;

/**
 * Date: 2016-05-24
 * Time: 19:34
 * Author: cf
 * -----------------------------
 */
public class CompressImageDemoActivity extends BaseActivity {

    private ImageView mIvOriginal;
    private ImageView mIvCompressed;
    private TextView mTv_info;
    private Button btnOriginal;
    private Button btnBitmapToFile;
    private Button btnToTargetSizeFile;

    private Bitmap mOriginalBitmap;
    private Bitmap mNewBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compress_image_demo);

        mIvOriginal = (ImageView) findViewById(R.id.iv_original);
        mIvCompressed = (ImageView) findViewById(R.id.iv_compressed);
        mTv_info = (TextView) findViewById(R.id.tv_info);
        btnOriginal = (Button) findViewById(R.id.btn_original);
        btnBitmapToFile = (Button) findViewById(R.id.btn_bitmap_to_file);
        btnToTargetSizeFile = (Button) findViewById(R.id.btn_to_target_size);

        btnOriginal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOriginalBitmap != null) {
                    mIvOriginal.setImageBitmap(null);
                    mOriginalBitmap.recycle();
                }
                if (mNewBitmap != null) {
                    mIvCompressed.setImageBitmap(null);
                    mNewBitmap.recycle();
                }
                File oldFile = getFileFromSDCard("chao.jpg");
                mOriginalBitmap = ImageProcessingUtil.decodeFile(oldFile);
                mIvOriginal.setImageBitmap(mOriginalBitmap);

                StringBuilder sb = new StringBuilder();
                mNewBitmap = ImageProcessingUtil.decodeFile(oldFile, 1000000);
                File newFile = ImageProcessingUtil.getFileFromBitmap(CompressImageDemoActivity.this,
                        mNewBitmap,
                        100,
                        "temp");

                Log.i("image", "原始 Bitmap：" + mOriginalBitmap.getWidth() + "*" + mOriginalBitmap.getHeight() + "\n" +
                        "压缩后 Bitmap：" + mNewBitmap.getWidth() + "*" + mNewBitmap.getHeight() + "\n" +
                        "原始文件大小：" + oldFile.length() / 1024 + "kb" + "\n" +
                        "压缩后文件大小：" + newFile.length() / 1024 + "kb" + "\n" +
                        "---\n"
                );
                sb.append(
                        "原始 Bitmap：" + mOriginalBitmap.getWidth() + "*" + mOriginalBitmap.getHeight() + "\n" +
                                "压缩后 Bitmap：" + mNewBitmap.getWidth() + "*" + mNewBitmap.getHeight() + "\n" +
                                "原始文件大小：" + oldFile.length() / 1024 + "kb" + "\n" +
                                "压缩后文件大小：" + newFile.length() / 1024 + "kb" + "\n" +
                                "---\n"
                );

                mIvCompressed.setImageBitmap(mNewBitmap);
                mTv_info.setText(sb.toString());

            }
        });

        btnBitmapToFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File oldFile = getFileFromSDCard("chao.jpg");
                Bitmap originalBitmap = ImageProcessingUtil.decodeFile(oldFile);
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i <= 10; i++) {
                    Bitmap newBitmap = ImageProcessingUtil.decodeFile(oldFile, 1000000);
                    File newFile = ImageProcessingUtil.getFileFromBitmap(CompressImageDemoActivity.this,
                            newBitmap,
                            i * 10,
                            "temp");

                    Log.i("image", "原始 Bitmap：" + originalBitmap.getWidth() + "*" + originalBitmap.getHeight() + "\n" +
                            "压缩后 Bitmap：" + newBitmap.getWidth() + "*" + newBitmap.getHeight() + "\n" +
                            "原始文件大小：" + oldFile.length() / 1024 + "kb" + "\n" +
                            "压缩后文件大小：" + newFile.length() / 1024 + "kb" + "\n" +
                            "---\n"
                    );

                    sb.append(
                            "原始 Bitmap：" + originalBitmap.getWidth() + "*" + originalBitmap.getHeight() + "\n" +
                                    "压缩后 Bitmap：" + newBitmap.getWidth() + "*" + newBitmap.getHeight() + "\n" +
                                    "原始文件大小：" + oldFile.length() / 1024 + "kb" + "\n" +
                                    "压缩后文件大小：" + newFile.length() / 1024 + "kb" + "\n" +
                                    "---\n"
                    );
                }

                mTv_info.setText(sb.toString());


            }
        });


        btnToTargetSizeFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File oldFile = getFileFromSDCard("20M.png");
                Bitmap originalBitmap = ImageProcessingUtil.decodeFile(oldFile);
                Bitmap newBitmap = ImageProcessingUtil.decodeFile(CompressImageDemoActivity.this,
                        oldFile,
                        2 * 1024 * 1024);
                File newFile = ImageProcessingUtil.getFileFromBitmap(CompressImageDemoActivity.this,
                        newBitmap,
                        95,
                        "temp");

                StringBuilder sb = new StringBuilder();
                Log.i("image", "原始 Bitmap：" + originalBitmap.getWidth() + "*" + originalBitmap.getHeight() + "\n" +
                        "压缩后 Bitmap：" + newBitmap.getWidth() + "*" + newBitmap.getHeight() + "\n" +
                        "原始文件大小：" + oldFile.length() / 1024 + "kb" + "\n" +
                        "压缩后文件大小：" + newFile.length() / 1024 + "kb" + "\n" +
                        "---\n"
                );

                sb.append(
                        "原始 Bitmap：" + originalBitmap.getWidth() + "*" + originalBitmap.getHeight() + "\n" +
                                "压缩后 Bitmap：" + newBitmap.getWidth() + "*" + newBitmap.getHeight() + "\n" +
                                "原始文件大小：" + oldFile.length() / 1024 + "kb" + "\n" +
                                "压缩后文件大小：" + newFile.length() / 1024 + "kb" + "\n" +
                                "---\n"
                );
                mTv_info.setText(sb.toString());
            }
        });

//        ImageProcessingUtil.calculateSampleSize();

    }


    private File getFileFromSDCard(String fileName) {
        File dir = Environment.getExternalStorageDirectory();
        File file = new File(dir, fileName);
        return file;
    }


//    private File getFileFromRes() {
//        Uri url = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.morphin3);
//        File file = new File(url.toString());
//        return file;
//    }


}
