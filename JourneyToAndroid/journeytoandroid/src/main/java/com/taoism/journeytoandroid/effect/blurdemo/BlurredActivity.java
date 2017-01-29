package com.taoism.journeytoandroid.effect.blurdemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.taoism.journeytoandroid.R;
import com.taoism.journeytoandroid.effect.blur.BlurBehind;

public class BlurredActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_blurred);

		BlurBehind.getInstance()
                .withAlpha(80)
                .withFilterColor(Color.parseColor("#0075c0"))
                .setBackground(this);
	}
}
