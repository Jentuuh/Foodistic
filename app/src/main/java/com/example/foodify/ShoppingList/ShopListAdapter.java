package com.example.foodify.ShoppingList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.foodify.Product.ProductItem;
import com.example.foodify.R;
import com.example.foodify.ShoppingCart.ShoppingCartItem;

/**
 * @author jentevandersanden
 * This class represents the adapter that parses a List Item into the corresponding
 * View container needed for this item
 */
public class ShopListAdapter extends ArrayAdapter<ShoppingCartItem> {
        private int resourceLayout;
        private Context mContext;
        private ShoppingList mList;

        public ShopListAdapter(@NonNull Context context, int resource, ShoppingList list) {
            super(context, resource, list.getProducts());
            resourceLayout = resource;
            mContext = context;
            mList = list;
        }


        public View getView(final int position, View convertView, ViewGroup parent){
            View view = convertView;
            if(view == null){
                LayoutInflater inflater = LayoutInflater.from(mContext);
                view = inflater.inflate(resourceLayout, null);

            }
            ShoppingCartItem cartItem = getItem(position);
            ProductItem item = cartItem.getItem();

            if (item != null){

                ImageView imgview = (ImageView) view.findViewById(R.id.item_img_view);
                TextView prodName = (TextView) view.findViewById(R.id.item_name_view);
                TextView prodPrice = (TextView) view.findViewById(R.id.item_price_view);
                // ImageButton deleteButton = (ImageButton) view.findViewById(R.id.delete_button);
                TextView prodQuantity = (TextView) view.findViewById(R.id.prod_quantity);
                ImageView plus = (ImageView) view.findViewById(R.id.imgPlus);
                ImageView minus = (ImageView) view.findViewById(R.id.imgMinus);
                ConstraintLayout basketItemContainer = (ConstraintLayout) view.findViewById(R.id.basket_item_container);


                if (imgview != null)
                    imgview.setImageDrawable(item.getImage());
                if (prodName !=null)
                    prodName.setText(item.getName());
                if (prodPrice != null)
                    prodPrice.setText("€ " + String.valueOf(item.getPrice()));
                if (prodQuantity != null)
                    prodQuantity.setText(String.valueOf(cartItem.getQuantity()));


                //add listener to container of item in shopping cart to open the item fragment
                if (basketItemContainer != null) {
                    basketItemContainer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //TODO Open item fragment once item is pressed
                            Log.v("ListAdapter", "Open the item page here");

                        }
                    });
                }
                if (plus != null){
                    plus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mList.addByPos(position);
                            notifyDataSetChanged();
                        }
                    });
                }
                if (minus != null){
                    minus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mList.removeByPos(position);
                            notifyDataSetChanged();
                        }
                    });
                }
                //add listener to delete a row when delete button is pressed
         /*   deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mList == null){
                        mCart.removeByPos(position);
                        //TODO ask for confirmation
                        notifyDataSetChanged();
                    }
                    else{
                        mList.removeByPosition(position);
                        // TODO ask for confirmation
                        notifyDataSetChanged();
                    }
                }
            });

          */
            }

            return view;
        }

}
