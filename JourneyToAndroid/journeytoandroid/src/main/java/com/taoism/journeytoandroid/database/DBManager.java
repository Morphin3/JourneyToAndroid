package com.taoism.journeytoandroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2017-02-05
 * Time: 11:46
 * Author: cf
 * -----------------------------
 */

public class DBManager {

    private DBHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {

        helper = new DBHelper(context);

        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里

        db = helper.getWritableDatabase();

    }


    public void add(List<Person> persons) {
        db.beginTransaction();//开始事务
        try {
            for (Person person : persons) {
                db.execSQL("INSERT INTO person VALUES(null, ?, ?, ?)", new Object[]{person.getName(), person.getAge(), person.getInfo()});
            }
            db.setTransactionSuccessful();//设置事务成功完成
        } finally {
            db.endTransaction();//结束事务
        }
    }

    public void updateAge(Person person) {
        ContentValues cv = new ContentValues();
        cv.put("age", person.getAge());
        db.update("person", cv, "name = ?", new String[]{person.getName()});
    }


    public void deleteOldPerson(Person person) {
        db.delete("person", "age >= ?", new String[]{String.valueOf(person.getAge())});
    }


    public List<Person> query() {
        ArrayList persons = new ArrayList();

        Cursor c = queryTheCursor();
        while (c.moveToNext()) {
            Person person = new Person();
            person.set_id(c.getInt(c.getColumnIndex("_id")));
            person.setName(c.getString(c.getColumnIndex("name")));
            person.setAge(c.getInt(c.getColumnIndex("age")));
            person.setInfo(c.getString(c.getColumnIndex("info")));
            persons.add(person);
        }
        c.close();
        return persons;
    }

    public Cursor queryTheCursor() {
        Cursor c = db.rawQuery("SELECT * FROM person", null);
        return c;
    }

    public void closeDB() {
        db.close();
    }
}
