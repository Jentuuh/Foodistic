package com.example.foodify.Database.Entities;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * @author jentevandersanden
 * Class that represents the row ProductOnListEntity in the table ProductsOnList*/
@Entity(tableName = "ProductsOnList")
public class ProductOnListEntity {

    /** Columns */
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int ID;

    @ColumnInfo(name = "listname")
    private String listname;

    @ColumnInfo(name = "productname")
    private String productname;

    @ColumnInfo(name = "quantity")
    private int quantity;

    @ColumnInfo(name = "checked")
    private boolean checked;



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

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getListname() {
        return listname;
    }

    public void setListname(String listname) {
        this.listname = listname;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
