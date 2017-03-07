package com.taoism.journeytoandroid.http;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.taoism.journeytoandroid.BaseActivity;
import com.taoism.journeytoandroid.R;

import java.io.File;

/**
 * Date: 2017-02-28
 * Time: 16:01
 * Author: cf
 * -----------------------------
 */

public class HttpDemoActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvResponse;

    private String mUrl = "http://10.242.54.138:8001/?name=chenfei&website=www.baidu.com&user=admin&pass=123467";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_demo);

        findViewById(R.id.btn_httpclient).setOnClickListener(this);
        findViewById(R.id.btn_httpurlconnection).setOnClickListener(this);
        findViewById(R.id.btn_okhttp).setOnClickListener(this);

        tvResponse = (TextView) findViewById(R.id.tv_response);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_httpclient:
                requestByHttpClient();
                break;
            case R.id.btn_httpurlconnection:
                tvResponse.setText(NetUtils.get(mUrl));
                break;
            case R.id.btn_okhttp:
                //TODO implement
                break;
        }
    }


    private void requestByHttpClient() {

        try {
            long httpCacheSize = 10 * 1024 * 1024; // 10 MiB
            File httpCacheDir = new File(getCacheDir(), "http");
            Class.forName("android.net.http.HttpResponseCache")
                    .getMethod("install", File.class, long.class)
                    .invoke(null, httpCacheDir, httpCacheSize);
        } catch (Exception httpResponseCacheNotAvailable) {

        }

    }


}
