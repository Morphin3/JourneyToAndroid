package com.taoism.journeytoandroid.smoothprogressbar;

import android.os.Bundle;
import android.view.View;
import com.taoism.journeytoandroid.R;

/**
 * Created by chenfei on 14-11-27.
 */
public class SmoothProgressBarDemo extends ProgressBaseActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_smooth_progress_bar);
		findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showSmoothProgressDialog(true);
			}
		});

		findViewById(R.id.btn_dismiss).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showSmoothProgressDialog(false);
			}
		});
	}



}
