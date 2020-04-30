package com.example.foodify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Main activity in which the fragment will be replaced for each scene
 * @author Tim-Lukas Blom
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setting up navigation controller
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        NavigationUI.setupWithNavController(bottomNav, navController);

        //App bar setup

        //AppBarConfiguration appBarConfiguration =
          //      new AppBarConfiguration.Builder(navController.getGraph()).build();

        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(R.id.shopFragment, R.id.pointFragment, R.id.listFragment, R.id.profileFragment).build();
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbarmenuitems);
        setSupportActionBar(toolbar);
        NavigationUI.setupWithNavController(
                toolbar, navController, appBarConfiguration);



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbarmenuitems, menu);
        setCount(this, "10", menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.shoppingBasket:
                // User chose the "Settings" item, show the app settings UI...
                //TODO open shoppingbasket when tapped
                String toastText = "Open Shopping basket";
                Toast toast = Toast.makeText(this, toastText, Toast.LENGTH_SHORT);
                toast.show();
                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
    public void setCount(Context context, String count, Menu menu) {

        MenuItem menuItem = menu.findItem(R.id.shoppingBasket);
        LayerDrawable icon = (LayerDrawable) menuItem.getIcon();

        BasketCount badge;

        // Reuse drawable if possible
        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_item_count);
        if (reuse != null && reuse instanceof BasketCount) {
            badge = (BasketCount) reuse;
        } else {
            badge = new BasketCount(context);
        }

        badge.setCount(count);
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_item_count, badge);
    }

}
