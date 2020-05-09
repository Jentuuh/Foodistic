package com.example.foodify.ProductPage;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.foodify.Database.AppDatabase;
import com.example.foodify.Database.Entities.ProductEntity;
import com.example.foodify.Database.Entities.ProductInShopEntity;
import com.example.foodify.Product.ProductInShop;
import com.example.foodify.Product.ProductItem;
import com.example.foodify.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jentevandersanden
 * This class represents the replacable fragment in the product page in which the prices between
 * different stores can be compared.
 */
public class ComparisonFragment extends Fragment {

    private ProductItem mItem;
    private ListView m_comparison_list;
    private ComparisonAdapter m_adapter;
    private ArrayList<ProductInShop> m_comparisons;

    public ComparisonFragment(ProductItem item) {
        // Required empty public constructor
        mItem = item;
        Log.v("DescriptionFragment", "created");

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.shop_comparison_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        m_comparisons = new ArrayList<ProductInShop>();
        m_comparison_list = (ListView)getActivity().findViewById(R.id.comparison_list);
        m_adapter = new ComparisonAdapter(getContext(), R.layout.comparison_item, m_comparisons, this);
        m_comparison_list.setAdapter(m_adapter);
        loadComparisons();

    }

    /**
     * Gets all comparisons for this product from the database.
     */
    private void loadComparisons(){
        m_comparisons.clear();

        AppDatabase db = AppDatabase.getDatabase(getContext());
        List<ProductInShopEntity> results = db.m_foodisticDAO().getShopsForProduct(mItem.getName());

        parseIntoObjects(results);
        m_adapter.notifyDataSetChanged();
    }

    /**
     * Parses the database data into usable objects (for the adapter).
     * @param db_data
     */
    private void parseIntoObjects(List<ProductInShopEntity> db_data){
        for(ProductInShopEntity comparison : db_data){
            m_comparisons.add(new ProductInShop(comparison.getProductname(),comparison.getPrice(),comparison.getShopname()));
        }
    }


}
