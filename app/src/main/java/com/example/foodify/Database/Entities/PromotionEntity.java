package com.example.foodify.Database.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author jorisbertram
 * Class that represents the row PromotionEntity in the table Promotions */
@Entity(tableName = "Promotions", primaryKeys = {"name", "shop"})
public class PromotionEntity  {

    /** Columns */
    @ColumnInfo(name = "image")
    private int image;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    @ColumnInfo(name = "shop")
    private String shop;

    @ColumnInfo(name = "points")
    private int points;


    /**
     * GETTERS AND SETTERS
     */

    public int getImage() {
        return image;
    }
    public void setImage(int image) {
        this.image = image;
    }

    public @NonNull String getName() {
        return name;
    }
    public void setName(@NonNull String name) {
        this.name = name;
    }

    public @NonNull String getShop() {
        return shop;
    }
    public void setShop(@NonNull String shop) {
        this.shop = shop;
    }

    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
}

