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

}
