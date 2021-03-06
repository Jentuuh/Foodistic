package com.example.foodify.ProductPage;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodify.Database.AppDatabase;
import com.example.foodify.Database.Entities.ProductEntity;
import com.example.foodify.Enums.Categories;
import com.example.foodify.Enums.FoodStyle;
import com.example.foodify.MainActivity;
import com.example.foodify.Product.ProductItem;
import com.example.foodify.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.text.DecimalFormat;

public class ItemFragment extends Fragment {
    private ProductItem mItem;
    private TabAdapter mTabAdapter;
    private ViewPager2 mViewpager;
    private final ItemFragment m_self_ref = this;

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
            String itemName = args.getString("itemName");

            // TODO: NOTE: I COMMENTED THESE if AND else OUT, IT DIDN'T WORK OTHERWISE BECAUSE ITEMID WASN'T EQUAL TO -1
            if (itemName != null){


                //TODO get item from db with same id
                AppDatabase db = AppDatabase.getDatabase(getActivity());
                 ProductEntity dbProd = db.m_foodisticDAO().getProduct(itemName);
                Drawable img = getResources().getDrawable(R.drawable.itemplaceholder);

                mItem = new ProductItem(dbProd.getName(), dbProd.getPrice(), dbProd.getDescription(), dbProd.getLikability(), null, dbProd.getDiscount(), img, dbProd.getEnumCategory());
                if (dbProd.getFoodstyleEnum() != null)
                    mItem.setFoodstyle(dbProd.getFoodstyleEnum());
                else
                    mItem.setFoodstyle(FoodStyle.OMNIVORE);


            }
            else{
                //TODO remove this, this is to test if it works
                Drawable img = getResources().getDrawable(R.drawable.itemplaceholder);
                mItem = new ProductItem("No  such item",  1.5555555555555f, "Very interesting item it is an item that has item values and stuff, u know the item things...", 0.5f,null, 0.50f, img, Categories.DRANKEN);
                Log.v("ItemFragment" ,"Id was -1");

            }
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
                switch(position){
                    case 0:
                        tab.setText("Algemene informatie");
                        break;
                    case 1:
                        tab.setText("Prijsvergelijking");
                        break;

                }
                 // TODO give tab name of fragment
            }
        }).attach();
        setupLayout(view);
    }

    public void setupLayout(@NonNull View view){
        TextView prodName = view.findViewById(R.id.main_product_name);
        TextView prodPrice = view.findViewById(R.id.main_item_price);
        TextView prevProdPrice = view.findViewById(R.id.main_item_prev_price);
        ImageView prodImg = view.findViewById(R.id.main_item_img);
        TextView prodLike = view.findViewById(R.id.like_percentage);
        ImageButton cart = view.findViewById(R.id.add_to_cart_button);
        ImageButton list = view.findViewById(R.id.add_to_list_button);
        final ImageButton like = view.findViewById(R.id.like_button);
        final ImageButton dislike = view.findViewById(R.id.dislike_button);

        prodName.setText(mItem.getName());
        if (mItem.getDiscount() != 0){
            prodPrice.setText("€ " + new DecimalFormat("###.##").format(mItem.calculatePrice()));
            prevProdPrice.setText("€ " + new DecimalFormat("###.##").format(mItem.getPrice()));
            prevProdPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else{
            prodPrice.setText("€ " + new DecimalFormat("###.##").format(mItem.getPrice()));
            prodPrice.setTextColor(getResources().getColor(android.R.color.secondary_text_light));

        }
        prodImg.setImageDrawable(mItem.getImage());
        prodLike.setText("("+new DecimalFormat("###").format((mItem.getLikability()*100)) + "% goede beoordelingen)");



        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Open item fragment once item is pressed
                ((MainActivity)getActivity()).addToCart(mItem);
                // User feedback
                Toast.makeText(getActivity(), "Toegevoegd aan je winkelmandje!", Toast.LENGTH_SHORT).show();
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO : actually add the product to the list
                Bundle bundle = new Bundle();
                bundle.putString("productName", mItem.getName());
                (NavHostFragment.findNavController(m_self_ref)).navigate(R.id.action_itemFragment_to_chooseListFragment, bundle);
//                Toast.makeText(getActivity(), "Toegevoegd aan je boodschappenlijstje!", Toast.LENGTH_SHORT).show();


            }
        });

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                like.setSelected(!like.isSelected());
                dislike.setSelected(false);
                //TODO add
            }
        });
        dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dislike.setSelected(!dislike.isSelected());
                like.setSelected(false);
                //TODO add dislike
            }
        });
    }
}
