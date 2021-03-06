package com.example.foodify;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
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
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.foodify.Database.AppDatabase;
import com.example.foodify.Database.DatabasePopulator;
import com.example.foodify.Database.Entities.CommentEntity;
import com.example.foodify.Database.Entities.ProductEntity;
import com.example.foodify.Enums.Categories;
import com.example.foodify.Database.Entities.UserEntity;
import com.example.foodify.Enums.FoodStyle;
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

        // Insert test data
        //insertTestData();
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

                if(destination.getId() == R.id.shopFilterFragment){

                    //mToolbar.setVisibility(View.GONE);
                    //mBottomNav.setVisibility(View.GONE);
                }
                else{
                    mMenu.findItem(R.id.points_icon).setVisible(false);
                    showSoftwareKeyboard(false);



                }
            }
        });
    }


    private void showSoftwareKeyboard(boolean showKeyboard){

        final InputMethodManager inputManager = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (this.getCurrentFocus() != null) {
            inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), showKeyboard ? InputMethodManager.SHOW_FORCED : InputMethodManager.HIDE_NOT_ALWAYS);
        }
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
        mNavController.navigate(actionId, bundle);

    }

    /**
     * When menu is created
     * @param menu
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenu = menu;
        // Add toolbar
        getMenuInflater().inflate(R.menu.toolbarmenuitems, menu);
        MenuItem searchItem = menu.findItem(R.id.search_icon);
        SearchView my_search_view = (SearchView) searchItem.getActionView();
        my_search_view.setSubmitButtonEnabled(false);
        ImageView searchIcon = (ImageView)my_search_view.findViewById(androidx.appcompat.R.id.search_button);
        searchIcon.setImageResource(R.drawable.ic_search_black_24dp);
        SearchView.SearchAutoComplete searchAutoComplete = my_search_view.findViewById(androidx.appcompat.R.id.search_src_text);
        searchAutoComplete.setHintTextColor(getResources().getColor(R.color.white));
        searchAutoComplete.setTextColor(getResources().getColor(android.R.color.white));
        searchItem.setVisible(false);





        //createBasketTests();
       // updateTotalBasket();
        setCount(this, mShoppingCart.getCount());
        //mToolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_arrow_back_black_24dp));
        setupDestinationListeners();



        return true;
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

        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());

        // RESET THE DATABASE
        //DatabasePopulator.clearDatabase(db);

        UserEntity user = new UserEntity();
        user.setFirstname("test");
        user.setLastname("test");
        user.setAddress("test");
        user.setEmail("test");
        user.setPassword("test");
        DatabasePopulator.addUser(db, user);

        // COCA COLA (nodiscount)
        ProductEntity test_item = new ProductEntity();
        test_item.setName("Coca-Cola");
        test_item.setPrice(2.55f);
        test_item.setDiscount(0f);
        test_item.setLikability(0.83f);
        test_item.setDescription("Drink hem ijskoud.");
        test_item.setFoodstyle(FoodStyle.OMNIVORE);
        test_item.setCategory(Categories.DRANKEN);
        DatabasePopulator.addProduct(db, test_item);

        ProductInShopEntity colainshop = new ProductInShopEntity();
        colainshop.setPrice(2.55f);
        colainshop.setProductname("Coca-Cola");
        colainshop.setShopname("Delhaize");
        colainshop.setAvailability(false);

        DatabasePopulator.addProductInShop(db, colainshop);

        CommentEntity cola_comment = new CommentEntity();
        cola_comment.setProductname("Coca-Cola");
        cola_comment.setCommentText("Mijn favoriete drankje!");
        cola_comment.setAuthor("Mr Beast");

        DatabasePopulator.addComment(db, cola_comment);

        // GEHAKT

        ProductEntity gehakt = new ProductEntity();
        gehakt.setName("Gehakt");
        gehakt.setPrice(1.56f);
        gehakt.setDiscount(0f);
        gehakt.setLikability(0.23f);
        gehakt.setDescription("1 ton goedkoop gehakt");
        gehakt.setFoodstyle(FoodStyle.OMNIVORE);
        gehakt.setCategory(Categories.VLEES);
        DatabasePopulator.addProduct(db ,gehakt);

        ProductInShopEntity gehaktinshop = new ProductInShopEntity();
        gehaktinshop.setPrice(1.56f);
        gehaktinshop.setProductname("Gehakt");
        gehaktinshop.setShopname("Okay");
        gehaktinshop.setAvailability(true);

        DatabasePopulator.addProductInShop(db, gehaktinshop);

        // TEST PRODUCT "peren"
        ProductEntity peer = new ProductEntity();
        peer.setName("Peer");
        peer.setPrice(10.33f);
        peer.setDescription("Verse peren van het nieuwe seizoen.");
        peer.setLikability(0.73f);
        peer.setDiscount(0.10f);
        peer.setFoodstyle(FoodStyle.VEGAN);
        peer.setCategory(Categories.FRUIT);

        DatabasePopulator.addProduct(db, peer);

        ProductInShopEntity peerinshop = new ProductInShopEntity();
        peerinshop.setPrice(10.33f);
        peerinshop.setProductname("Peer");
        peerinshop.setShopname("Okay");
        peerinshop.setAvailability(true);



        DatabasePopulator.addProductInShop(db, peerinshop);

        ProductInShopEntity peerinshop1 = new ProductInShopEntity();
        peerinshop1.setPrice(12.33f);
        peerinshop1.setProductname("Peer");
        peerinshop1.setShopname("Carrefour");
        peerinshop1.setAvailability(true);

        DatabasePopulator.addProductInShop(db, peerinshop1);


        CommentEntity pear_comment = new CommentEntity();
        pear_comment.setProductname("Peer");
        pear_comment.setCommentText("Nog nooit zo'n verse en lekkere peren gegeten!");
        pear_comment.setAuthor("peerlover99");

        DatabasePopulator.addComment(db, pear_comment);

        // WORTEL

        ProductEntity wortel = new ProductEntity();
        wortel.setName("Wortel");
        wortel.setPrice(10.33f);
        wortel.setDescription("Vers en gezond.");
        wortel.setLikability(0.93f);
        wortel.setDiscount(0.15f);
        wortel.setFoodstyle(FoodStyle.VEGAN);
        wortel.setCategory(Categories.GROENTEN);

        DatabasePopulator.addProduct(db, wortel);

        ProductInShopEntity wortelinshop = new ProductInShopEntity();
        wortelinshop.setPrice(10.33f);
        wortelinshop.setProductname("Wortel");
        wortelinshop.setShopname("Colruyt");
        wortelinshop.setAvailability(false);

        DatabasePopulator.addProductInShop(db, wortelinshop);

        ProductInShopEntity wortelinshop1 = new ProductInShopEntity();
        wortelinshop1.setPrice(11.55f);
        wortelinshop1.setProductname("Wortel");
        wortelinshop1.setShopname("Delhaize");
        wortelinshop1.setAvailability(true);



        DatabasePopulator.addProductInShop(db, wortelinshop1);


    }


}
