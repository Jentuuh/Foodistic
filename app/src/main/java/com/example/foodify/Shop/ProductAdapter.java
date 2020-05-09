package com.example.foodify.Shop;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodify.Enums.Size;
import com.example.foodify.MainActivity;
import com.example.foodify.Product.ProductItem;
import com.example.foodify.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    private ArrayList<ProductItem> mProductItems, mFilteredList;
    private Fragment mContext;
    private Size mSize;

    public ProductAdapter(ArrayList<ProductItem> itemlist, Fragment context, Size size){
        mProductItems = itemlist;
        mContext = context;
        mSize = size;
        mFilteredList = new ArrayList<ProductItem>();
        mFilteredList.addAll(mProductItems);


    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView itemName;
        TextView itemPrice;
        TextView prevItemPrice;
        ImageView prodImg;
        ImageButton addToCart;
        ImageButton addToList;
        CardView container;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            switch (mSize) {
                case SMALL:
                    itemName = itemView.findViewById(R.id.product_name);
                    itemPrice = itemView.findViewById(R.id.prod_price);
                    prevItemPrice = itemView.findViewById(R.id.prev_prod_price);
                    prodImg = itemView.findViewById(R.id.product_img);
                    addToCart = itemView.findViewById(R.id.add_to_cart_button);
                    addToList = itemView.findViewById(R.id.add_to_list_button);
                    container = itemView.findViewById(R.id.product_container);
                    break;
                case MEDIUM:
                    break;
                case LARGE:
                    itemName = itemView.findViewById(R.id.large_product_name);
                    itemPrice = itemView.findViewById(R.id.large_product_price);
                    prevItemPrice = itemView.findViewById(R.id.large_prev_prod_price);
                    prodImg = itemView.findViewById(R.id.large_product_img);
                    addToCart = itemView.findViewById(R.id.add_to_cart_button);
                    addToList = itemView.findViewById(R.id.add_to_list_button);
                    container = itemView.findViewById(R.id.large_product_container);

                    break;
                default:
                    itemName = itemView.findViewById(R.id.product_name);
                    itemPrice = itemView.findViewById(R.id.prod_price);
                    prevItemPrice = itemView.findViewById(R.id.prev_prod_price);
                    prodImg = itemView.findViewById(R.id.product_img);
                    addToCart = itemView.findViewById(R.id.add_to_cart_button);
                    addToList = itemView.findViewById(R.id.add_to_list_button);
                    container = itemView.findViewById(R.id.product_container);


            }
        }
    }

    public void sortByPriceDESC(){
        clearFilter();
        Collections.sort(mFilteredList, new Comparator<ProductItem>(){
            @Override
            public int compare(ProductItem a, ProductItem b) {
                float aPrice, bPrice;
                aPrice = b.calculatePrice();
                bPrice = a.calculatePrice();
                if (aPrice == bPrice)
                    return 0;
                else if (aPrice > bPrice)
                    return 1;
                else
                    return -1;
            }
        });
    }


    public void sortByPriceASC(){
        clearFilter();
        Collections.sort(mFilteredList, new Comparator<ProductItem>(){
            @Override
            public int compare(ProductItem a, ProductItem b) {
                float aPrice, bPrice;
                aPrice = a.calculatePrice();
                bPrice = b.calculatePrice();
                if (aPrice == bPrice)
                    return 0;
                else if (aPrice > bPrice)
                    return 1;
                else
                    return -1;
            }
        });
    }

    public void sortByHighestDiscount(){
        clearFilter();
        Collections.sort(mFilteredList, new Comparator<ProductItem>(){
            @Override
            public int compare(ProductItem a, ProductItem b) {
                float aDisc, bDisc;
                aDisc = b.getDiscount();
                bDisc = a.getDiscount();
                if (aDisc == bDisc)
                    return 0;
                else if (aDisc > bDisc)
                    return 1;
                else
                    return -1;
            }
        });
    }

    public void resetFilters(){
        mFilteredList.clear();
        addAll();

    }

    private void clearFilter() {
    mFilteredList.clear();
    }

    public void addAll(){
        mFilteredList.addAll(mProductItems);
    }

    public void discountedItems(){
        clearFilter();
        for(ProductItem item : mProductItems){
            if (item.getDiscount() > 0){
                mFilteredList.add(item);
            }
        }
        notifyDataSetChanged();
    }

    public void filter(final String text) {

        // Searching could be complex..so we will dispatch it to a different thread...
        new Thread(new Runnable() {
            @Override
            public void run() {

                // Clear the filter list
                mFilteredList.clear();
                // If there is no search value, then add all original list items to filter list
                if (TextUtils.isEmpty(text)) {
                    mFilteredList.addAll(mProductItems);
                }
                else {

                    // Iterate in the original List and add it to filter list...
                    for (ProductItem item : mProductItems) {
                        if (item.getName().toLowerCase().contains(text.toLowerCase()) ) {
                            // Adding Matched items
                            mFilteredList.add(item);
                        }
                    }
                }

                // Set on UI Thread
                (mContext).getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Notify the List that the DataSet has changed...
                        notifyDataSetChanged();
                    }
                });

            }
        }).start();

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = null;

        switch (mSize) {
            case SMALL:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_small,parent,false);
                break;
            case MEDIUM:
                break;
            case LARGE:
               itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_large,parent,false);
               break;
            default:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_large,parent,false);



        }

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final ProductItem prodItem = mFilteredList.get(position);
        holder.itemName.setText(prodItem.getName());
        holder.itemPrice.setText("€ " + new DecimalFormat("###.##").format(prodItem.calculatePrice()));
        if (prodItem.getDiscount() != 0f){
            holder.itemPrice.setTextColor(mContext.getResources().getColor(R.color.red));
            holder.prevItemPrice.setText("€ " + new DecimalFormat("###.##").format(prodItem.getPrice()));
            holder.prevItemPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
        holder.prodImg.setImageDrawable(prodItem.getImage());


        // click listeners

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("itemName", prodItem.getName());
                (NavHostFragment.findNavController(mContext)).navigate(R.id.itemFragment, bundle);
            }
        });

        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Open item fragment once item is pressed
                ((MainActivity)mContext.getActivity()).addToCart(mProductItems.get(position));
                // User feedback
                Toast.makeText(mContext.getActivity(), "Toegevoegd aan je winkelmandje!", Toast.LENGTH_SHORT).show();
              //  NavHostFragment.findNavController(mContext).navigate(R.id.shop_to_filter_on_view_more);
                }
            });

        holder.addToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO : actually add the product to the list
                Bundle bundle = new Bundle();
                bundle.putString("productName", prodItem.getName());
                (NavHostFragment.findNavController(mContext)).navigate(R.id.action_shopFragment_to_chooseListFragment, bundle);
//                Toast.makeText(mContext.getActivity(), "Toegevoegd aan je boodschappenlijstje!", Toast.LENGTH_SHORT).show();

            }
        });


        }
    @Override
    public int getItemCount() {
        return mFilteredList.size();

    }
    }



