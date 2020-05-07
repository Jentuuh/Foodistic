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

    @ColumnInfo(name = "productid")
    private int productid;

    @ColumnInfo(name = "productname")
    private String productname;

    @ColumnInfo(name = "quantity")
    private int quantity;

    @ColumnInfo(name = "listID")
    private int listID;

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

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public int getListID() {
        return listID;
    }

    public void setListID(int listID) {
        this.listID = listID;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
