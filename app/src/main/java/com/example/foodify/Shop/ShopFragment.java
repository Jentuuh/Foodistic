package com.example.foodify.Shop;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodify.Product.ProductItem;
import com.example.foodify.Product.Promotion;
import com.example.foodify.R;

import java.util.ArrayList;


/**
 * Fragment of the shop
 * @author Tim-Lukas Blom
 */
public class ShopFragment extends Fragment {

    private ArrayList<Promotion> mPromotionList;
    private PromotionAdapter mPromotionAdapter;

    private ArrayList<ProductItem> mDiscountedItems;
    private DiscountAdapter mDiscountAdapter;
    private LinearLayoutManager mPromotionLayoutManager;
    private LinearLayoutManager mDiscountLayoutManager;

    public ShopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop, container, false);


    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPromotionList = new ArrayList<Promotion>();
        mDiscountedItems = new ArrayList<ProductItem>();
        mDiscountAdapter = new DiscountAdapter(mDiscountedItems, this);
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
        LinearLayoutManager mPromotionLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mPromotionLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        promotionRecyclerView.setLayoutManager(mPromotionLayoutManager);
        promotionRecyclerView.setItemAnimator(new DefaultItemAnimator());
        promotionRecyclerView.setAdapter(mPromotionAdapter);
    }

    private void setupDiscounts(){
        RecyclerView discountRecyclerView = getView().findViewById(R.id.discount_list);
        LinearLayoutManager mDiscountLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mDiscountLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        discountRecyclerView.setLayoutManager(mDiscountLayoutManager);
        discountRecyclerView.setItemAnimator(new DefaultItemAnimator());
        discountRecyclerView.setAdapter(mDiscountAdapter);

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

        mDiscountedItems.add(new ProductItem("Test_item",  1.5555555555555f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, 0.50f, img));
        mDiscountedItems.add(new ProductItem("Test_item",  1.5555555555555f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, 0.50f, img));
        mDiscountedItems.add(new ProductItem("Test_item",  1.5555555555555f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, 0.50f, img));
        mDiscountedItems.add(new ProductItem("Test_item",  1.5555555555555f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, 0.50f, img));
        mDiscountedItems.add(new ProductItem("Test_item",  1.5555555555555f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, 0.50f, img));

    }
}