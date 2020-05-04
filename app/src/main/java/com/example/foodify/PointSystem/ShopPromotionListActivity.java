package com.example.foodify.PointSystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.foodify.MainActivity;
import com.example.foodify.R;

import java.util.ArrayList;

public class ShopPromotionListActivity extends AppCompatActivity {

    private TextView shopName;
    private TextView shopPoints;

    private ArrayList<ShopPromotion> shopPromotionList;
    private ShopPromotionAdapter adapter;
    private ListView shopPromotionView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_promotion_list);

        // Toolbar
        setupToolbar();
        setupPromotionList();
        handleIntent();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("TabToStart", 2);
                startActivity(intent);
            }
        });
    }
    private void setupPromotionList() {
        shopPromotionList = new ArrayList<ShopPromotion>();
        //TODO: get from DB
        shopPromotionList.add(new ShopPromotion(R.drawable.itemplaceholder, "Wortel", 10));
        shopPromotionList.add(new ShopPromotion(R.drawable.itemplaceholder, "Wortel", 20));
        shopPromotionList.add(new ShopPromotion(R.drawable.itemplaceholder, "Wortel", 30));
        shopPromotionList.add(new ShopPromotion(R.drawable.itemplaceholder, "Wortel", 40));

        adapter = new ShopPromotionAdapter(getApplicationContext(), shopPromotionList);

        shopPromotionView = (ListView) findViewById(R.id.listView_shop_promotion_list);
        shopPromotionView.setAdapter(adapter);
    }
    @SuppressLint("SetTextI18n")
    private void handleIntent() {
        Intent intent = getIntent();
        shopName = (TextView)findViewById(R.id.textView_shop_name);
        shopPoints = (TextView)findViewById(R.id.textView_shop_points);

        Bundle extras = intent.getExtras();
        // If data was send
        if (extras != null) {
            // Set layout
            shopName.setText(extras.getString("SHOP_NAME"));
            shopPoints.setText(Integer.toString(extras.getInt("SHOP_POINTS")));
        }
    }

    /**
     * Callback functions which handles what needs to happen when promotion button is clicked
     */
    private void onPromotionPress(int position) {

    }
}
