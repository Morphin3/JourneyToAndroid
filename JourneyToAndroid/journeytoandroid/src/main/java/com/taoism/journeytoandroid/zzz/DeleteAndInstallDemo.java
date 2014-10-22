package com.taoism.journeytoandroid.zzz;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.test.ActivityTestCase;
import android.view.View;
import android.widget.Button;
import com.taoism.journeytoandroid.R;

import java.io.File;

/**
 * Created by chenfei on 14-10-22.
 */
public class DeleteAndInstallDemo extends Activity {
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_delete_and_install);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent promptInstall = new Intent(Intent.ACTION_VIEW);
                promptInstall.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/download/app-debug-unaligned.apk")), "application/vnd.android.package-archive");
                startActivity(promptInstall);

                //通过程序的包名创建URI
                Uri packageURI = Uri.parse("package:com.taoism.journeytoandroid");
                //创建Intent意图
                Intent intent = new Intent(Intent.ACTION_DELETE, packageURI);
                //执行卸载程序
                startActivity(intent);
            }
        });

    }


    private BroadcastReceiver downLoadReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //这里可以取得下载的id，这样就可以知道哪个文件下载完成了。适用与多个下载任务的监听
            Intent promptInstall = new Intent(Intent.ACTION_VIEW);
            promptInstall.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/download/WindThunder.apk")), "application/vnd.android.package-archive");
            startActivity(promptInstall);
        }
    };


//    /**
//     * 下载新版本
//     */
//    public void downLoad() {
//        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
//
//        String url = ResourceUrl.BASE_URL + "/downloads/apps/883/android/latest-package?app_type=windthunder";
//        Uri uri = Uri.parse(url);
//
//        DownloadManager.Request request = new DownloadManager.Request(uri);
//        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
//        request.setAllowedOverRoaming(false);
//
//        //设置文件类型
//        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
//        String mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(url));
//        request.setMimeType(mimeString);
//
//        //在通知栏中显示
//        request.setShowRunningNotification(true);
//        request.setVisibleInDownloadsUi(true);
//        //先删除已有的文件
//        File file = new File(Environment.getExternalStorageDirectory() + "/download/WindThunder.apk");
//        if (file.exists()) {
//            file.delete();
//        }
//        //sdcard的目录下的download文件夹
//        request.setDestinationInExternalPublicDir("/download/", "WindThunder.apk");
//        request.setTitle("风呼叫更新");
//        downloadManager.enqueue(request);
//    }

}
