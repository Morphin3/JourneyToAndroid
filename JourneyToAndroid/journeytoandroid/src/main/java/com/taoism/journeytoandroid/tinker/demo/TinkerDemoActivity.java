package com.taoism.journeytoandroid.tinker.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.taoism.journeytoandroid.R;
import com.tencent.tinker.lib.tinker.TinkerInstaller;

/**
 * Date: 2017-03-16
 * Time: 17:23
 * Author: cf
 * -----------------------------
 */

public class TinkerDemoActivity extends Activity implements View.OnClickListener {

    private static final String TAG = TinkerDemoActivity.class.getSimpleName();

    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinker_demo);

        findViewById(R.id.btn_load_patch).setOnClickListener(this);
        tvInfo = (TextView) findViewById(R.id.tv_info);


        Log.e(TAG, "i am on onCreate string:" + "I am in the base apk");

//        tvInfo.setText("补丁前");
        tvInfo.setText("补丁后");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_load_patch:
                //TODO implement
                TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(),
                        Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.apk");
                break;
        }

    }
}
