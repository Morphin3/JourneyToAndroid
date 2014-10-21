package com.taoism.journeytoandroid.intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.taoism.journeytoandroid.R;

/**
 * Created by chenfei on 14-8-28.
 * <p/>
 * Intent data 属性
 */
public class IntentDataAttr extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_intent_data_attr);


        findViewById(R.id.bn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                String data = "http://www.baidu.com";
                Uri uri = Uri.parse(data);

                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(uri);
                startActivity(intent);
            }
        });

        findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_EDIT);
//                intent.setAction(Intent.ACTION_VIEW);
                String data = "content://com.android.contacts/contacts/1";
//                String data = "content://contacts/people";
                Uri uri = Uri.parse(data);
                intent.setData(uri);
                startActivity(intent);
            }
        });

    }
}
