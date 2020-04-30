package com.example.foodify.Product;

import java.util.ArrayList;

/**
 * @author jentevandersanden
 * This data class represents a product
 */
public class ProductItem {

    private String m_name;                      // Name of the product
    private float m_price;                      // Price of the product
    private String m_description;               // Description of the product
    private float m_likability;                 // The percentage of likes on this product
    private ArrayList<Comment> m_comments;      // Comments on this product

    /**
     * CONSTRUCTOR
     */
    ProductItem(String name, float price, String description, float likability, ArrayList<Comment> comments){
        m_name = name;
        m_price = price;
        m_description = description;
        m_likability = likability;
        m_comments = new ArrayList<>(comments);
    }

}
