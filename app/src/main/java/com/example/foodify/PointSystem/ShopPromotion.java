package com.example.foodify.PointSystem;


/**
 * Class that holds the data of shop promotion
 * @author jorisbertram
 */
public class ShopPromotion {
    private int m_image;
    private String m_name;
    private int m_points;

    ShopPromotion(int image, String name, int points) {
        m_image = image;
        m_name = name;
        m_points = points;
    }

    public int getImage() {
        return m_image;
    }
    public String getName() {
        return m_name;
    }
    public int getPoints() {
        return m_points;
    }
}
