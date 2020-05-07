package com.example.foodify.ShoppingCart;

import com.example.foodify.Product.ProductItem;

public class ShoppingCartItem {


    private int mQuantity;
    private boolean checked;
    private ProductItem mItem;


    public ShoppingCartItem(ProductItem item){
        mItem = item;
        checked = false;
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

    public void setQuantity(int quantity){mQuantity = quantity;}

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public ProductItem getItem() {
        return mItem;
    }
}
