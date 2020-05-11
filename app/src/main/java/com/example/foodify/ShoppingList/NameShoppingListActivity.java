package com.example.foodify.ShoppingList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.foodify.Database.AppDatabase;
import com.example.foodify.Database.Entities.ShoppingListEntity;
import com.example.foodify.MainActivity;
import com.example.foodify.R;

import java.util.List;


/**
 * @author jentevandersanden
 * This Fragment class represents the fragment in which the use can name a shopping list before creating it.
 */
public class NameShoppingListActivity extends Fragment {
    public static final String EXTRA_LIST_NAME = "com.example.myfirstapp.LISTNAME";
    private Button m_confirm_button;
    private EditText m_name_field;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




/*
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("TabToStart", 3);
                startActivity(intent);
            }
        });
        */
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_name_shopping_list, container, false);

        m_confirm_button = (Button) rootView.findViewById(R.id.confirmButton);
        m_name_field = (EditText) rootView.findViewById(R.id.nameField);

        m_confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onConfirmClick();
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    }

    /**
     * This callback method handles what needs to happen when the user clicks "confirm". This will
     * create a new empty shopping list with a certain name.
     */
    private void onConfirmClick(){
        if(nameDoesntAlreadyExist(m_name_field.getText().toString())) {
            if(m_name_field.getText().toString().matches("")){
                Toast.makeText(getActivity(), "Vul een naam in.", Toast.LENGTH_SHORT).show();
            }
            else {
                createShoppingList(m_name_field.getText().toString());
                showShoppingListActivity(m_name_field.getText().toString());
            }
        }
        else {
            Toast.makeText(getActivity(), "Je hebt reeds een lijstje met deze naam. Gelieve een andere naam te kiezen.", Toast.LENGTH_SHORT).show();
        }
    }



    /**
     * ////////////////
     * DATABASE METHODS
     * ////////////////
     */


    /**
     * Method that creates a new shopping list and inserts it into the database.
     * @param name : The name of the shopping list
     */
    private void createShoppingList(String name){
        // Retrieve db instance
        AppDatabase db = AppDatabase.getDatabase(getActivity());
        ShoppingListEntity to_add = new ShoppingListEntity();

        // Set name and ID
        to_add.setName(name);

        // Add entity to db
        db.m_foodisticDAO().createShoppingList(to_add);
    }

    /**
     * Method that checks whether a shopping list with the current chosen name already exists.
     * @return boolean
     */
    private boolean nameDoesntAlreadyExist(String name){

        AppDatabase db = AppDatabase.getDatabase(getActivity());
        List<ShoppingListEntity> lists = db.m_foodisticDAO().getAllShoppingLists();

        for(ShoppingListEntity list : lists){
            if(list.getName().matches(name)){
                return false;
            }
        }
        return true;
    }

    /**
     * This method switches to the shopping list activity after a shopping list was succesfully made.
     */
    private void showShoppingListActivity(String name) {
      //  Intent intent = new Intent(this, MainActivity.class);
     //   intent.putExtra("TabToStart", 3);
        // Update database with the created shoppinglist
//        ListCollectionFragment listcollection = new ListCollectionFragment();
//        ShoppingList to_add = new ShoppingList(name);
//        listcollection.addShoppingList(to_add);

        // Go back to the MainActivity
    //    startActivity(intent);
     //   Toast.makeText(getApplicationContext(), "Lijst '" + name + "' werd aangemaakt!", Toast.LENGTH_SHORT).show();


    }

}

