package com.example.foodify.ShoppingList;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.foodify.R;

/**
 * @author jentevandersanden
 * This simple activity will be presented to the user when they haven't made any shopping lists yet.
 * It also contains a button which allows them to create one.
 */
public class CreateShoppingList extends AppCompatActivity {

    private Button m_create_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        m_create_button = (Button) findViewById(R.id.createButton);
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
        Intent intent = new Intent(this, NameShoppingListActivity.class);
        startActivity(intent);
    }
}
