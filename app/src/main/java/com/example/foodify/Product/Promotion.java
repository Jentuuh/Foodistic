package com.example.foodify.Product;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class Promotion {

    private ArrayList<ProductItem> mImpactedItem;
    private Drawable mPromotionImg;

    public Promotion(Drawable img){
        mPromotionImg = img;

    }

    public ArrayList<ProductItem> getImpactedItem() {
        return mImpactedItem;
    }

    public Drawable getPromotionImg() {
        return mPromotionImg;
    }

    public void setPromotionImg(Drawable img) {
        this.mPromotionImg = img;
    }
}
