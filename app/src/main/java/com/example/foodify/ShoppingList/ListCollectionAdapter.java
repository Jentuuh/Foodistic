package com.example.foodify.ShoppingList;

import com.example.foodify.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * @author jentevandersanden
 * This class represents the adapter that parses a ShoppingList into the corresponding View
 * container needed for this ShoppingList
 */
public class ListCollectionAdapter extends ArrayAdapter<ShoppingList> {
    private int resourceLayout;
    private Context mContext;
    private List<ShoppingList> lists_to_display;

    public ListCollectionAdapter(@NonNull Context context, int resource, List<ShoppingList> lists_to_display) {
        super(context, resource);
        this.resourceLayout = resource;
        this.mContext = context;
        this.lists_to_display = lists_to_display;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(resourceLayout, null);

        }
        ShoppingList list_item = lists_to_display.get(position);


        if(list_item != null){

            TextView listName = (TextView) view.findViewById(R.id.list_name_view);
            ImageButton deleteButton = (ImageButton) view.findViewById(R.id.delete_button_view);
            ConstraintLayout listNameContainer = (ConstraintLayout) view.findViewById(R.id.list_container_view);

            if(listName != null){
                listName.setText(list_item.getName());
            }
            if(deleteButton != null){
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO : Delete ShoppingList from DB
                        // TODO : ask for confirmation
                    }
                });
            }
            if(listNameContainer != null){
                listNameContainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO : open corresponding list
                    }
                });
            }
        }

        return view;

    }
}
