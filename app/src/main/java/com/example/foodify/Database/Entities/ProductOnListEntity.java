package com.example.foodify.Database.Entities;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * @author jentevandersanden
 * Class that represents the row ProductOnListEntity in the table ProductsOnList*/
@Entity(tableName = "ProductsOnList", foreignKeys = { @ForeignKey(entity = ProductEntity.class, parentColumns = "productname" , childColumns = "name", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)
                                                    , @ForeignKey(entity = ShoppingListEntity.class, parentColumns = "listname", childColumns = "name", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)})
public class ProductOnListEntity {

    /** Columns */
    @PrimaryKey
    @NonNull
    private String ID;

    @ColumnInfo(name = "productname")
    private String productname;

    @ColumnInfo(name = "listname")
    private String listname;

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
}
