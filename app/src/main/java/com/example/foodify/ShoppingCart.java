package com.example.foodify;

import com.example.foodify.Product.ProductItem;

import java.util.ArrayList;
import java.util.Observable;
import androidx.navigation.Navigation;

/**
 * Class to represent the shopping cart
 */
public class ShoppingCart extends Observable {
    private ArrayList<ShoppingCartItem> mItems;

    ShoppingCart(){
    mItems = new ArrayList<ShoppingCartItem>();

    }

    /**
     * remove a certain position in the list
     * @param pos
     */
    public void removeByPos(int pos){
        ShoppingCartItem item = mItems.get(pos);
        if (item.getQuantity() > 1){
            item.removeOne();
        }
        else{   // if theres only one left
            mItems.remove(pos); //TODO maybe ask for confirmation
        }
        setChanged();
        notifyObservers();
    }

    public void addByPos(int pos){
        if(mItems.get(pos) != null)
            mItems.get(pos).addOne();
    }

    /**
     * add item to shoppingcart
     * @param item
     */
    public void addItem(ProductItem item){
        for(ShoppingCartItem i : mItems){
            if (i.getItem().getName().equals(item.getName())){ // if there's an item in the cart with the same name
                i.addOne(); // quantity += 1
                setChanged();
                notifyObservers();
                return;
            }
        }
        //if item is not in the cart
        ShoppingCartItem cartItem = new ShoppingCartItem(item);
        mItems.add(cartItem);
        setChanged();
        notifyObservers();
    }

    /**
     * get the total amount of the shoppingcart
     * @return
     */
    public float getTotal(){
        float total = 0;
        for( ShoppingCartItem i : mItems){
            total += (i.getItem().getPrice() * i.getQuantity());
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
    public ArrayList<ShoppingCartItem> getItems(){
        return mItems;
    }
}
