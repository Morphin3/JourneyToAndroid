package com.taoism.journeytoandroid.intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.taoism.journeytoandroid.R;

/**
 * Created by Morphin3 on 5/22/15.
 */
public class IntentDemo extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_intent);
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                3 显示拨号面板， 并在拨号面板上将号码显示出来
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("tel//15868113766"));
                startActivity(intent);
            }
        });

        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//   4 显示拨号面板， 并在拨号面板上将号码显示出来
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel://15216448315"));
                startActivity(intent);
            }
        });

        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//   4 显示拨号面板， 并在拨号面板上将号码显示出来
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel://15216448315"));
                startActivity(intent);
            }
        });


        findViewById(R.id.btn6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//   6 显示拨号面板， 并在拨号面板上将号码显示出来
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("content://contacts/people/"));
                startActivity(intent);
            }
        });

        findViewById(R.id.btn7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//   7 启动HomeScreen
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_MAIN);     //启动HomeScreen
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }
        });


    }


}
