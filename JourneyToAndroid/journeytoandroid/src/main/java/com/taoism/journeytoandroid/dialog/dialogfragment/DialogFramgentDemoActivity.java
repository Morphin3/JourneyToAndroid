package com.taoism.journeytoandroid.dialog.dialogfragment;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.taoism.journeytoandroid.R;

/**
 * Date: 2015-11-03
 * Time: 09:45
 * Author: cf
 * -----------------------------
 */
public class DialogFramgentDemoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_fragment_demo);

        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                MyDialogFragment myDialogFragment = new MyDialogFragment();
                myDialogFragment.show(ft,"dialog_tag");
            }
        });

    }

}
