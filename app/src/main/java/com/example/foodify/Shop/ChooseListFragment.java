package com.example.foodify.Shop;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.foodify.Database.AppDatabase;
import com.example.foodify.Database.Entities.ProductOnListEntity;
import com.example.foodify.Database.Entities.ShoppingListEntity;
import com.example.foodify.R;

import java.util.ArrayList;
import java.util.List;

public class ChooseListFragment extends Fragment {

    private ArrayList<String> m_list_names;
    private ArrayAdapter<String> m_adapter;
    private ListView m_list_of_lists;
    private final ChooseListFragment m_self_ref = this;



    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);

        m_list_names = new ArrayList<>();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_list, container, false);

    }

    @Override
    public void onStart() {        // Retrieve list container from layout
        super.onStart();

        m_list_of_lists = (ListView) getActivity().findViewById(R.id.shoplists);
        m_adapter = new ArrayAdapter<String>(getContext(), R.layout.chooselistitem, m_list_names);
        m_list_of_lists.setAdapter(m_adapter);
        getShoppingLists();


        // If the user has no lists yet, let him/her create one
        if(m_list_names.isEmpty()){
            NavHostFragment.findNavController(this).navigate(R.id.action_chooseListFragment_to_createShoppingList);
        }

        m_list_of_lists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // TODO: if this item is already on this list, increase the quantity
                AppDatabase db = AppDatabase.getDatabase(getContext());
                // Create a new ProductOnListEntity and add it to a list
               String listname = ((TextView)view).getText().toString();
                ProductOnListEntity to_add = new ProductOnListEntity();
                to_add.setProductname(getArguments().getString("productName"));
                to_add.setQuantity(1);
                to_add.setChecked(false);
                to_add.setListname(listname);
                db.m_foodisticDAO().addProductToList(to_add);
                NavHostFragment.findNavController(m_self_ref).navigate(R.id.action_chooseListFragment_to_shopFragment);
                Toast.makeText(getContext(), "Product toegevoegd aan " + listname + "!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getShoppingLists(){
        m_list_names.clear();
        // Retrieve the shopping lists from the database
        AppDatabase db = AppDatabase.getDatabase(getActivity());
        List<ShoppingListEntity> lists_from_db = db.m_foodisticDAO().getAllShoppingLists();
        // Parse the database data to actual objects that we can use in our code
        for(ShoppingListEntity list : lists_from_db){
            m_list_names.add(list.getName());
        }
        m_adapter.notifyDataSetChanged();
    }
}
