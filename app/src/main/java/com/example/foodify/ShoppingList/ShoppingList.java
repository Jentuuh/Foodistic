package com.example.foodify.ShoppingList;

import com.example.foodify.Enums.CartItemModification;
import com.example.foodify.Product.ProductItem;
import com.example.foodify.ShoppingCart.ShoppingCartItem;

import java.util.ArrayList;
import java.util.Observable;

/**
 * @author jentevandersanden
 * Data class that represents a Shopping List
 */
public class ShoppingList extends Observable {
    private String m_name;
    private int m_id;
    private ArrayList<ShoppingCartItem> m_products_on_list;

    ShoppingList(String name, int ID){
        m_name = name;
        m_id = ID;
        m_products_on_list = new ArrayList<>();
        loadProductsFromDatabase(name);
    }


    private void loadProductsFromDatabase(String listname){
        // TODO implement (fill products_on_list)
    }

    /**
     * Gets the total price from all the products on the list
     * @return float: the total price.
     */
    public float getTotalPrice(){
        float total = 0;
        for(ShoppingCartItem i : m_products_on_list){
            total += (i.getItem().getPrice() * i.getQuantity());
        }
        return total;
    }

    /**
     * Adds a product to this list, if this list already contains the product,
     * the amount is increased
     */
    public void addItem(ProductItem item_to_add){
        for(ShoppingCartItem i : m_products_on_list){
            if (i.getItem().getName().equals(item_to_add.getName())){ // if there's an item in the cart with the same name
                i.addOne(); // quantity += 1
                setChanged();
                notifyObservers();
                return;
            }
        }
        //if item is not in the cart
        ShoppingCartItem listItem = new ShoppingCartItem(item_to_add);
        m_products_on_list.add(listItem);
        setChanged();
        notifyObservers();
    }

    /**
     * Removes a product from this list
     */
    public void removeItem(ShoppingCartItem item_to_remove){
        removeProductFromListDB(item_to_remove.getItem());
        m_products_on_list.remove(item_to_remove);
        setChanged();
        notifyObservers();
    }

    /**
     * Removes a certain position in the list
     * @param pos
     */
    public void removeByPosition(int pos){
        ProductItem to_remove = m_products_on_list.get(pos).getItem();
        // Remove from database
        removeProductFromListDB(to_remove);
        // Remove from current instance of the list
        m_products_on_list.remove(pos);
        setChanged();
        notifyObservers();
    }


    /**
     * Makes sure the quantity goes up by one.
     * @param pos
     */
    public void addByPos(int pos){
        if(m_products_on_list.get(pos) != null) {
            m_products_on_list.get(pos).addOne();
            updateDataBase(m_products_on_list.get(pos).getItem(), CartItemModification.PLUS);
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Makes sure the quantity goes down by one, if the quantity is 0,
     * the product will be removed
     * @param pos
     */
    public void removeByPos(int pos){
        ShoppingCartItem item = m_products_on_list.get(pos);
        if (item.getQuantity() > 1){
            item.removeOne();
            updateDataBase(item.getItem(), CartItemModification.MINUS);
        }
        else{   // if theres only one left
            removeItem(item); //TODO maybe ask for confirmation
        }
        setChanged();
        notifyObservers();
    }

    /**
     * //////////////////
     * DATABASE FUNCTIONS
     * //////////////////
     */


    /**
     * Updates the database when a product was added to a list
     * @param item_to_add : The product that was added to this list
     */
    private void addProductToListDB(ProductItem item_to_add){
        // TODO: DB QUERY
    }

    /**
     * Updates the database when a product was removed from a list
     * @param item_to_remove : The product that was removed from this list
     */
    private void removeProductFromListDB(ProductItem item_to_remove){
        // TODO: DB
    }

    /**
     * Updates the database when the amount of a product was changed
     * @param item : The product of which the amount was changed
     * @param mod : The modification that happened to this product
     */
    private void updateDataBase(ProductItem item, CartItemModification mod){
        if(mod == CartItemModification.PLUS){
            // TODO: UPDATE DB +
        }
        else{
            //TODO : UPDATE DB -
        }

    }


    /**
     * GETTERS
     */

    public String getName(){
        return m_name;
    }

    public ArrayList<ShoppingCartItem> getProducts(){
        return m_products_on_list;
    }

    public int getM_id() {
        return m_id;
    }
}
