package com.taoism.journeytoandroid.activeandroid.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;

import java.util.List;

/**
 * Created by chenfei on 14-10-22.
 */
public class Category extends Model {

    @Column(name = "Name")
    public String name;


    //This method is optional,does not affect the foreign key creation.
    public List<Item> items() {
        return getMany(Item.class, "Category");
    }




}
