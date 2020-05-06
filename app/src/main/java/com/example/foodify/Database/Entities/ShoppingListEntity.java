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
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int ID;

    @ColumnInfo(name = "name")
    private String name;

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
}
