package com.taoism.journeytoandroid.activeandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.taoism.journeytoandroid.R;
import com.taoism.journeytoandroid.activeandroid.model.Category;
import com.taoism.journeytoandroid.activeandroid.model.Item;
import com.taoism.journeytoandroid.interfaces.InitializeInterface;

/**
 * Created by chenfei on 14-10-22.
 */
public class ActiveAndroidDemo extends Activity implements InitializeInterface, View.OnClickListener {

    Button save_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activeandroid);
        initCustom();
        initListener();
    }


    @Override
    public void initCustom() {
        save_btn = (Button) findViewById(R.id.save_btn);
    }

    @Override
    public void initListener() {
        save_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int rid = v.getId();
        if (rid == R.id.save_btn) {
            Category singers = new Category();
            singers.name = "歌手";
            singers.save();

            Item item = new Item();
            item.category = singers;
            item.name = "陶喆";
            item.save();

            item = new Item();
            item.category = singers;
            item.name = "王力宏";
            item.save();

            item = new Item();
            item.category = singers;
            item.name = "周杰伦";
            item.save();

        }
    }
}
