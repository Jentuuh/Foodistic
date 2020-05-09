package com.example.foodify;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


import com.example.foodify.Database.AppDatabase;
import com.example.foodify.Database.DatabasePopulator;
import com.example.foodify.Database.Entities.ProductEntity;
import com.example.foodify.Database.Entities.ProductInShopEntity;
import com.example.foodify.Login.LoginActivity;
import com.example.foodify.Login.SaveSharedPreference;
import com.example.foodify.Product.ProductItem;
import com.example.foodify.ShoppingCart.ShoppingCart;
import com.example.foodify.ShoppingCart.ShoppingCartAdapter;
import com.example.foodify.ShoppingCart.ShoppingCartCount;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;
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
    private ShoppingCartAdapter mShoppingCartAdapter;
    private AppBarConfiguration mAppBarConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupBottomNav();
        mAppBarConfig = new AppBarConfiguration.Builder(R.id.shopFragment, R.id.pointFragment, R.id.listCollectionFragment, R.id.profileFragment).build();
        setupToolbar();
        setupBasket();
        NavigationUI.setupWithNavController(mToolbar, mNavController, mAppBarConfig);
        setupDestinationListeners();

        // INSERT ALL SAMPLE DATA
        insertTestData();
    }

    /**
     * Creates Toolbar
     */
    private void setupToolbar(){

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.inflateMenu(R.menu.toolbarmenuitems);
        setSupportActionBar(mToolbar);
        // Setup Shopping cart drawer
        mDrawerLayout = findViewById(R.id.drawer_layout);

    }

    /**
     * Creates bottom navigation bar
     */
    private void setupBottomNav(){
        // setting up navigation controller
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment);
        // Navigation bar setup
        mBottomNav = findViewById(R.id.bottom_nav);
        NavigationUI.setupWithNavController(mBottomNav, mNavController);

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

    private void setupDestinationListeners(){

        //TODO see if we wanna use this

        // Add destination listener
        mNavController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if(destination.getId() == R.id.profileFragment){

                    //mToolbar.setVisibility(View.GONE);
                    //mBottomNav.setVisibility(View.GONE);
                }
                else{
                    mToolbar.setVisibility(View.VISIBLE);
                    mBottomNav.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void setupBasket(){
        mShoppingCart = new ShoppingCart();
        mShoppingCart.addObserver(this);

        ListView listview = (ListView) findViewById(R.id.basketitemlist);
        mShoppingCartAdapter = new ShoppingCartAdapter(this, R.layout.basket_item, mShoppingCart);
        listview.setAdapter(mShoppingCartAdapter);

    }

    public void addToCart(ProductItem item){
        mShoppingCart.addItem(item);
        mShoppingCartAdapter.notifyDataSetChanged();

    }

    public void navigateTo(int actionId, Bundle bundle){
        if (mDrawerLayout.isDrawerOpen(GravityCompat.END)){ // If you tap on an item but the shopping cart is open, close it
            mDrawerLayout.closeDrawer(GravityCompat.END);
        }
        mNavController.navigate(actionId);

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
        menu.findItem(R.id.search_icon).setVisible(false);
        //createBasketTests();
       // updateTotalBasket();
        setCount(this, mShoppingCart.getCount());

        return true;
    }

    /**
     * temporary method that adds 18 carrots to shopping cart
     *
     */
    public void createBasketTests(){
        //TODO remove this when everything works
        Drawable img = getResources().getDrawable(R.drawable.itemplaceholder);
        mShoppingCart.addItem(new ProductItem("Test_item",  1.5f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null,0.3f, img));
        mShoppingCart.addItem(new ProductItem("Test_item",  1.5f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null,0.3f, img));
        mShoppingCart.addItem(new ProductItem("Test_item",  1.5f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null,0.3f ,img));
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
    public void setCount(Context context, int count) {
        String strCount = String.valueOf(count);
        MenuItem menuItem = mMenu.findItem(R.id.shoppingBasket);
        LayerDrawable icon = (LayerDrawable) menuItem.getIcon();

        ShoppingCartCount badge;

        // Reuse drawable if possible
        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_item_count);
        if (reuse != null && reuse instanceof ShoppingCartCount) {
            badge = (ShoppingCartCount) reuse;
        }
        else {
            badge = new ShoppingCartCount(context);
        }

        badge.setCount(strCount);
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_item_count, badge);

    }

    /**
     * Update the total amount at the bottom of the shoppingcart
     */
    private void updateTotalBasket(){
        TextView total = (TextView) findViewById(R.id.total_view_value);
        total.setText("€ " + new DecimalFormat("###.##").format(mShoppingCart.getTotal()));
    }


    /**
     * Update count when item is added to shoppingbasket
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        setCount(this, mShoppingCart.getCount());
        updateTotalBasket();
        mShoppingCartAdapter.notifyDataSetChanged();
    }

    /**
     * Method that will supply all the test data for the application, using the DatabasePopulator
     */
    private void insertTestData(){

        // TEST PRODUCT
        ProductEntity new_product = new ProductEntity();
        new_product.setName("Test_item");
        new_product.setPrice(1.5555555f);
        new_product.setDiscount(0.2222f);
        new_product.setLikability(20.3f);
        new_product.setDescription("testtesttesttest");
        DatabasePopulator.addProduct(AppDatabase.getDatabase(getApplicationContext()),new_product);

        ProductInShopEntity product_in_shop = new ProductInShopEntity();
        product_in_shop.setProductname("Test_item");
        product_in_shop.setShopname("Colruyt");
        product_in_shop.setPrice(1.56f);
        DatabasePopulator.addProductInShop(AppDatabase.getDatabase(getApplicationContext()), product_in_shop);

        ProductInShopEntity product_in_shop1 = new ProductInShopEntity();
        product_in_shop1.setProductname("Test_item");
        product_in_shop1.setShopname("Delhaize");
        product_in_shop1.setPrice(1.77f);
        DatabasePopulator.addProductInShop(AppDatabase.getDatabase(getApplicationContext()), product_in_shop1);


    }
}
