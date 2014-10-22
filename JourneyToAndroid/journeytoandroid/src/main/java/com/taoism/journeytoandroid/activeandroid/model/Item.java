package com.taoism.journeytoandroid.activeandroid.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;

/**
 * Created by chenfei on 14-10-22.
 */
public class Item extends Model {
    //If name is omitted,the the field name is used.

    @Column(name = "Name")
    public String name;

    @Column(name = "Category")
    public Category category;

    public Item() {
        super();
    }

    public Item(String name, Category category) {
        super();
        this.name = name;
        this.category = category;
    }
}
