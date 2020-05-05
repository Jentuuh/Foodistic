package com.example.foodify;

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

import com.example.foodify.Product.Promotion;

import java.util.ArrayList;


/**
 * Fragment of the shop
 * @author Tim-Lukas Blom
 */
public class ShopFragment extends Fragment {

    private ArrayList<Promotion> mPromotionList;
    private PromotionAdapter mPromotionAdapter;
    private LinearLayoutManager mLayoutManager;

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPromotionList = new ArrayList<Promotion>();
        RecyclerView recyclerView = getView().findViewById(R.id.promotion_list);
        mPromotionAdapter = new PromotionAdapter(mPromotionList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mPromotionAdapter);
        testData();


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
    }
}
