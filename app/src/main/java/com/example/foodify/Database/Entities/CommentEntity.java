package com.example.foodify.Database.Entities;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author jentevandersanden
 * Entity that represents the comments on a product
 */
@Entity(tableName = "Comments")
public class CommentEntity {

    /** Columns */
    @ColumnInfo(name = "ID")
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int ID;

    @ColumnInfo(name = "productname")
    private String productname;

    @ColumnInfo(name = "author")
    private String author;

    @ColumnInfo(name = "commentText")
    private String commentText;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
