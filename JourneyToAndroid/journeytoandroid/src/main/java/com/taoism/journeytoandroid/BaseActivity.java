package com.taoism.journeytoandroid;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by chenfei on 14-11-4.
 */
public class BaseActivity extends Activity {
    private ProgressDialog mProgressDialog;
    public Toast mToast;

    public static String TAG = BaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
    }

    /**
     * 通讯提示框
     *
     * @param msg
     * @param showStatus
     */
    public void showProgressDialog(String msg, boolean showStatus) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(true);
        }

        if (showStatus) {
            mProgressDialog.setMessage(msg);
            mProgressDialog.show();
        } else {
            mProgressDialog.dismiss();
        }
    }

    /**
     * 调用拨号盘
     *
     * @param tel
     */
    public void makeCall(String tel) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    /**
     * 得到manifest中的versionCode
     */
    public int getVersionCode() {
        PackageManager pm = getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(getPackageName(), 0);
            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
