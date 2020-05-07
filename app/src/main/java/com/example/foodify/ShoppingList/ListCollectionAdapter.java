package com.example.foodify.ShoppingList;

import com.example.foodify.Database.AppDatabase;
import com.example.foodify.R;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.io.NotActiveException;
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
    private Fragment mFragment;

    public ListCollectionAdapter(@NonNull Context context, List<ShoppingList> lists_to_display, Fragment fragment) {
        super(context, 0, lists_to_display);
        this.mContext = context;
        this.lists_to_display = lists_to_display;
        mFragment = fragment;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.listitem, parent, false);

        }
        final ShoppingList list_item = lists_to_display.get(position);


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
                        // TODO : ask for confirmation
                        removeShoppingList(list_item);
                    }
                });
            }
            if(listNameContainer != null){
                listNameContainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("listname", list_item.getName());
                        bundle.putInt("listID", list_item.getM_id());
                        NavHostFragment.findNavController(mFragment).navigate(R.id.listCollection_to_listFragment, bundle);
                    }
                });
            }
        }

        return view;
    }



    /**
     * Removes a shopping list from the container
     * @param list_to_remove    : The List to be removed
     */
    public void removeShoppingList(ShoppingList list_to_remove){
        // Remove the list
        lists_to_display.remove(list_to_remove);
        removeShoppingListDB(list_to_remove);

        // Give the user feedback
        Toast.makeText(mContext,"Lijst '" + list_to_remove.getName() + "' werd verwijderd.", Toast.LENGTH_SHORT).show();
        this.notifyDataSetChanged();
    }

    /**
     * Method that updates the database when a new Shopping list was removed.
     * @param list_to_remove    : The List to be removed
     */
    private void removeShoppingListDB(ShoppingList list_to_remove){
        AppDatabase db = AppDatabase.getDatabase(getContext());
        db.m_foodisticDAO().deleteShoppingList(list_to_remove.getName());
    }
}

