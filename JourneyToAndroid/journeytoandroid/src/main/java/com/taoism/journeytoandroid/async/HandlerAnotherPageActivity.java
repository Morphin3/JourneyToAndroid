package com.taoism.journeytoandroid.async;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.taoism.journeytoandroid.BaseActivity;
import com.taoism.journeytoandroid.R;

/**
 * Date: 2017-05-26
 * Time: 11:26
 * Author: cf
 * -----------------------------
 */

public class HandlerAnotherPageActivity extends BaseActivity implements View.OnClickListener {

    private MyThread mBackgrdouThread;
    private Handler  mBackgroudHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_another_page);

        findViewById(R.id.btn_async).setOnClickListener(this);
        findViewById(R.id.btn_quit).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_async:

                mBackgrdouThread = MyThread.getMyThread("async");
                mBackgroudHandler = mBackgrdouThread.getBackgroundHandler();
                Message message = Message.obtain();

                mBackgroudHandler.sendMessage(message);

                break;

            case R.id.btn_quit:
                mBackgrdouThread.quitSafely();
                break;
        }
    }

}
