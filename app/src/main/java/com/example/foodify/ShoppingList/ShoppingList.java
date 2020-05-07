package com.example.foodify.ShoppingList;

import android.content.Context;
import android.util.Log;

import com.example.foodify.Database.AppDatabase;
import com.example.foodify.Database.Entities.ProductOnListEntity;
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
    public void addItem(ProductItem item_to_add, Context context){
        for(ShoppingCartItem i : m_products_on_list){
            if (i.getItem().getName().equals(item_to_add.getName())){ // if there's an item in the cart with the same name
                i.addOne(); // quantity += 1
                updateDataBase(i.getItem(), CartItemModification.PLUS, context);
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

    public void addItem(ShoppingCartItem item_to_add, Context context ){
        m_products_on_list.add(item_to_add);
        setChanged();
        notifyObservers();
    }

    /**
     * Removes a product from this list
     */
    public void removeItem(ShoppingCartItem item_to_remove, Context context){
        removeProductFromListDB(item_to_remove.getItem(), context);
        m_products_on_list.remove(item_to_remove);
        setChanged();
        notifyObservers();
    }

    /**
     * Makes sure the quantity goes up by one.
     * @param pos
     */
    public void addByPos(int pos, Context context){
        if(m_products_on_list.get(pos) != null) {
            m_products_on_list.get(pos).addOne();
            updateDataBase(m_products_on_list.get(pos).getItem(), CartItemModification.PLUS, context);
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Makes sure the quantity goes down by one, if the quantity is 0,
     * the product will be removed
     * @param pos
     */
    public void removeByPos(int pos, Context context){
        ShoppingCartItem item = m_products_on_list.get(pos);
        if (item.getQuantity() > 1){
            item.removeOne();
            updateDataBase(item.getItem(), CartItemModification.MINUS, context);
        }
        else{   // if theres only one left
            removeItem(item,context); //TODO maybe ask for confirmation
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
     * Updates the database when a product was removed from a list
     * @param item_to_remove : The product that was removed from this list
     */
    private void removeProductFromListDB(ProductItem item_to_remove, Context context){
        // TODO: DB
        AppDatabase db = AppDatabase.getDatabase(context);
        db.m_foodisticDAO().removeProductFromList(item_to_remove.getM_id(), m_id);
    }

    /**
     * Updates the database when the amount of a product was changed
     * @param item : The product of which the amount was changed
     * @param mod : The modification that happened to this product
     */
    private void updateDataBase(ProductItem item, CartItemModification mod, Context context){
        AppDatabase db = AppDatabase.getDatabase(context);
        int current_quantity = db.m_foodisticDAO().getProductQuantity(item.getM_id(), m_id);

        if(mod == CartItemModification.PLUS){
            db.m_foodisticDAO().updateProductQuantity(m_id, item.getM_id(), current_quantity + 1);

        }
        else{
            db.m_foodisticDAO().updateProductQuantity(m_id, item.getM_id(), current_quantity - 1);
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
