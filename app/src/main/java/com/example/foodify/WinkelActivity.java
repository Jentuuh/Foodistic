package com.example.foodify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WinkelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winkel);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_container);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        NavigationUI.setupWithNavController(bottomNav, navController);
        /*
        //bottom nav
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.shopNavItem:
                        Toast.makeText(WinkelActivity.this, "Shop", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.pointsNavItem:
                        Toast.makeText(WinkelActivity.this, "Points", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.listNavItem:
                        Toast.makeText(WinkelActivity.this, "lists", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.profileNavItem:
                        Toast.makeText(WinkelActivity.this, "lists", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

         */
    }
}
