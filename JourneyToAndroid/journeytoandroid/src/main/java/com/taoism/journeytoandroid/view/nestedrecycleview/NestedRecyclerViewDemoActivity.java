package com.taoism.journeytoandroid.view.nestedrecycleview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.taoism.journeytoandroid.R;

/**
 * Date: 2015-07-05
 * Time: 21:56
 * Author: cf
 * -----------------------------
 */
public class NestedRecyclerViewDemoActivity extends Activity {

    private RecyclerView rv_content;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_recyclerview_demo_activity);

        rv_content = (RecyclerView)findViewById(R.id.rv_content);
        mAdapter = new RecyclerViewAdapter(this);

        rv_content.setLayoutManager(new LinearLayoutManager(this));
        rv_content.setAdapter(mAdapter);
    }





}
