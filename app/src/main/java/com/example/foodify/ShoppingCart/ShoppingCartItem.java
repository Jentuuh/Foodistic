package com.example.foodify.ShoppingCart;

import com.example.foodify.Product.ProductItem;

public class ShoppingCartItem {


    private int mQuantity;
    private ProductItem mItem;


    public ShoppingCartItem(ProductItem item){
        mItem = item;
        mQuantity = 1;
    }

    public void removeOne(){
        if(mQuantity > 0){
            mQuantity--;
        }
    }

    public void addOne(){
        mQuantity++;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public ProductItem getItem() {
        return mItem;
    }
}
