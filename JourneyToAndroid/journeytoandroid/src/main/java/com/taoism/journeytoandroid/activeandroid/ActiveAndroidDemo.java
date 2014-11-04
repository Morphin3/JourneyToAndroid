package com.taoism.journeytoandroid.activeandroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.taoism.journeytoandroid.BaseActivity;
import com.taoism.journeytoandroid.R;
import com.taoism.journeytoandroid.activeandroid.model.Category;
import com.taoism.journeytoandroid.activeandroid.model.Item;
import com.taoism.journeytoandroid.interfaces.InitializeInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenfei on 14-10-22.
 */
public class ActiveAndroidDemo extends BaseActivity implements InitializeInterface, View.OnClickListener {

    Button btn_insert;
    Button btn_bulk_insert;

    private static final String TAG = "active_android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activeandroid);
        initCustom();
        initListener();
    }


    @Override
    public void initCustom() {
        btn_insert = (Button) findViewById(R.id.btn_insert);
        btn_bulk_insert = (Button) findViewById(R.id.btn_bulk_insert);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_query_random).setOnClickListener(this);
        findViewById(R.id.btn_query_random_category).setOnClickListener(this);
        findViewById(R.id.btn_query_all_category).setOnClickListener(this);
    }

    @Override
    public void initListener() {
        btn_insert.setOnClickListener(this);
        btn_bulk_insert.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int rid = v.getId();
        if (rid == R.id.btn_insert) {
            Category singers = new Category();
            singers.name = "歌手";
            singers.save();

            Category actors = new Category();
            actors.name = "演员";
            actors.save();

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

            item = new Item();
            item.category = actors;
            item.name = "周星驰";
            item.save();

            item = new Item();
            item.category = actors;
            item.name = "林青霞";
            item.save();

            item = new Item();
            item.category = actors;
            item.name = "高圆圆";
            item.save();

            item = new Item();
            item.category = actors;
            item.name = "张国荣";
            item.save();

            Log.i(TAG, "save成功");
            mToast.setText("save成功");
            mToast.show();
        } else if (rid == R.id.btn_bulk_insert) {
            ActiveAndroid.beginTransaction();
            Category walkon = new Category();
            walkon.name = "打杂";
            walkon.save();
            try {
                for (int i = 0; i < 100; i++) {
                    Item item = new Item();
                    item.name = "龙套" + i;
                    item.category=walkon;
                    item.save();
                }
                ActiveAndroid.setTransactionSuccessful();
            } finally {
                ActiveAndroid.endTransaction();
            }
            Log.i(TAG, "批量save成功");
            mToast.setText("批量save成功");
            mToast.show();
        } else if (rid == R.id.btn_delete) {
//            Item item = Item.load(Item.class, 1);
//            item.delete();

//            Item.delete(Item.class,1);
//            new Delete().from(Item.class).where("Id=?", 1).execute();
            new Delete().from(Item.class).execute();
            new Delete().from(Category.class).execute();
            Log.i(TAG, "delete成功");
            mToast.setText("delete成功");
            mToast.show();
        } else if (rid == R.id.btn_query_random) {
            Item item = Item.getRandom();
            if (item != null) {
                mToast.setText("id:" + item.getId() + " 姓名:" + item.name + " 分类:" + item.category.name);
                mToast.show();
            } else {
                mToast.setText("空");
                mToast.show();
            }

        } else if (rid == R.id.btn_query_random_category) {
            Category category = new Select().from(Category.class).executeSingle();
            if (category != null) {
                Item item = Item.getRandom(category);
                List<Item> items = new ArrayList<Item>();
                items.add(item);
                if (items.size() != 0) {
                    displayList(items);
                }
            } else {
                mToast.setText("空");
                mToast.show();
            }


        } else if (rid == R.id.btn_query_all_category) {
            Category category = new Select().from(Category.class).orderBy("RANDOM()").executeSingle();
            if (category != null) {
                List<Item> items = Item.getAll(category);
                if (items.size() != 0) {
                    displayList(items);
                }
            } else {
                mToast.setText("空");
                mToast.show();
            }
        }

    }

    private void displayList(List<?> list) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            Item item = (Item) list.get(i);
            stringBuilder.append("id:" + item.getId() + " 姓名:" + item.name + " 分类:" + item.category.name + "\n");
        }
        mToast.setText(stringBuilder.toString());
        mToast.show();
    }
}
