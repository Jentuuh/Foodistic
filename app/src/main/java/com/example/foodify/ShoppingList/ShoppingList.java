package com.example.foodify.ShoppingList;

import com.example.foodify.Product.ProductItem;

import java.util.ArrayList;
import java.util.Observable;

/**
 * @author jentevandersanden
 * Data class that represents a Shopping List
 */
public class ShoppingList extends Observable {
    private String m_name;
    private ArrayList<ProductItem> m_products_on_list;

    ShoppingList(String name){
        m_name = name;
        m_products_on_list = new ArrayList<>();
        loadProductsFromDatabase(name);
    }


    private void loadProductsFromDatabase(String listname){
        // TODO implement (fill products_on_list)
    }

    /**
     * Adds a product to this list
     */
    public void addItem(ProductItem item_to_add){
        m_products_on_list.add(item_to_add);
        addProductToListDB(item_to_add);
        setChanged();
        notifyObservers();
    }

    /**
     * Removes a product from this list
     */
    public void removeItem(ProductItem item_to_remove){
        m_products_on_list.remove(item_to_remove);
        removeProductFromListDB(item_to_remove);
        setChanged();
        notifyObservers();
    }

    /**
     * Removes a certain position in the list
     * @param pos
     */
    public void removeByPosition(int pos){
        ProductItem to_remove = m_products_on_list.get(pos);
        // Remove from database
        removeProductFromListDB(to_remove);
        // Remove from current instance of the list
        m_products_on_list.remove(pos);
        setChanged();
        notifyObservers();
    }

    /**
     * Updates the database when a product was added to a list
     * @param item_to_add : The product that was added to this list
     */
    private void addProductToListDB(ProductItem item_to_add){

    }

    /**
     * Updates the database when a product was removed from a list
     * @param item_to_remove : The product that was removed from this list
     */
    private void removeProductFromListDB(ProductItem item_to_remove){

    }

    /**
     * GETTERS AND SETTERS
     */

    public String getName(){
        return m_name;
    }

    public ArrayList<ProductItem> getProducts(){
        return m_products_on_list;
    }

}
