package com.example.foodify;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodify.Product.ProductItem;



public class DescriptionFragment extends Fragment {

    ProductItem mItem;

    public DescriptionFragment(ProductItem item) {
        // Required empty public constructor
        mItem = item;
        Log.v("DescriptionFragment", "created");
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_description, container, false);
    }

    public void setItem(ProductItem item){
        mItem = item;
    }
}
