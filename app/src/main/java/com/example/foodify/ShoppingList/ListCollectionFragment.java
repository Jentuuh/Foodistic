package com.example.foodify.ShoppingList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodify.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * @author jentevandersanden
 * This Fragment class represents the collection of Shopping lists a user owns.
 */
public class ListCollectionFragment extends Fragment {

    private ListView listcontainer;                     // ListView that contains all the shopping lists to be displayed
    private ArrayList<ShoppingList> lists_to_display;   // Shopping lists that needs to be displayed
    private ArrayList<String>   m_list_names;           // Names of the shopping lists that need to be displayed
    private ArrayAdapter<String> shop_list_adapter;     // ArrayAdapter that will get the names from the shopping lists and parse them into TextViews for the ListView.
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

        // Retrieve list container from layout
        listcontainer = (ListView) getView().findViewById(R.id.ShoppingListView);

        // Load the shopping lists from the database
        lists_to_display = new ArrayList<ShoppingList>();

        // Put the names of the lists in a new arraylist, for the arrayadapter
        m_list_names = new ArrayList<String>();
        for (ShoppingList list:lists_to_display) {
            m_list_names.add(list.getName());
        }


        // Create the arrayadapter and set it for the listcontainer
        shop_list_adapter = new ArrayAdapter<String>(getActivity(), R.layout.listitem, m_list_names);


        // Set on click listener for every item in the listview
        listcontainer.setAdapter(shop_list_adapter);

        listcontainer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openList(view);
            }
        });

        // Set on click listener for the FAB
        add_button = (FloatingActionButton) getView().findViewById(R.id.addButton);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNameListActivity();
            }
        });

        // Retrieve the shopping lists from database
        getShoppingLists();
    }


    @Override
    public void onStart() {
        super.onStart();
        getShoppingLists();
    }


    private void getShoppingLists(){
        // TODO: retrieve shopping lists from database
        // TEST
        lists_to_display.add(new ShoppingList("Testlijst"));
        m_list_names.add("Testlijst");
        shop_list_adapter.notifyDataSetChanged();
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


    /**
     * Removes a shopping list from the container
     * @param list_to_remove    : The List to be removed
     */
    public void removeShoppingList(ShoppingList list_to_remove){
        lists_to_display.remove(list_to_remove);
        m_list_names.remove(list_to_remove.getName());
        removeShoppingListDB(list_to_remove);
        shop_list_adapter.notifyDataSetChanged();
    }

    /**
     * Method that updates the database when a new Shopping list was removed.
     * @param list_to_remove    : The List to be removed
     */
    private void removeShoppingListDB(ShoppingList list_to_remove){
        // TODO: implement with DB
    }



}

