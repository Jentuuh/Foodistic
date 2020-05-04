package com.example.foodify.PointSystem;

public class ShopPoint {
    private String m_name;
    private int m_points;

    ShopPoint(String name, int points) {
        m_name = name;
        m_points = points;
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
