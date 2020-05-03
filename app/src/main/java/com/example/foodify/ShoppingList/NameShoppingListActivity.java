package com.example.foodify.ShoppingList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;




import android.view.LayoutInflater;

import android.view.ViewGroup;

import com.example.foodify.LoginActivity;
import com.example.foodify.MainActivity;
import com.example.foodify.R;

import java.util.List;


/**
 * @author jentevandersanden
 * This Fragment class represents the fragment in which the use can name a shopping list before creating it.
 */
public class NameShoppingListActivity extends AppCompatActivity {
    public static final String EXTRA_LIST_NAME = "com.example.myfirstapp.LISTNAME";
    private Button m_confirm_button;
    private EditText m_name_field;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_shopping_list);

        m_confirm_button = (Button) findViewById(R.id.confirmButton);
        m_name_field = (EditText) findViewById(R.id.nameField);

        m_confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onConfirmClick();
            }
        });

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
    }


    /**
     * This callback method handles what needs to happen when the user clicks "confirm". This will
     * create a new empty shopping list with a certain name.
     */
    private void onConfirmClick(){
        if(nameDoesntAlreadyExist()) {
            createShoppingList(m_name_field.getText().toString());
            showShoppingListActivity(m_name_field.getText().toString());
        }
    }

    /**
     * Method that creates a new shopping list and inserts it into the database.
     * @param name : The name of the shopping list
     */
    private void createShoppingList(String name){
        // TODO: implement with database that a new shopping list with a certain name is created

    }

    /**
     * Method that checks whether a shopping list with the current chosen name already exists.
     * @return boolean
     */
    private boolean nameDoesntAlreadyExist(){
        //TODO: ask database if this name already exists
        if(true){ //TODO
            return true;
        }
        else {
            // Give the user feedback that this name already exists.
            Toast.makeText(this, "You already have a shopping list with this name. Choose another name.", Toast.LENGTH_SHORT);
            return false;
        }
    }

    /**
     * This method switches to the shopping list activity after a shopping list was succesfully made.
     */
    private void showShoppingListActivity(String name) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("TabToStart", 3);
        // Update database with the created shoppinglist
//        ListCollectionFragment listcollection = new ListCollectionFragment();
//        ShoppingList to_add = new ShoppingList(name);
//        listcollection.addShoppingList(to_add);

        // Go back to the MainActivity
        startActivity(intent);


    }

}

