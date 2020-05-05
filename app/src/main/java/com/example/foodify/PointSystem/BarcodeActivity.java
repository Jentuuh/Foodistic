package com.example.foodify.PointSystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.foodify.R;

public class BarcodeActivity extends AppCompatActivity {

    private TextView barcodeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        setupToolbar();
        setupPromotionName();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });
    }

    private void setupPromotionName() {
        Intent intent = getIntent();

        barcodeName = (TextView)findViewById(R.id.textView_barcode_name);

        Bundle extras = intent.getExtras();
        // If data was send
        if (extras != null) {
            // Set layout
            barcodeName.setText(extras.getString("PROMOTION_NAME"));
        }
    }
}
