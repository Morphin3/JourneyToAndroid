package com.taoism.journeytoandroid.activeandroid.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by chenfei on 14-10-22.
 */
@Table(name = "Categories")
public class Category extends Model {

    @Column(name = "Name")
    public String name;


    //This method is optional,does not affect the foreign key creation.
    public List<Item> items() {
        return getMany(Item.class, "Category");
    }


//    public static getByName(String name){
//        return new Select().from(Category.class).where("Na")
//    }




}
