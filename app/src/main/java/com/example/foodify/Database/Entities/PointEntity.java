package com.example.foodify.Database.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author jorisbertram
 * Class that represents the row PointEntity in the table Points */
@Entity(tableName = "Points")
public class PointEntity {

    /** Columns */
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int ID;

    @ColumnInfo(name = "logo")
    private int logo;

    @ColumnInfo(name = "shop")
    private String shop;

    @ColumnInfo(name = "points")
    private int points;


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

    public int getLogo() { return logo; }

    public void setLogo(int logo) { this.logo = logo; }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
