package com.example.foodify.Product;

import com.example.foodify.User.User;

/**
 * @author jentevandersanden
 * This data class represents a comment on a product
 */
public class Comment {
    private User m_author;      // The user who wrote this comment
    private String m_text;      // The actual comment

    /**
     * CONSTRUCTOR
     */
    Comment(User author, String text){
        m_author = author;
        m_text = text;
    }
}
