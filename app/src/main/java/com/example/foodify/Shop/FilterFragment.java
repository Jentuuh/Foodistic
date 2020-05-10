package com.example.foodify.Shop;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.foodify.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilterFragment extends Fragment {
    private CheckBox mDiscount;
    private CheckBox mPriceAsc;
    private CheckBox mPriceDesc;
    private Button mApply;
    private Button mReset;
    public FilterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_filter, container, false);
        mPriceAsc = (CheckBox)rootView.findViewById(R.id.price_asc_check);
        mPriceDesc = (CheckBox)rootView.findViewById(R.id.price_desc_check);
        mDiscount = (CheckBox)rootView.findViewById(R.id.discount_check);
        mApply = (Button)rootView.findViewById(R.id.apply_filters);
        mReset = (Button)rootView.findViewById(R.id.reset_filters);

        attachListeners();
        uncheckAll();
        getFilters();
        return rootView;
    }

    public void attachListeners() {
        mPriceDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()){
                    Log.v("desc", "ischecked");
                    mPriceAsc.setChecked(false);
                }
                else
                    Log.v("desc", "not checked");
            }
        });

        mPriceAsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()){
                    Log.v("asc", "ischecked");
                    mPriceDesc.setChecked(false);
                }
                else
                    Log.v("asc", "not checked");
            }
        });

        mApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyFilters();
            }
        });
        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uncheckAll();
            }
        });
    }


    private void applyFilters(){
        Bundle filters = new Bundle();
        String[] filterTypes = new String[4];
        if (mDiscount.isChecked())
            filterTypes[0] = "discount";
        if (mPriceAsc.isChecked())
            filterTypes[1] = "priceASC";
        if (mPriceDesc.isChecked())
            filterTypes[2] = "priceDESC";
        filters.putStringArray("filterType",filterTypes);

        NavHostFragment.findNavController(this).navigate(R.id.shopFilterFragment, filters);
    }


    private void getFilters(){
        Log.v("filterfrag", "getfilters()");
        Bundle bundle = getArguments();
        String[] filters = bundle.getStringArray("filterType");
        if (filters != null) {
            for (String filter : filters) {
                if (filter != null) {
                    switch (filter) {
                        case "discount":
                            mDiscount.setChecked(true);
                            Log.v("filterfrag", "discount");
                            break;
                        case "priceASC":
                            mPriceAsc.setChecked(true);
                            mPriceDesc.setChecked(false);
                            Log.v("filterfrag", "asc");
                            break;
                        case "priceDESC":
                            mPriceAsc.setChecked(false);
                            mPriceDesc.setChecked(true);
                            Log.v("filterfrag", "desc");
                            break;

                    }

                }
                else{
                    Log.v("filterfrag", "null");

                }
            }
        }
        else{
            uncheckAll();
            Log.v("filterfrag", "filters = null");

        }
    }

    private void uncheckAll(){
        mDiscount.setChecked(false);
        mPriceAsc.setChecked(false);
        mPriceDesc.setChecked(false);

    }
}
