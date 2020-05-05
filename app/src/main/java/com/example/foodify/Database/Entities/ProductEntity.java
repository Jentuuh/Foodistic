package com.example.foodify.Database.Entities;


import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.foodify.Enums.FoodStyle;

import java.util.Date;

/**
 * @author jentevandersanden
 * Class that represents the row ProductEntity in the table Products */
@Entity(tableName = "Products")
public class ProductEntity {

    /** Columns */
    @PrimaryKey
    @NonNull
    private String ID;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "price")
    private float price;

    @ColumnInfo(name = "description")
    private String description;

//    @ColumnInfo(name = "likability")
//    private FoodStyle likability;
//
//    @ColumnInfo(name = "img")
//    private Drawable img;


    /**
     * GETTERS AND SETTERS
     */

    @NonNull
    public String getID() {
        return ID;
    }

    public void setID(@NonNull String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public FoodStyle getLikability() {
//        return likability;
//    }
//
//    public void setLikability(FoodStyle likability) {
//        this.likability = likability;
//    }
//
//    public Drawable getImg() {
//        return img;
//    }
//
//    public void setImg(Drawable img) {
//        this.img = img;
//    }
}
