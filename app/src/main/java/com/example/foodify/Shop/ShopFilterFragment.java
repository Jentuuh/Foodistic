package com.example.foodify.Shop;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;

import com.example.foodify.Database.AppDatabase;
import com.example.foodify.Database.Entities.ProductEntity;
import com.example.foodify.Enums.Size;
import com.example.foodify.Product.ProductItem;
import com.example.foodify.R;

import java.util.ArrayList;
import java.util.List;


/**
 *  Fragment where you can filter through a list of items
 */
public class ShopFilterFragment extends Fragment implements SearchView.OnQueryTextListener {

    private ProductAdapter mAdapter;
    private ArrayList<ProductItem> mFilteredProductItems;
    private SearchView mSearchFilter;
    private Menu mMenu;

    public ShopFilterFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mFilteredProductItems = new ArrayList<ProductItem>();
        mAdapter = new ProductAdapter(mFilteredProductItems, this, Size.LARGE);
        getProducts();

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        mMenu = menu;
        mMenu.findItem(R.id.search_icon).setVisible(true);
        setupSearchFilter();
        super.onCreateOptionsMenu(menu, inflater);



    }


    private void testData(){
        Drawable img = getResources().getDrawable(R.drawable.itemplaceholder);



        mFilteredProductItems.add(new ProductItem("wortel",  5.5555555555555f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, 0.50f, img));
        mFilteredProductItems.add(new ProductItem("appel",  0.5555555555555f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, 0.40f, img));
        mFilteredProductItems.add(new ProductItem("banaan",  2.5555555555555f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, 0.40f, img));
        mFilteredProductItems.add(new ProductItem("Test_item",  1.5555555555555f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, 0.20f, img));
        mFilteredProductItems.add(new ProductItem("Test_item",  1.5555555555555f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, 0.10f, img));
        mAdapter.notifyDataSetChanged();
        Log.v("filterfrag", ""+mFilteredProductItems.size());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_shop_filter, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView filterRecyclerView = getView().findViewById(R.id.filtered_product_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        filterRecyclerView.setLayoutManager(layoutManager);
        filterRecyclerView.setItemAnimator(new DefaultItemAnimator());
        filterRecyclerView.setAdapter(mAdapter);
        filterRecyclerView.setHasFixedSize(true);
    }

    public void setupSearchFilter(){
        mSearchFilter = (SearchView) mMenu.findItem(R.id.search_icon).getActionView();

        //mSearchFilter = (SearchView) getView().findViewById(R.id.search_filter);
        //mSearchFilter.setIconifiedByDefault(false);
        mSearchFilter.setOnQueryTextListener(this);
        mSearchFilter.setSubmitButtonEnabled(true);
        mSearchFilter.setQueryHint("Search Here");
    }

    private void getProducts(){

        mFilteredProductItems.clear();
        // Retrieve the shopping lists from the database
        AppDatabase db = AppDatabase.getDatabase(getActivity());
        List<ProductEntity> products_from_db = db.m_foodisticDAO().getAllProducts();
        // Parse the database data to actual objects that we can use in our code
        parseFromDBToObjects(products_from_db);

        //TODO remove testdata
        testData();
        mAdapter.addAll();
    }

    /**
     * Method that parses a list of ShoppingListEntity objects retrieved from a db into actual ShoppingList objects.
     * @param db_products : the products retrieved from the db
     */
    private void parseFromDBToObjects(List<ProductEntity> db_products){
        Drawable img = getResources().getDrawable(R.drawable.itemplaceholder);
        for(ProductEntity dbItem : db_products){
            //TODO Dynamically add img to product, add comments
            mFilteredProductItems.add(new ProductItem(dbItem.getName(), dbItem.getPrice(), dbItem.getDescription(), dbItem.getLikability(), null, dbItem.getDiscount(), img));
        }


    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mAdapter.filter(newText);
        return true;
    }
}
