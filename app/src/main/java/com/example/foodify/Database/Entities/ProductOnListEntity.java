package com.example.foodify.Database.Entities;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * @author jentevandersanden
 * Class that represents the row ProductOnListEntity in the table ProductsOnList*/
@Entity(tableName = "ProductsOnList", foreignKeys = { @ForeignKey(entity = ProductEntity.class, parentColumns = "ID" , childColumns = "productid", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)
                                                    , @ForeignKey(entity = ShoppingListEntity.class, parentColumns = "ID", childColumns = "listID", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)})
public class ProductOnListEntity {

    /** Columns */
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private String ID;

    @ColumnInfo(name = "productid")
    private String productid;

    @ColumnInfo(name = "quantity")
    private int quantity;

    @ColumnInfo(name = "listID")
    private String listID;

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


    public int getQuantity() {
        return quantity;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getListID() {
        return listID;
    }

    public void setListID(String listID) {
        this.listID = listID;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
