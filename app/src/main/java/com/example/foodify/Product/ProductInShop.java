package com.example.foodify.Product;

/**
 * @author jentevandersanden
 * This represents a product that's sold in a shop (different products can have different prices in different shops
 */
public class ProductInShop {

    private String m_productname;
    private float m_price_in_shop;
    private String m_shopname;
    private boolean m_availability;

    public ProductInShop(String m_productname, float m_price_in_shop, String m_shopname, boolean availability) {
        this.m_productname = m_productname;
        this.m_price_in_shop = m_price_in_shop;
        this.m_shopname = m_shopname;
        this.m_availability = availability;
    }


    /**
     * GETTERS AND SETTERS
     */

    public String getM_productname() {
        return m_productname;
    }

    public void setM_productname(String m_productname) {
        this.m_productname = m_productname;
    }

    public float getM_price_in_shop() {
        return m_price_in_shop;
    }

    public void setM_price_in_shop(float m_price_in_shop) {
        this.m_price_in_shop = m_price_in_shop;
    }

    public String getM_shopname() {
        return m_shopname;
    }

    public void setM_shopname(String m_shopname) {
        this.m_shopname = m_shopname;
    }

    public boolean isM_availability() {
        return m_availability;
    }

    public void setM_availability(boolean m_availability) {
        this.m_availability = m_availability;
    }
}
