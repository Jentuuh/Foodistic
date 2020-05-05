package com.example.foodify;

import android.content.ClipData;
import android.content.Context;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.foodify.Product.ProductItem;
import com.example.foodify.ShoppingList.ShoppingList;

import java.util.List;

public class ListAdapter extends ArrayAdapter<ProductItem> {
    private int resourceLayout;
    private Context mContext;
    private ShoppingCart mCart;
    private ShoppingList mList;

    public ListAdapter(@NonNull Context context, int resource, ShoppingCart cart) {
        super(context, resource, cart.getItems());
        resourceLayout = resource;
        mContext = context;
        mCart = cart;
        mList = null;
    }

    public ListAdapter(@NonNull Context context, int resource, ShoppingList mList) {
        super(context, resource);
        this.resourceLayout = resource;
        this.mContext = context;
        this.mList = mList;
        mCart = null;
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
            TextView prodPrice = (TextView) view.findViewById(R.id.item_price_view);
            ImageButton deleteButton = (ImageButton) view.findViewById(R.id.delete_button);
            ConstraintLayout basketItemContainer = (ConstraintLayout) view.findViewById(R.id.basket_item_container);


            if (imgview != null)
                imgview.setImageDrawable(item.getImage());
            if (prodName !=null)
                prodName.setText(item.getName());
            if (prodPrice != null)
                prodPrice.setText("€ " + String.valueOf(item.getPrice()));


            //add listener to container of item in shopping cart to open the item fragment
            basketItemContainer.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //TODO Open item fragment once item is pressed
                    Log.v("ListAdapter", "Open the item page here");

                }
            });
            //add listener to delete a row when delete button is pressed
            deleteButton.setOnClickListener(new View.OnClickListener() {
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
        }

        return view;
    }

}
