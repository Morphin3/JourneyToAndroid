package com.taoism.journeytoandroid.database;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.taoism.journeytoandroid.BaseActivity;
import com.taoism.journeytoandroid.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Date: 2017-02-05
 * Time: 10:39
 * Author: cf
 * -----------------------------
 */

public class DatabaseDemo extends BaseActivity {


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_database_demo);
//
//        SQLiteDatabase db = openOrCreateDatabase("test.db", Context.MODE_PRIVATE, null);
//        db.execSQL("DROP TABLE IF EXISTS PERSON");
//
//
//        //创建表
//        db.execSQL("CREATE TABLE person(_id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,age SMALLINT)");


//    Person person = new Person();
//    person.name = "john";
//    person.age = 30;
//  //    插入数据
//    db.execSQL("INSERT INTO person VALUES (NULL, ?, ?)", new Object[]{person.name, person.age});

//
//        Person person = new Person();
//        person.setName("David");
//        person.setAge(33);
//
//        //ContentValues以键值对的形式存放数据
//        ContentValues cv = new ContentValues();
//        cv.put("name", person.getName());
//        cv.put("age", person.getAge());
//        //插入ContentValues中的数据
//        db.insert("person", null, cv);
//
//
//        cv = new ContentValues();
//        cv.put("age", 77);
//        //更新数据
//        db.update("person", cv, "name = ?", new String[]{"David"});
//
//        Cursor c =  db.rawQuery("SELECT * FROM person WHERE age >= ?",new String[]{"33"});
//        while (c.moveToNext()){
//            int _id = c.getInt(c.getColumnIndex("_id"));
//            String name  = c.getString(c.getColumnIndex("name"));
//            int age = c.getInt(c.getColumnIndex("age"));
//
//            Log.i("db", "_id=>" + _id + ", name=>" + name + ", age=>" + age);
//        }
//
//        c.close();
//
//        //删除数据
//        db.delete("person","age < ?",new String[]{"20"});
//
//        //关闭数据库
//        db.close();
//
//
//
//    }


    private DBManager mgr;
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_demo);
        listView = (ListView) findViewById(R.id.listView);
        //初始化DBManager
        mgr = new DBManager(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //应用的最后一个Activity关闭时应释放DB
        mgr.closeDB();
    }

    public void add(View view) {
        ArrayList<Person> persons = new ArrayList<Person>();

        Person person1 = new Person("Ella", 22, "lively girl");
        Person person2 = new Person("Jenny", 22, "beautiful girl");
        Person person3 = new Person("Jessica", 23, "sexy girl");
        Person person4 = new Person("Kelly", 23, "hot baby");
        Person person5 = new Person("Jane", 25, "a pretty woman");

        persons.add(person1);
        persons.add(person2);
        persons.add(person3);
        persons.add(person4);
        persons.add(person5);

        mgr.add(persons);
    }

    public void update(View view) {
        Person person = new Person();
        person.name = "Jane";
        person.age = 30;
        mgr.updateAge(person);
    }

    public void delete(View view) {
        Person person = new Person();
        person.age = 30;
        mgr.deleteOldPerson(person);
    }

    public void query(View view) {
        List<Person> persons = mgr.query();
        ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (Person person : persons) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("name", person.name);
            map.put("info", person.age + " years old, " + person.info);
            list.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, list, android.R.layout.simple_list_item_2,
                new String[]{"name", "info"}, new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(adapter);
    }

    public void queryTheCursor(View view) {
        Cursor c = mgr.queryTheCursor();
        startManagingCursor(c); //托付给activity根据自己的生命周期去管理Cursor的生命周期
        CursorWrapper cursorWrapper = new CursorWrapper(c) {
            @Override
            public String getString(int columnIndex) {
                //将简介前加上年龄
                if (getColumnName(columnIndex).equals("info")) {
                    int age = getInt(getColumnIndex("age"));
                    return age + " years old, " + super.getString(columnIndex);
                }
                return super.getString(columnIndex);
            }
        };
        //确保查询结果中有"_id"列
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
                cursorWrapper, new String[]{"name", "info"}, new int[]{android.R.id.text1, android.R.id.text2});
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }


}
