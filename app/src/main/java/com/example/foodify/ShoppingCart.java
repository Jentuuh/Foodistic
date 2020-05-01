package com.example.foodify;

import com.example.foodify.Product.ProductItem;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Class to represent the shopping cart
 */
public class ShoppingCart extends Observable {
    private ArrayList<ProductItem> mItems;

    ShoppingCart(){
    mItems = new ArrayList<ProductItem>();

    }

    /**
     * remove a certain position in the list
     * @param pos
     */
    public void removeByPos(int pos){
        mItems.remove(pos);
        setChanged();
        notifyObservers();
    }

    /**
     * add item to shoppingcart
     * @param item
     */
    public void addItem(ProductItem item){
        mItems.add(item);
        setChanged();
        notifyObservers();
    }

    /**
     * get the total amount of the shoppingcart
     * @return
     */
    public float getTotal(){
        float total = 0;
        for( ProductItem i : mItems){
            total += i.getPrice();
        }
        return total;
    }

    /**
     * get amount of items in shoppingcart
     * @return
     */
    public int getCount(){
        return mItems.size();
    }

    /**
     * get items in shoppingcart
     * @return
     */
    public ArrayList<ProductItem> getItems(){
        return mItems;
    }
}
