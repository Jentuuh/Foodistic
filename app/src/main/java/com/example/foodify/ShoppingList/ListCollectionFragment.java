package com.example.foodify.ShoppingList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodify.Database.AppDatabase;
import com.example.foodify.Database.Entities.ShoppingListEntity;
import com.example.foodify.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jentevandersanden
 * This Fragment class represents the collection of Shopping lists a user owns.
 */
public class ListCollectionFragment extends Fragment {

    private ListView listcontainer;                     // ListView that contains all the shopping lists to be displayed
    private List<ShoppingList> lists_to_display;   // Shopping lists that needs to be displayed
    private ListCollectionAdapter shop_list_adapter;    // ArrayAdapter that will get the names from the shopping lists and parse them into TextViews for the ListView.
    private FloatingActionButton add_button;            // FAB

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_collection, container, false);

    }

    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);

        listcontainer = (ListView) getView().findViewById(R.id.ShoppingListView);

        // Load the shopping lists from the database
        lists_to_display = new ArrayList<ShoppingList>();

        getShoppingLists();
        // Create the arrayadapter and set it for the listcontainer
        shop_list_adapter = new ListCollectionAdapter(getActivity(), R.layout.listitem, lists_to_display);


        // Set adapter for the listview
        listcontainer.setAdapter(shop_list_adapter);


        // Set on click listener for the FAB
        add_button = (FloatingActionButton) getView().findViewById(R.id.addButton);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNameListActivity();
            }
        });

    }


    @Override
    public void onStart() {        // Retrieve list container from layout
        super.onStart();
        getShoppingLists();
        shop_list_adapter.notifyDataSetChanged();
        Log.v ("Test", "Listcollectionfragment started");
    }



    public void showNameListActivity(){
        //Intent intent = new Intent(getActivity(), NameShoppingListActivity.class);
        //startActivity(intent);
        //TODO Eventueel NameListActivity veranderen naar een FRAGMENT
        NavHostFragment.findNavController(this).navigate(R.id.action_listCollectionFragment_to_nameShoppingListActivity);

    }


    /**
     * Method that's called when the user clicks on a list in the listcontainer.
     * @param view  : The view from the list that was clicked on
     */
    public void openList(View view){

        // Get the view object that you clicked on
        View selected = getView().findViewById(view.getId());
        // Retrieve the textual content of this object
        String listname = ((TextView) selected).getText().toString();

        //TODO: Switch fragment to ListFragment
        loadListFragment(listname);
    }


    /**
     * Changes the displayed fragment to the corresponding list fragment
     * @param list_name : The name of the shopping list that we want to display a fragment for.
     */
    private void loadListFragment(String list_name){
        Bundle bundle = new Bundle();
        bundle.putString("listname", list_name);
        NavHostFragment.findNavController(this).navigate(R.id.listCollection_to_listFragment, bundle);
    }



    /**
     * ///////////////////////
     * DATA + DATABASE METHODS
     * ///////////////////////
     */



    private void getShoppingLists(){
        // TODO : DON'T FORGET TO SETOBSERVER FOR SHOPPINGLIST

        // Retrieve the shopping lists from the database
        AppDatabase db = AppDatabase.getDatabase(getActivity());
        List<ShoppingListEntity>  lists_from_db = db.m_foodisticDAO().getAllShoppingLists();
        // Parse the database data to actual objects that we can use in our code
        parseFromDBToObjects(lists_from_db);

        // TEST DATA
        lists_to_display.add(new ShoppingList("Testlijst"));
    }

    /**
     * Method that parses a list of ShoppingListEntity objects retrieved from a db into actual ShoppingList objects.
     * @param db_lists : the list retrieved from the db
     */
    private void parseFromDBToObjects(List<ShoppingListEntity> db_lists){
        for(ShoppingListEntity list : db_lists){
            lists_to_display.add(new ShoppingList(list.getName()));
        }
    }

    /**
     * Removes a shopping list from the container
     * @param list_to_remove    : The List to be removed
     */
    public void removeShoppingList(ShoppingList list_to_remove){
        lists_to_display.remove(list_to_remove);
        removeShoppingListDB(list_to_remove);
        shop_list_adapter.notifyDataSetChanged();
    }

    /**
     * Method that updates the database when a new Shopping list was removed.
     * @param list_to_remove    : The List to be removed
     */
    private void removeShoppingListDB(ShoppingList list_to_remove){
        AppDatabase db = AppDatabase.getDatabase(getActivity());
        db.m_foodisticDAO().deleteShoppingList(list_to_remove.getName());
    }



}

