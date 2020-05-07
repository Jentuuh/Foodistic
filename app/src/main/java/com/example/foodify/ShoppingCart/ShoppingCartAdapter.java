package com.example.foodify.ShoppingCart;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.foodify.MainActivity;
import com.example.foodify.Product.ProductItem;
import com.example.foodify.R;

import java.text.DecimalFormat;

public class ShoppingCartAdapter extends ArrayAdapter<ShoppingCartItem> {
    private int resourceLayout;
    private Context mContext;
    private ShoppingCart mCart;


    public ShoppingCartAdapter(@NonNull Context context, int resource, ShoppingCart cart) {
        super(context, resource, cart.getItems());
        resourceLayout = resource;
        mContext = context;
        mCart = cart;
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
            CardView basketItemContainer = (CardView) view.findViewById(R.id.basket_item_container);


            if (imgview != null)
                imgview.setImageDrawable(item.getImage());
            if (prodName !=null)
                prodName.setText(item.getName());
            if (prodPrice != null)
                prodPrice.setText("€ " + new DecimalFormat("###.##").format(item.calculatePrice()));
            if (prodQuantity != null)
                prodQuantity.setText(String.valueOf(cartItem.getQuantity()));


            //add listener to container of item in shopping cart to open the item fragment
            if (basketItemContainer != null) {
                basketItemContainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO Open item fragment once item is pressed
                        ((MainActivity)mContext).navigateTo(R.id.shopFilterFragment);

                        Log.v("ListAdapter", "Open the item page here");

                    }
                });
            }
            if (plus != null){
                plus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mCart.addByPos(position);
                        notifyDataSetChanged();
                    }
                });
            }
            if (minus != null){
                minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //ask for confirmation if it is the last item
                        if (mCart.getProductAtIndex(position).getQuantity() <= 1){
                                new AlertDialog.Builder(getContext())
                                        .setTitle("Verwijder product")
                                        .setMessage("Weet je zeker dat je dit wilt doen?")

                                        // Specifying a listener allows you to take an action before dismissing the dialog.
                                        // The dialog is automatically dismissed when a dialog button is clicked.
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // Continue with delete operation
                                                mCart.removeByPos(position);
                                            }
                                        })

                                        // A null listener allows the button to dismiss the dialog and take no further action.
                                        .setNegativeButton(android.R.string.no, null)
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();


                            }
                        else{
                            mCart.removeByPos(position);
                        }

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
    private String calculatePrice(float originalPrice, float discount){

        return "€ " + new DecimalFormat("###.##").format(originalPrice - (originalPrice * discount));
    }

}
