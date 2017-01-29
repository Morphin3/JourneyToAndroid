package com.taoism.journeytoandroid.effect.blurdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.taoism.journeytoandroid.R;
import com.taoism.journeytoandroid.effect.blur.BlurBehind;
import com.taoism.journeytoandroid.effect.blur.OnBlurCompleteListener;

public class BlurDemoActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_blur_demo);

		final Button dummyButton = (Button) findViewById(R.id.dummy_button);

		dummyButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				BlurBehind.getInstance().execute(BlurDemoActivity.this, new OnBlurCompleteListener() {
                    @Override
                    public void onBlurComplete() {
                        Intent intent = new Intent(BlurDemoActivity.this, BlurredActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                    }
                });
			}
		});
	}
}
