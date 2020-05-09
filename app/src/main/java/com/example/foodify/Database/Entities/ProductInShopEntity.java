package com.example.foodify.Database.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


/**
 * @author jentevandersanden
 * This entity represents a product that's sold in a certain store for a certain price.
 */
@Entity(tableName = "ProductsInShops")
public class ProductInShopEntity {


    /** Columns */
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int ID;

    @ColumnInfo(name = "productname")
    private String productname;

    @ColumnInfo(name = "shopname")
    private String shopname;

    @ColumnInfo(name = "price")
    private float price;


    /**
     * GETTERS AND SETTERS
     */

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
