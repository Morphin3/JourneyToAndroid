package com.taoism.journeytoandroid.nightmode;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.taoism.journeytoandroid.BaseActivity;
import com.taoism.journeytoandroid.R;

/**
 * Date: 2016-05-19
 * Time: 11:24
 * Author: cf
 * -----------------------------
 */
public class BrightnessNightModeDemoActivity extends BaseActivity implements View.OnClickListener {

    // Content View Elements

    private Button mBtn_add_brightness;
    private Button mBtn_minus_brightness;
    private Button mBtn_open_auto;
    private Button mBtn_close_auto;

    // End Of Content View Elements

    private void bindViews() {

        mBtn_add_brightness = (Button) findViewById(R.id.btn_add_brightness);
        mBtn_minus_brightness = (Button) findViewById(R.id.btn_minus_brightness);
        mBtn_open_auto = (Button) findViewById(R.id.btn_open_auto);
        mBtn_close_auto = (Button) findViewById(R.id.btn_close_auto);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brightness_night_mode_demo);
        bindViews();

        mBtn_add_brightness.setOnClickListener(this);
        mBtn_minus_brightness.setOnClickListener(this);
        mBtn_open_auto.setOnClickListener(this);
        mBtn_close_auto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int brightness = 128;
        switch (v.getId()) {
            case R.id.btn_add_brightness:
                brightness = BrightnessNightModeUtil.getSystemBrightness() + 10;
                if (brightness > 255) {
                    brightness = 255;
                }
                BrightnessNightModeUtil.setBrightness(brightness);
                break;
            case R.id.btn_minus_brightness:
                brightness = BrightnessNightModeUtil.getSystemBrightness() - 10;
                if (brightness < 0) {
                    brightness = 0;
                }
                BrightnessNightModeUtil.setBrightness(brightness);
                ;
                break;
            case R.id.btn_open_auto:
                BrightnessNightModeUtil.openAutoBrightness();
                ;
                break;
            case R.id.btn_close_auto:
                BrightnessNightModeUtil.closeAutoBrightness();
                ;
                break;
        }

    }
}
