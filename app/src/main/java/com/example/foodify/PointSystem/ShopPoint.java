package com.example.foodify.PointSystem;

/**
 * Class that holds the points of shop data
 * @author jorisbertram
 */
public class ShopPoint {
    private int m_logo;
    private String m_name;
    private int m_points;

    ShopPoint(int logo, String name, int points) {
        m_logo = logo;
        m_name = name;
        m_points = points;
    }

    public int getLogo() {
        return m_logo;
    }
    public int getPoints() {
        return m_points;
    }
    public String getName() {
        return m_name;
    }

    public void addPoints(int points) {
        m_points += points;
    }
}
