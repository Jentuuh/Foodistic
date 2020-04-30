package com.example.foodify.ShoppingList;

import androidx.fragment.app.Fragment;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.foodify.R;

/**
 * @author jentevandersanden
 * This Fragment class represents the collection of Shopping lists a user owns.
 */
public class ListCollectionFragment extends Fragment {

    private ListView listcontainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_collection, container, false);

    }

    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);

        // Retrieve list container from layout
        listcontainer = (ListView) getView().findViewById(R.id.ShoppingListView);

        // TODO: Retrieve shopping lists from database;

    }


    @Override
    public void onStart() {
        super.onStart();
    }
}

