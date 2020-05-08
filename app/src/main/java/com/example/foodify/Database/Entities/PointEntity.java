package com.example.foodify.Database.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Class that represents the row PointEntity in the table points
 * @author jorisbertram
 */
@Entity(tableName = "Points")
public class PointEntity {

    /** Columns */
    @ColumnInfo(name = "logo")
    private int logo;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "shop")
    private String shop;

    @ColumnInfo(name = "points")
    private int points;


    /**
     * GETTERS AND SETTERS
     */

    public int getLogo() { return logo; }
    public void setLogo(int logo) { this.logo = logo; }

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
