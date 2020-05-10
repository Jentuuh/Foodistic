package com.example.foodify.Database.Entities;


import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.foodify.Enums.Categories;
import com.example.foodify.Enums.FoodStyle;

import java.util.Date;

/**
 * @author jentevandersanden
 * Class that represents the row ProductEntity in the table Products */
@Entity(tableName = "Products")
public class ProductEntity {

    /** Columns */
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int ID;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "price")
    private float price;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "likability")
    private float likability;

    @ColumnInfo(name = "discount")
    private float discount;

    @ColumnInfo(name = "foodstyle")
    private String foodstyle;

    @ColumnInfo(name = "category")
    private String category;

//    @ColumnInfo(name = "img")
    //    private Drawable img;


    /**
     * GETTERS AND SETTERS
     */

    @NonNull
    public int getID() {
        return ID;
    }

    public void setID(@NonNull int ID) {
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

    public float getLikability() {
        return likability;
    }

    public void setLikability(float likability) {
        this.likability = likability;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public FoodStyle getFoodstyleEnum(){

        if (foodstyle != null) {
            return FoodStyle.valueOf(this.foodstyle);
        }
        else {
            Log.v("prodentity", "foodstyle=null");
            return null;
        }
    }

    public String getFoodstyle(){return this.foodstyle;}

    public void setFoodstyle(String foodstyle){this.foodstyle = foodstyle;}

    public void setFoodstyle(FoodStyle fStyle){this.foodstyle = fStyle.toString();}

    public String getCategory(){return this.category;}
    public void setCategory(String newCategory){this.category = newCategory;}

    public void setCategory(Categories newcat){this.category = newcat.toString();}

    public Categories getEnumCategory(){
        if (category != null)
            return Categories.valueOf(this.category);
        else {
            Log.v("prodentity", "category=null");
            return null;
    }}




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
