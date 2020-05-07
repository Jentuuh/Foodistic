package com.example.foodify.Shop;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodify.Database.AppDatabase;
import com.example.foodify.Database.Entities.ProductEntity;
import com.example.foodify.Database.Entities.ProductOnListEntity;
import com.example.foodify.Enums.Size;
import com.example.foodify.MainActivity;
import com.example.foodify.Product.ProductItem;
import com.example.foodify.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    private ArrayList<ProductItem> mDiscountList;
    private Fragment mContext;
    private Size mSize;
    public ProductAdapter(ArrayList<ProductItem> discountlist, Fragment context, Size size){
        mDiscountList = discountlist;
        mContext = context;
        mSize = size;

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

            switch (mSize) {
                case SMALL:
                    itemName = itemView.findViewById(R.id.product_name);
                    itemPrice = itemView.findViewById(R.id.prod_price);
                    prevItemPrice = itemView.findViewById(R.id.prev_prod_price);
                    prodImg = itemView.findViewById(R.id.product_img);
                    addToCart = itemView.findViewById(R.id.add_to_cart_button);
                    addToList = itemView.findViewById(R.id.add_to_list_button);
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
                    break;
                default:
                    itemName = itemView.findViewById(R.id.product_name);
                    itemPrice = itemView.findViewById(R.id.prod_price);
                    prevItemPrice = itemView.findViewById(R.id.prev_prod_price);
                    prodImg = itemView.findViewById(R.id.product_img);
                    addToCart = itemView.findViewById(R.id.add_to_cart_button);
                    addToList = itemView.findViewById(R.id.add_to_list_button);


            }
        }
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
        final ProductItem prodItem = mDiscountList.get(position);
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
                ((MainActivity)mContext.getActivity()).addToCart(mDiscountList.get(position));
                // User feedback
                Toast.makeText(mContext.getActivity(), "Toegevoegd aan je winkelmandje!", Toast.LENGTH_SHORT).show();
              //  NavHostFragment.findNavController(mContext).navigate(R.id.shop_to_filter_on_view_more);
                }
            });

        holder.addToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO : actually add the product to the list
                Toast.makeText(mContext.getActivity(), "Toegevoegd aan je boodschappenlijstje!", Toast.LENGTH_SHORT).show();


            }
        });


        }



    @Override
    public int getItemCount() {
        return mDiscountList.size();
    }



}
