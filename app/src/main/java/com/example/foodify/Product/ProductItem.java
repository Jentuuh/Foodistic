package com.example.foodify.Product;

import android.graphics.drawable.Drawable;
import android.media.Image;

import com.example.foodify.Enums.FoodStyle;
import com.example.foodify.R;
import com.google.android.material.animation.DrawableAlphaProperty;

import java.util.ArrayList;

/**
 * @author jentevandersanden
 * This data class represents a product
 */
public class ProductItem {

    private String m_name;                      // Name of the product
    private float m_price;                      // Price of the product
    private String m_description;               // Description of the product
    private FoodStyle m_foodstyle;
    private float m_likability;                 // The percentage of likes on this product
    private ArrayList<Comment> m_comments;      // Comments on this product
    private float m_discount;
    private Drawable m_image;

    /**
     * CONSTRUCTORS
     */
    public ProductItem(String name, float price, String description, float likability, ArrayList<Comment> comments, Drawable img){
        this(name,price,description,likability,comments,0,img);


    }

    public ProductItem(String name, float price, String description, float likability, ArrayList<Comment> comments, float discount,Drawable img){
        m_discount = discount;
        m_name = name;
        m_price = price;
        m_description = description;
        m_likability = likability;

        if (comments != null)
            m_comments = new ArrayList<>(comments);
        else
            m_comments=null;
        if (img != null)
            m_image = img;
        else
            m_image = null;
    }

    /**
     *  GETTERS
     */
    public String getName(){return m_name;}
    public float getPrice(){return m_price;}
    public String getDescription(){return m_description;}
    public float getLikability(){return m_likability;}
    public ArrayList<Comment> getComments(){return m_comments;}
    public Drawable getImage(){return m_image;};
    public float getDiscount(){return m_discount;}
    public FoodStyle getFoodstyle(){return m_foodstyle;}

    /**
     * Setters
     */
    public void setImage(Drawable img){m_image = img;}
    public void setDiscount(float discount){m_discount = discount;}


    public float calculatePrice(){
        return m_price - (m_price * m_discount);
    }
}
