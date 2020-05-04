package com.example.foodify.Database.Entities;


import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.foodify.Enums.FoodStyle;

/**
 * @author jentevandersanden
 * Class that represents the row ShoppingListEntity in the table ShoppingLists */
@Entity(tableName = "ShoppingLists")
public class ShoppingListEntity {

    /** Columns */
    @PrimaryKey
    @NonNull
    private String ID;

    @ColumnInfo(name = "name")
    private String name;

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
}
