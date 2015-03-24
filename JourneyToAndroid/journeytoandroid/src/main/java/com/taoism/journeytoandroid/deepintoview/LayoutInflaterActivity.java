package com.taoism.journeytoandroid.deepintoview;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.widget.LinearLayout;
import com.taoism.journeytoandroid.BaseActivity;
import com.taoism.journeytoandroid.R;

/**
 * Created by Morphin3 on 3/24/15.
 */
public class LayoutInflaterActivity extends BaseActivity {

    private LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layoutinflater);
        mainLayout = (LinearLayout) findViewById(R.id.layoutinflater_main_layout);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View buttonLayout = layoutInflater.inflate(R.layout.layoutinflater_button_layout, null);
        mainLayout.addView(buttonLayout);

        ViewParent viewParent = mainLayout.getParent();
        Log.d("tag","the parent of mainLayout is " + viewParent);
    }
}
