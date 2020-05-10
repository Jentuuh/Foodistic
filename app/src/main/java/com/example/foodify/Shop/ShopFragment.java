package com.example.foodify.Shop;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.foodify.Database.AppDatabase;
import com.example.foodify.Database.Entities.ProductEntity;
import com.example.foodify.Enums.FoodStyle;
import com.example.foodify.Enums.Size;
import com.example.foodify.Product.ProductItem;
import com.example.foodify.Product.Promotion;
import com.example.foodify.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Fragment of the shop
 * @author Tim-Lukas Blom
 */
public class ShopFragment extends Fragment {

    private ArrayList<Promotion> mPromotionList;
    private PromotionAdapter mPromotionAdapter;
    private Menu mMenu;
    private ArrayList<ProductItem> mDiscountedItems;
    private ProductAdapter mDiscountAdapter;
    private LinearLayoutManager mPromotionLayoutManager;
    private LinearLayoutManager mDiscountLayoutManager;
    private SearchView mSearch;

    public ShopFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        mMenu = menu;
        mMenu.findItem(R.id.search_icon).setVisible(true);
        setupSearchFilter();
        super.onCreateOptionsMenu(menu, inflater);
    }




    private void setupSearchFilter(){
         mSearch = (SearchView) mMenu.findItem(R.id.search_icon).getActionView();
        mMenu.findItem(R.id.search_icon).setEnabled(false);
        mSearch.setFocusable(false);
        mSearch.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchClick();
                //search.clearFocus();
            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop, container, false);


    }


    private void onSearchClick(){
        Bundle bundle = new Bundle();
        String[] filterTypes = new String[4];
        filterTypes[3] = "search";

        bundle.putStringArray("filterType", filterTypes);
        NavHostFragment.findNavController(this).navigate(R.id.shop_filter_onSearch, bundle);

    }

    private void discountViewMore(){
        Bundle bundle = new Bundle();
        String[] filters = new String[4];
        filters[0] = "discount";
        bundle.putStringArray("filterType", filters);
        NavHostFragment.findNavController(this).navigate(R.id.shop_to_filter_on_view_more, bundle);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mPromotionList = new ArrayList<Promotion>();
        mDiscountedItems = new ArrayList<ProductItem>();
        mDiscountAdapter = new ProductAdapter(mDiscountedItems, this, Size.SMALL);
        mPromotionAdapter = new PromotionAdapter(mPromotionList);

        testData();


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       setupPromotions();
       setupDiscounts();



    }

    private void setupPromotions(){
        RecyclerView promotionRecyclerView = getView().findViewById(R.id.promotion_list);
        mPromotionLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mPromotionLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        promotionRecyclerView.setLayoutManager(mPromotionLayoutManager);
        promotionRecyclerView.setItemAnimator(new DefaultItemAnimator());
        promotionRecyclerView.setAdapter(mPromotionAdapter);
    }

    private void setupDiscounts(){
        LinearLayout discountViewMore = getView().findViewById(R.id.discount_view_more);
        discountViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discountViewMore();
            }
        });
        RecyclerView discountRecyclerView = getView().findViewById(R.id.discount_list);
        mDiscountLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mDiscountLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        discountRecyclerView.setLayoutManager(mDiscountLayoutManager);
        discountRecyclerView.setItemAnimator(new DefaultItemAnimator());
        discountRecyclerView.setAdapter(mDiscountAdapter);
        getDiscountedItems();

    }


    private void getDiscountedItems(){
        AppDatabase db = AppDatabase.getDatabase(getActivity());
        List<ProductEntity> products_from_db = db.m_foodisticDAO().getAllProducts();
        // Parse the database data to actual objects that we can use in our code
        parseFromDBToObjects(products_from_db);

        //TODO remove testdata
        // testData();
        mDiscountAdapter.addAll();
        mDiscountAdapter.discountedItems();

    }


    /**
     * Method that parses a list of ShoppingListEntity objects retrieved from a db into actual ShoppingList objects.
     * @param db_products : the products retrieved from the db
     */
    private void parseFromDBToObjects(List<ProductEntity> db_products){
        Drawable img = getResources().getDrawable(R.drawable.itemplaceholder);
        for(ProductEntity dbItem : db_products){
            //TODO Dynamically add img to product, add comments
            ProductItem newProd = new ProductItem(dbItem.getName(), dbItem.getPrice(), dbItem.getDescription(), dbItem.getLikability(), null, dbItem.getDiscount(), img);
            if (dbItem.getFoodstyleEnum() != null)
                newProd.setFoodstyle(dbItem.getFoodstyleEnum());
            else
                newProd.setFoodstyle(FoodStyle.OMNIVORE);
            mDiscountedItems.add(newProd);
        }


    }

    public void testData(){
        Drawable img = getResources().getDrawable(R.drawable.itemplaceholder);
        mPromotionList.add(new Promotion(img));
        mPromotionList.add(new Promotion(img));
        mPromotionList.add(new Promotion(img));
        mPromotionList.add(new Promotion(img));
        mPromotionList.add(new Promotion(img));
        mPromotionList.add(new Promotion(img));
        mPromotionAdapter.notifyDataSetChanged();
/*
        mDiscountedItems.add(new ProductItem("Test_item",  1.5555555555555f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, 0.50f, img));
        mDiscountedItems.add(new ProductItem("Test_item",  1.5555555555555f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, 0.50f, img));
        mDiscountedItems.add(new ProductItem("Test_item",  1.5555555555555f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, 0.50f, img));
        mDiscountedItems.add(new ProductItem("Test_item",  1.5555555555555f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, 0.50f, img));
        mDiscountedItems.add(new ProductItem("Test_item",  1.5555555555555f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, 0.50f, img));
*/
    }
}
