package com.example.foodify.ProductPage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.foodify.Product.ProductInShop;
import com.example.foodify.R;

import java.util.List;

public class ComparisonAdapter extends ArrayAdapter {

    private int resourceLayout;
    private Context mContext;
    private List<ProductInShop> products_in_shops;
    private Fragment mFragment;

    public ComparisonAdapter(@NonNull Context context, int resource, List<ProductInShop> products_to_display, Fragment fragment) {
        super(context, 0, products_to_display);
        resourceLayout = resource;
        this.mContext = context;
        this.products_in_shops = products_to_display;
        mFragment = fragment;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(resourceLayout, null);

        }
        ProductInShop product = products_in_shops.get(position);

        if(product != null){
            TextView shopname = (TextView) view.findViewById(R.id.shopname);
            TextView price = (TextView) view.findViewById(R.id.price);
            TextView availability = (TextView) view.findViewById(R.id.availability);

            if(shopname != null){
                shopname.setText(product.getM_shopname());
            }
            if(price != null){
                price.setText(Float.toString(product.getM_price_in_shop()));
            }
            if(availability != null){
                if(product.isM_availability()){
                    availability.setText("Op voorraad");
                }
                else {
                    availability.setText("Niet op voorraad");
                }
            }
        }

        return view;
    }
}
