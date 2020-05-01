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

    public void removeByPos(int pos){
        mItems.remove(pos);
        setChanged();
        notifyObservers();
    }

    public void addItem(ProductItem item){
        mItems.add(item);
        setChanged();
        notifyObservers();
    }
    public float getTotal(){
        float total = 0;
        for( ProductItem i : mItems){
            total += i.getPrice();
        }
        return total;
    }
    public int getCount(){
        return mItems.size();
    }

    public ArrayList<ProductItem> getItems(){
        return mItems;
    }
}
