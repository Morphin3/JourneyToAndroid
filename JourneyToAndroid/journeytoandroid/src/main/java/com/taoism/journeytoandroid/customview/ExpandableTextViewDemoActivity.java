package com.taoism.journeytoandroid.customview;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.taoism.journeytoandroid.BaseActivity;
import com.taoism.journeytoandroid.R;
import com.taoism.journeytoandroid.customview.widget.ExpandableTextView;

/**
 * Date: 2015-06-26
 * Time: 13:38
 * Author: cf
 * -----------------------------
 * FIXME
 */
public class ExpandableTextViewDemoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_text_view_demo);


        ExpandableTextView expTv1 = (ExpandableTextView)findViewById(R.id.expand_text_view);
//        ExpandableTextView expTv2 = (ExpandableTextView)findViewById(R.id.expand_text_view);

        expTv1.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                Toast.makeText(ExpandableTextViewDemoActivity.this, isExpanded ? "Expanded" : "Collapsed", Toast.LENGTH_SHORT).show();
            }
        });

        expTv1.setText(getString(R.string.dummy_text1),"展开...");
//        expTv2.setText(getString(R.string.dummy_text2));

    }
}
