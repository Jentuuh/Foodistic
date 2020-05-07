package com.example.foodify.PointSystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodify.R;

public class BarcodeActivity extends AppCompatActivity {

    private Toolbar  toolbar;

    private String promotionName;
    private int promotionPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        // Load data
        handleIntent();

        setupToolbar();
        showPurchaseToast();
    }

    private void handleIntent() {
        Intent intent = getIntent();

        Bundle extras = intent.getExtras();
        if (extras != null) {
            promotionName = extras.getString("PROMOTION_NAME");
            promotionPoints = extras.getInt("PROMOTION_POINTS");
        }
    }

    /**
     * SETUPS
     */
    private void setupToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(promotionName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });
    }

    /**
     * Shows toast of purchase (with appropriate info)
     */
    private void showPurchaseToast() {
        Context context = getApplicationContext();
        CharSequence text =  "Je hebt net " + promotionName + " gekocht voor " + promotionPoints + " punten." ;
        int duration = Toast.LENGTH_LONG;

        Toast.makeText(context, text, duration).show();
    }
}
