package com.taoism.journeytoandroid.oom;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.taoism.journeytoandroid.R;

/**
 * Created by Morphin3 on 5/23/15.
 */
public class ImageCacheDemoActivity extends Activity {

    private ImageView iv;

    /**
     * 打开本地相册的requestcode.
     */
    public static final int OPEN_PHOTO_REQUESTCODE =  0x1;


    /**
     * 图片的target大小.
     */
    private static final int target = 400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_cache_demo);

        iv =(ImageView)findViewById(R.id.iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhotos();
            }
        });
    }


    /**
     * 打开本地相册
     */
    private void openPhotos(){
        Intent intent= new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "iamge/*");
        startActivityForResult(intent,OPEN_PHOTO_REQUESTCODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){

            case OPEN_PHOTO_REQUESTCODE:
                if(resultCode == RESULT_OK){
                    //如果用这个方法，Options为null时候，就是默认decode会出现oom哦
                    Bitmap bm = ImageCacheUtil.decode(null,null,ImageCacheDemoActivity.this,data.getData(),null);

                    //这里调用这个方法就不会oom.屌丝们就用这个方法吧.
//                    Bitmap bm =ImageCacheUtil.getResizedBitMap(null,null,ImageCacheDemoActivity.this,data.getData(),target,false);
//                    iv.setImageBitmap(bm);
                }

                break;

            default:
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
