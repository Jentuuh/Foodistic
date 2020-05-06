package com.example.foodify.PointSystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.foodify.Database.AppDatabase;
import com.example.foodify.MainActivity;
import com.example.foodify.R;

import java.util.ArrayList;

public class ShopPromotionListActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Menu menu;

    private ArrayList<ShopPromotion> shopPromotionList;
    private ShopPromotionAdapter adapter;
    private ListView shopPromotionView;

    private String shopName;
    private int shopPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_promotion_list);

        // Load intent data
        handleIntent();

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
        this.menu = menu;
        updateToolbar();

        return super.onPrepareOptionsMenu(menu);
    }

    private void handleIntent() {
        Intent intent = getIntent();

        Bundle extras = intent.getExtras();
        if (extras != null) {
            shopName = extras.getString("SHOP_NAME");
            shopPoints = extras.getInt("SHOP_POINTS");
        }
    }

    /**
     * SETUPS
     */
    private void setupToolbar() {
        toolbar = findViewById(R.id.toolbar_promotion_list);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(shopName);
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
        openPurchaseDialog(position);
    }

    /**
     * Opens up purchase dialog for item on given position
     * @param position
     */
    private void openPurchaseDialog(int position) {
        final ShopPromotion shopPromotion = shopPromotionList.get(position);

        new AlertDialog.Builder(getApplicationContext())
                .setTitle("Accepteer aankoop" )
                .setMessage("Ben je zeker dat je deze aankoop wil bevestigen?\nJe totaal aantal punten voor " + shopName + " zal dalen naar " + String.valueOf(shopPoints - shopPromotion.getPoints()))

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Update shop points
                        shopPoints -= shopPromotion.getPoints();
                        updateToolbar();
                        updatePointsByName(shopName, shopPoints);

                        // Open up Barcode
                        //Intent openBarcodeIntent = new Intent(getApplication(), BarcodeActivity.class);
                        //openBarcodeIntent.putExtra("PROMOTION_NAME", shopPromotion.getName());
                        //startActivity(openBarcodeIntent);
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    /**
     * Updates toolbar when points changed
     */
    private void updateToolbar() {
        final MenuItem pointItem = menu.findItem(R.id.points_icon);
        ConstraintLayout rootView = (ConstraintLayout) pointItem.getActionView();
        TextView shopPointsIconText = (TextView) rootView.getChildAt(0);
        shopPointsIconText.setText(String.valueOf(shopPoints));
    }

    /**
     * ///////////////////////
     * DATA + DATABASE METHODS
     * ///////////////////////
     */

    /**
     * Update points for given shop
     */
    private void updatePointsByName(String name, int points) {
        AppDatabase db = AppDatabase.getDatabase(getApplication());
        db.m_foodisticDAO().setPointsByShop(name, points);
    }
}
