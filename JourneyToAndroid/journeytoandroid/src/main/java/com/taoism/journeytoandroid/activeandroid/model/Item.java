package com.taoism.journeytoandroid.activeandroid.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by chenfei on 14-10-22.
 */
@Table(name = "Items")
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

    public static Item getRandom() {
        return new Select().from(Item.class).orderBy("RANDOM()").executeSingle();
    }

    public static Item getRandom(Category category) {
        return new Select().from(Item.class).where("Category=?", category.getId()).orderBy("RANDOM()").executeSingle();
    }

    public static List<Item> getAll(Category category) {
        return new Select().from(Item.class).where("Category=?", category.getId()).orderBy("Name ASC").execute();
    }

}
