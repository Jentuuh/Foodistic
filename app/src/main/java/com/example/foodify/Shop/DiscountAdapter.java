package com.example.foodify.Shop;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodify.Product.ProductItem;
import com.example.foodify.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DiscountAdapter extends RecyclerView.Adapter<DiscountAdapter.MyViewHolder> {
    private ArrayList<ProductItem> mDiscountList;
    private Fragment mContext;
    public DiscountAdapter(ArrayList<ProductItem> discountlist, Fragment context){
        mDiscountList = discountlist;
        mContext = context;

    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView itemName;
        TextView itemPrice;
        TextView prevItemPrice;
        ImageView prodImg;
        ImageButton addToCart;
        ImageButton addToList;

         ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.product_name);
            itemPrice = itemView.findViewById(R.id.prod_price);
            prevItemPrice = itemView.findViewById(R.id.prev_prod_price);
            prodImg = itemView.findViewById(R.id.product_img);
            addToCart = itemView.findViewById(R.id.add_to_cart_button);
            addToList = itemView.findViewById(R.id.add_to_list_button);


        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_small,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ProductItem prodItem = mDiscountList.get(position);
        holder.itemName.setText(prodItem.getName());
        holder.itemPrice.setText("€ " + new DecimalFormat("###.##").format(prodItem.calculatePrice()));
        holder.prevItemPrice.setText("€ " + new DecimalFormat("###.##").format(prodItem.getPrice()));
        holder.prevItemPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.prodImg.setImageDrawable(prodItem.getImage());


        // click listeners


        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Open item fragment once item is pressed
                NavHostFragment.findNavController(mContext).navigate(R.id.shop_to_filter_on_view_more);
                }
            });

        holder.addToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Open item fragment once item is pressed
                Log.v("ListAdapter", "Open the item page here");

            }
        });


        }



    @Override
    public int getItemCount() {
        return mDiscountList.size();
    }



}
