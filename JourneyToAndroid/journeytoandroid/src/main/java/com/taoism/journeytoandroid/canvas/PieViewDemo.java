package com.taoism.journeytoandroid.canvas;

import android.os.Bundle;

import com.taoism.journeytoandroid.BaseActivity;
import com.taoism.journeytoandroid.R;

import java.util.ArrayList;

/**
 * Date: 2017-01-30
 * Time: 15:05
 * Author: cf
 * -----------------------------
 */

public class PieViewDemo extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pie_view_demo);


        ArrayList<PieData> data = new ArrayList<>();
        data.add(new PieData("morphin3", 10));
        data.add(new PieData("morphin3", 20));
        data.add(new PieData("morphin3", 30));
        data.add(new PieData("morphin3", 40));
        data.add(new PieData("morphin3", 50));
        data.add(new PieData("morphin3", 60));
        data.add(new PieData("morphin3", 70));

        PieView pieView = (PieView) findViewById(R.id.pv);
        pieView.setData(data);


    }
}
