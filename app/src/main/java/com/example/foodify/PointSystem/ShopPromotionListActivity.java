package com.example.foodify.PointSystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodify.Database.AppDatabase;
import com.example.foodify.Database.Entities.PointEntity;
import com.example.foodify.Database.Entities.PromotionEntity;
import com.example.foodify.MainActivity;
import com.example.foodify.Product.Promotion;
import com.example.foodify.R;

import java.util.ArrayList;
import java.util.List;

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

        shopPromotionList = new ArrayList<ShopPromotion>();

        // Load intent data
        handleIntent();

        // Fill DB if not already
        createTestData();

        // Load data
        getPromotions();

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
        final ShopPromotion shopPromotion = shopPromotionList.get(position);

        // If enough points
        if (shopPoints >= shopPromotion.getPoints())
            openPurchaseDialog(shopPromotion);
        else
            showNotEnoughToast();

    }

    /**
     * Opens up purchase dialog for item on given shopPromotion
     * @param shopPromotion
     */
    private void openPurchaseDialog(final ShopPromotion shopPromotion) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ShopPromotionListActivity.this);
        builder.setTitle("Accepteer aankoop");
        builder.setMessage("Ben je zeker dat je deze aankoop wil bevestigen?\n\n" +
                            "Je totaal aantal punten voor " + shopName +  " zal dalen naar " +
                            String.valueOf(shopPoints - shopPromotion.getPoints()) + ".");

        // Specifying a listener allows you to take an action before dismissing the dialog.
        // The dialog is automatically dismissed when a dialog button is clicked.
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Update shop points
                shopPoints -= shopPromotion.getPoints();
                updatePointsByName(shopName, shopPoints);
                updateToolbar();

                // Open up Barcode
                Intent openBarcodeIntent = new Intent(getApplication(), BarcodeActivity.class);
                openBarcodeIntent.putExtra("PROMOTION_NAME", shopPromotion.getName());
                openBarcodeIntent.putExtra("PROMOTION_POINTS", shopPromotion.getPoints());
                startActivity(openBarcodeIntent);
            }
        });

        // A null listener allows the button to dismiss the dialog and take no further action.
        builder.setNegativeButton(android.R.string.no, null);
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        // Create dialog
        AlertDialog dialog = builder.create();
        dialog.show();

        // Change icon color of dialog
        ImageView imageView = dialog.findViewById(android.R.id.icon);
        if (imageView != null)
            imageView.setColorFilter(Color.parseColor("#DACD0D"), android.graphics.PorterDuff.Mode.SRC_IN);
    }

    private void showNotEnoughToast() {
        Context context = getApplicationContext();
        CharSequence text =  "Niet genoeg punten om aankoop te maken.";
        int duration = Toast.LENGTH_SHORT;

        Toast.makeText(context, text, duration).show();
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
     * Creates test data to fill up DB
     */
    private void createTestData() {
        AppDatabase db = AppDatabase.getDatabase(getApplication());

        if (shopName.equals("Colruyt")) {
            shopPromotionList.add(new ShopPromotion(R.drawable.apple, "Appel", 5));
            shopPromotionList.add(new ShopPromotion(R.drawable.cheese, "Kaas", 10));
            shopPromotionList.add(new ShopPromotion(R.drawable.carrot, "Wortel", 6));
        }
        else if (shopName.equals("Delhaize")) {
            shopPromotionList.add(new ShopPromotion(R.drawable.salad, "Sla", 6));
            shopPromotionList.add(new ShopPromotion(R.drawable.tomato, "Tomaat", 7));
            shopPromotionList.add(new ShopPromotion(R.drawable.apple, "Appel", 4));
        }
        else if (shopName.equals("Okay")) {
            shopPromotionList.add(new ShopPromotion(R.drawable.cheese, "Kaas", 5));
            shopPromotionList.add(new ShopPromotion(R.drawable.carrot, "Wortel", 12));
            shopPromotionList.add(new ShopPromotion(R.drawable.salad, "Sla", 8));
        }
        else if (shopName.equals("Carrefour")) {
            shopPromotionList.add(new ShopPromotion(R.drawable.carrot, "Wortel", 4));
            shopPromotionList.add(new ShopPromotion(R.drawable.cheese, "Kaas", 9));
            shopPromotionList.add(new ShopPromotion(R.drawable.tomato, "Tomaat", 8));
        }


        for (ShopPromotion promotion : shopPromotionList) {
            PromotionEntity entity = new PromotionEntity();
            entity.setImage(promotion.getImage());
            entity.setName(promotion.getName());
            entity.setPoints(promotion.getPoints());
            entity.setShop(shopName);

            db.m_foodisticDAO().createPromotion(entity);
        }

        // Clear list to let DB load right one
        shopPromotionList.clear();
    }

    /**
     * Retrieves all shopPoints from DB
     */
    private void getPromotions(){
        // Retrieve the shop points from the database
        AppDatabase db = AppDatabase.getDatabase(getApplication());
        List<PromotionEntity> lists_from_db = db.m_foodisticDAO().getPromotionsByShop(shopName);

        // Parse the database data to actual objects that we can use in our code
        parseFromDBToObjects(lists_from_db);

        // TEST DATA
        //shopPromotionList.add(new ShopPromotion(R.drawable.itemplaceholder, "Carrot", 6));
    }


    /**
     * Method that parses a list of PointEntity objects retrieved from a db into actual ShopPoint objects.
     * @param db_lists : the list retrieved from the db
     */
    private void parseFromDBToObjects(List<PromotionEntity> db_lists){
        for(PromotionEntity entity : db_lists)
            shopPromotionList.add(new ShopPromotion(entity.getImage(), entity.getName(), entity.getPoints()));
    }

    /**
     * Update points for given shop
     */
    private void updatePointsByName(String name, int points) {
        AppDatabase db = AppDatabase.getDatabase(getApplication());
        db.m_foodisticDAO().setPointsByShop(name, points);
    }
}
