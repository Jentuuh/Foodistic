package com.example.foodify;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.foodify.Product.Comment;
import com.example.foodify.Product.ProductItem;
import com.example.foodify.ShoppingList.ListCollectionFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Observable;
import java.util.Observer;

/**
 * Main activity in which the fragment will be replaced for each scene
 * @author Tim-Lukas Blom
 */
public class MainActivity extends AppCompatActivity implements Observer {
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private BottomNavigationView mBottomNav;
    private NavController mNavController;
    private ShoppingCart mShoppingCart;
    private Menu mMenu;
    private ListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // setting up navigation controller
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment);



        //Navigation bar setup
        mBottomNav = findViewById(R.id.bottom_nav);
        NavigationUI.setupWithNavController(mBottomNav, mNavController);

        //App bar setup

        //AppBarConfiguration appBarConfiguration =
          //      new AppBarConfiguration.Builder(navController.getGraph()).build();

        mDrawerLayout = findViewById(R.id.drawer_layout);

        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(R.id.shopFragment, R.id.pointFragment, R.id.listCollectionFragment, R.id.profileFragment).build();


        mToolbar = findViewById(R.id.toolbar);
        mToolbar.inflateMenu(R.menu.toolbarmenuitems);
        setSupportActionBar(mToolbar);


        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayShowHomeEnabled(false);
        NavigationUI.setupWithNavController(
                mToolbar, mNavController, appBarConfiguration);
        setupBasket();



        // Add destination listener
        mNavController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if(destination.getId() == R.id.profileFragment){
                    //mToolbar.setVisibility(View.GONE);
                    mBottomNav.setVisibility(View.GONE);
                }
                else{
                    mToolbar.setVisibility(View.VISIBLE);
                    mBottomNav.setVisibility(View.VISIBLE);
                }
            }
        });

        //TODO Put this in a different method
        Intent i = getIntent();
        int start_tab = i.getIntExtra("TabToStart", 1);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        switch (start_tab){
            case 1:
                mBottomNav.setSelectedItemId(R.id.shopFragment);
                break;
            case 2:
                mBottomNav.setSelectedItemId(R.id.pointFragment);
                break;
            case 3:
                mBottomNav.setSelectedItemId(R.id.listCollectionFragment);
                break;
            case 4:
                mBottomNav.setSelectedItemId(R.id.profileFragment);
                break;
        }
    }


    public void setupBasket(){
        mShoppingCart = new ShoppingCart();
        mShoppingCart.addObserver(this);

        ListView listview = (ListView) findViewById(R.id.basketitemlist);
        mAdapter = new ListAdapter(this, R.layout.basket_item, mShoppingCart);
        listview.setAdapter(mAdapter);


    }

    /**
     * When menu is created
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenu = menu;
        // Add toolbar
        getMenuInflater().inflate(R.menu.toolbarmenuitems, menu);
        createBasketTests();
        return true;
    }

    /**
     * temporary method that adds 18 carrots to shopping cart
     *
     */
    public void createBasketTests(){
        Drawable img = getResources().getDrawable(R.drawable.itemplaceholder);
        mShoppingCart.addItem(new ProductItem("Test_item",  1.5f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, img));
        mShoppingCart.addItem(new ProductItem("Test_item",  1.5f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, img));
        mShoppingCart.addItem(new ProductItem("Test_item",  1.5f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, img));
        mShoppingCart.addItem(new ProductItem("Test_item",  1.5f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, img));
        mShoppingCart.addItem(new ProductItem("Test_item",  1.5f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, img));
        mShoppingCart.addItem(new ProductItem("Test_item",  1.5f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, img));
        mShoppingCart.addItem(new ProductItem("Test_item",  1.5f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, img));
        mShoppingCart.addItem(new ProductItem("Test_item",  1.5f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, img));
        mShoppingCart.addItem(new ProductItem("Test_item",  1.5f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, img));
        mShoppingCart.addItem(new ProductItem("Test_item",  1.5f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, img));
        mShoppingCart.addItem(new ProductItem("Test_item",  1.5f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, img));
        mShoppingCart.addItem(new ProductItem("Test_item",  1.5f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, img));
        mShoppingCart.addItem(new ProductItem("Test_item",  1.5f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, img));
        mShoppingCart.addItem(new ProductItem("Test_item",  1.5f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, img));
        mShoppingCart.addItem(new ProductItem("Test_item",  1.5f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, img));
        mShoppingCart.addItem(new ProductItem("Test_item",  1.5f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, img));
        mShoppingCart.addItem(new ProductItem("Test_item",  1.5f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, img));
        mShoppingCart.addItem(new ProductItem("Test_item",  1.5f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, img));

    }


    /**
     * method that gets called when an item in the actionbar is pressed
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //Open drawer when shopping basket is tapper
            case R.id.shoppingBasket:
                mDrawerLayout.openDrawer(GravityCompat.END);
                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    /**
     * Set the count on top of the shopping basket
     * @param context
     * @param count count to be set
     */
    public void setCount(Context context, String count) {

        MenuItem menuItem = mMenu.findItem(R.id.shoppingBasket);
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

    /**
     * Update the total amount at the bottom of the shoppingcart
     */
    private void updateTotalBasket(){
        TextView total = (TextView) findViewById(R.id.total_view_value);
        total.setText("€ " + String.valueOf(mShoppingCart.getTotal()));
    }
    /**
     * Update count when item is added to shoppingbasket
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        setCount(this, String.valueOf(mShoppingCart.getCount()));
        updateTotalBasket();
        mAdapter.notifyDataSetChanged();
    }
}
