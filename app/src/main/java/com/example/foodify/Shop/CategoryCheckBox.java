package com.example.foodify.Shop;

import android.widget.CheckBox;

import com.example.foodify.Enums.Categories;

public class CategoryCheckBox {
    private Categories mCategory;
    private CheckBox mCheckbox;

    public CategoryCheckBox(CheckBox check, Categories cat){
        mCategory = cat;
        mCheckbox = check;
    }

    public Categories getCategory(){
        return mCategory;
    }

    public CheckBox getCheckbox() {
        return mCheckbox;
    }

    public void setChecked(boolean state){
        mCheckbox.setChecked(state);
    }

    public boolean isChecked(){
        return mCheckbox.isChecked();
    }
}
