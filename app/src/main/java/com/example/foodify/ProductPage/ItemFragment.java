package com.example.foodify.ProductPage;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodify.Product.ProductItem;
import com.example.foodify.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class ItemFragment extends Fragment {
    ProductItem mItem;
    TabAdapter mTabAdapter;
    ViewPager2 mViewpager;
    public ItemFragment() {
        // Required empty public constructor
    }

    static ItemFragment newInstance(int itemId) {
        ItemFragment myFragment = new ItemFragment();

        Bundle args = new Bundle();
        args.putInt("itemId", itemId);
        myFragment.setArguments(args);

        return myFragment;
    }

    public void setItem(ProductItem item){
        mItem = item;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args != null) {
            int itemId = args.getInt("itemId");

            // TODO: NOTE: I COMMENTED THESE if AND else OUT, IT DIDN'T WORK OTHERWISE BECAUSE ITEMID WASN'T EQUAL TO -1
//            if (itemId == -1){
//                //TODO remove this, this is to test if it works
                Drawable img = getResources().getDrawable(R.drawable.itemplaceholder);
                mItem = new ProductItem("Test_item",  1.5555555555555f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, 0.50f, img);
                Log.v("ItemFragment" ,"Id was -1");
//            }
//            else{
//                //TODO get item from db with same id
//            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //super.onViewCreated(view, savedInstanceState);
        mTabAdapter = new TabAdapter(this, mItem);
        mViewpager = view.findViewById(R.id.tabContainer);
        mViewpager.setAdapter(mTabAdapter);
        TabLayout tabLayout = view.findViewById(R.id.tabs);
        new TabLayoutMediator(tabLayout, mViewpager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText("Test" + (position + 1)); // TODO give tab name of fragment
            }
        }).attach();

    }
}
