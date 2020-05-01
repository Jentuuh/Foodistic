package com.example.foodify;

import android.content.ClipData;
import android.content.Context;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.foodify.Product.ProductItem;

import java.util.List;

public class ListAdapter extends ArrayAdapter<ProductItem> {
    private int resourceLayout;
    private Context mContext;
    private ShoppingCart mCart;

    public ListAdapter(@NonNull Context context, int resource, ShoppingCart cart) {
        super(context, resource, cart.getItems());
        resourceLayout = resource;
        mContext = context;
        mCart = cart;
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        View view = convertView;
        if(view == null){
            LayoutInflater inflater =LayoutInflater.from(mContext);
            view = inflater.inflate(resourceLayout, null);

        }
        ProductItem item = getItem(position);

        if (item != null){

            ImageView imgview = (ImageView) view.findViewById(R.id.item_img_view);
            TextView prodName = (TextView) view.findViewById(R.id.item_name_view);
            TextView prodPrice= (TextView) view.findViewById(R.id.item_price_view);


            if (imgview != null)
                imgview.setImageDrawable(item.getImage());
            if (prodName !=null)
                prodName.setText(item.getName());
            if (prodPrice != null)
                prodPrice.setText(String.valueOf(item.getPrice()) + " €");

            //add listener to delete a row when delete button is pressed
            view.findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCart.removeByPos(position) ;
                    //TODO ask for confirmation
                    notifyDataSetChanged() ;
                }
            });
        }

        return view;
    }

}
