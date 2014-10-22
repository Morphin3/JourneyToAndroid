package com.taoism.journeytoandroid.activeandroid.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;

/**
 * Created by chenfei on 14-10-22.
 */
public class Item extends Model {

    @Column(name = "Name")
    public String name;

    

}
