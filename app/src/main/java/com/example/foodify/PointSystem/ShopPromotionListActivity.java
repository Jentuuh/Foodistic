package com.example.foodify.PointSystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.foodify.MainActivity;
import com.example.foodify.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class ShopPromotionListActivity extends AppCompatActivity {

    private Toolbar toolbar;
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pointbar, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        final MenuItem pointItem = menu.findItem(R.id.points_icon);
        ConstraintLayout rootView = (ConstraintLayout) pointItem.getActionView();
        shopPoints = (TextView) rootView.getChildAt(0);

        // Set shop points by intent
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        // If data was send
        if (extras != null)
            shopPoints.setText(String.valueOf(extras.getInt("SHOP_POINTS")));

        return super.onPrepareOptionsMenu(menu);
    }

    private void setupToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set shop name by intent
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        // If data was send
        if (extras != null)
            getSupportActionBar().setTitle(extras.getString("SHOP_NAME"));

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
        shopPromotionView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) { onPromotionPress(position); }
        });
    }

    /**
     * Callback functions which handles what needs to happen when promotion button is clicked
     */
    private void onPromotionPress(int position) {
        ShopPromotion shopPromotion = shopPromotionList.get(position);
        Intent openBarcodeIntent = new Intent(this, BarcodeActivity.class);
        openBarcodeIntent.putExtra("PROMOTION_NAME", shopPromotion.getName());
        startActivity(openBarcodeIntent);
    }
}
