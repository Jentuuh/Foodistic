package com.example.foodify;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.foodify.Product.ProductItem;

public class TabAdapter extends FragmentStateAdapter {
    ProductItem mItem;
    public TabAdapter(@NonNull Fragment fragment, ProductItem item) {
        super(fragment);
        mItem = item;
        Log.v("TabAdapter", "created");
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        DescriptionFragment fragment = new DescriptionFragment(mItem);
        return fragment;

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
