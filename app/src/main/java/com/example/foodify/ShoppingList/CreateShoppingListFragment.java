package com.example.foodify.ShoppingList;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.foodify.Database.AppDatabase;
import com.example.foodify.R;
import com.example.foodify.ShoppingCart.ShoppingCartItem;

/**
 * @author jentevandersanden
 * This simple activity will be presented to the user when they haven't made any shopping lists yet.
 * It also contains a button which allows them to create one.
 */
public class CreateShoppingListFragment extends Fragment {

    private Button m_create_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_list, container, false);

    }




    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);


    }


    @Override
    public void onStart() {
        super.onStart();

        m_create_button = (Button) getActivity().findViewById(R.id.createButton);
        m_create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateClick();
            }
        });
    }

    /**
     * This callback method handles what needs to happen when the user clicks the "Create shopping list" button.
     * This will open a new activity in which the user will be asked to name the new shopping list.
     */
    private void onCreateClick(){
        NavHostFragment.findNavController(this).navigate(R.id.action_createShoppingList_to_nameShoppingListActivity);
    }
}
