package com.example.foodify.ShoppingList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodify.R;




/**
 * @author jentevandersanden
 * This class represents the activity in which the use can name a shopping list before creating it.
 */
public class NameShoppingList extends AppCompatActivity {
    public static final String EXTRA_LIST_NAME = "com.example.myfirstapp.LISTNAME";
    private Button m_confirm_button;
    private EditText m_name_field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
    private void showShoppingListActivity(String name){
        Intent intent = new Intent(this, ShoppingList.class);
        // Pass the name of the list just made as an extra
        intent.putExtra(EXTRA_LIST_NAME, name);
        startActivity(intent);
    }

}
