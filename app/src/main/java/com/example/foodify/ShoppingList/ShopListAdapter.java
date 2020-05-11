package com.example.foodify.ShoppingList;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.example.foodify.Database.AppDatabase;
import com.example.foodify.Product.ProductItem;
import com.example.foodify.R;
import com.example.foodify.ShoppingCart.ShoppingCart;
import com.example.foodify.ShoppingCart.ShoppingCartItem;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * @author jentevandersanden
 * This class represents the adapter that parses a List Item into the corresponding
 * View container needed for this item
 */
public class ShopListAdapter extends ArrayAdapter<ShoppingCartItem> {
        private int resourceLayout;
        private Context mContext;
        private ShoppingList mList;
        private ArrayList<ShoppingCartItem> mFilteredList;
        private final ShopListAdapter selfref = this;
        private ListFragment m_fragment;

        public ShopListAdapter(@NonNull Context context, int resource, ShoppingList list, ListFragment fragm) {
            super(context, resource, list.getProducts());
            resourceLayout = resource;
            mContext = context;
            mList = list;
            m_fragment = fragm;
            mFilteredList = new ArrayList<ShoppingCartItem>();
            fillFilteredList();
        }

    public void fillFilteredList(){
            mFilteredList.clear();
            mFilteredList.addAll(mList.getList());
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
                    mFilteredList.addAll(mList.getList());
                }
                else {

                    // Iterate in the original List and add it to filter list...
                    for (ShoppingCartItem item : mList.getList()) {
                        if (item.getItem().getName().toLowerCase().contains(text.toLowerCase()) ) {
                            // Adding Matched items
                            mFilteredList.add(item);
                        }
                    }
                }

                // Set on UI Thread
                (m_fragment).getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Notify the List that the DataSet has changed...
                        notifyDataSetChanged();
                    }
                });

            }
        }).start();

    }

    @Override
    public int getCount() {
            Log.v("listcount", ""+mFilteredList.size());
            super.getCount();
            return mFilteredList.size();

    }

    public View getView(final int position, View convertView, ViewGroup parent){
            View view = convertView;
            if(view == null){
                LayoutInflater inflater = LayoutInflater.from(mContext);
                view = inflater.inflate(resourceLayout, null);

            }
            ShoppingCartItem cartItem = mFilteredList.get(position);
            final ProductItem item = cartItem.getItem();

            if (item != null){
                CheckBox checkbox = (CheckBox) view.findViewById(R.id.checkItem);
                ImageView imgview = (ImageView) view.findViewById(R.id.item_img_view);
                TextView prodName = (TextView) view.findViewById(R.id.item_name_view);
                TextView prodPrice = (TextView) view.findViewById(R.id.item_price_view);
                // ImageButton deleteButton = (ImageButton) view.findViewById(R.id.delete_button);
                TextView prodQuantity = (TextView) view.findViewById(R.id.prod_quantity);
                ImageView plus = (ImageView) view.findViewById(R.id.imgPlus);
                ImageView minus = (ImageView) view.findViewById(R.id.imgMinus);
                CardView basketItemContainer = (CardView) view.findViewById(R.id.list_item_container);

                if (imgview != null)
                    imgview.setImageDrawable(item.getImage());
                if (prodName !=null)
                    prodName.setText(item.getName());
                if (prodPrice != null)
                    prodPrice.setText("€ " + new DecimalFormat("###.##").format(item.calculatePrice()));
                if (prodQuantity != null)
                    prodQuantity.setText(Integer.toString(cartItem.getQuantity()));


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
                            mList.addByName(mFilteredList.get(position).getItem().getName(), mContext);
                            notifyDataSetChanged();
                            m_fragment.updatePrice();
                        }
                    });
                }
                if (minus != null){
                    minus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            mList.removeByName(mFilteredList.get(position).getItem().getName() , mContext, selfref);
                            notifyDataSetChanged();
                            updatePriceAndMessage();

                        }
                    });
                }

                if(checkbox != null){
                    checkbox.setChecked(cartItem.isChecked());
                    checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            AppDatabase db = AppDatabase.getDatabase(getContext());

                            if(isChecked){
                                // Save that the item is checked
                                db.m_foodisticDAO().updateProductChecked(true, mList.getName(), item.getName());
                            }
                            else{
                                // Save that the item is unchecked
                                db.m_foodisticDAO().updateProductChecked(false, mList.getName(), item.getName());
                            }
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

        public void removeFromFilter(String name){
            ArrayList<ShoppingCartItem> removeItems = new ArrayList<ShoppingCartItem>();
            for (ShoppingCartItem item:mFilteredList){
                if (item.getItem().getName().equals(name)){
                    removeItems.add(item);
                }
            }
            mFilteredList.removeAll(removeItems);

        }

    public void updatePriceAndMessage(){
        m_fragment.updatePrice();
        m_fragment.updateEmptyMessage();
    }
}
