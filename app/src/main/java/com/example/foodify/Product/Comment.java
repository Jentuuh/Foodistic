package com.example.foodify.Product;

import com.example.foodify.User.User;

/**
 * @author jentevandersanden
 * This data class represents a comment on a product
 */
public class Comment {
    private String m_author_name;      // The user who wrote this comment
    private String m_text;      // The actual comment

    /**
     * CONSTRUCTOR
     */
    public Comment(String author_name, String text){
        m_author_name = author_name;
        m_text = text;
    }


    /**
     * GETTERS
     */
    public String getM_author_name() {
        return m_author_name;
    }

    public String getM_text() {
        return m_text;
    }
}
