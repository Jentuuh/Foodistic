package com.example.foodify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.widget.LinearLayout;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * Activity which handles the store points
 * @author Joris Bertram
 */
public class PointActivity extends AppCompatActivity {
    private enum Tab {
        VIEW,
        REDEEM,
        SUBMIT
    }

    private Button m_viewButton;
    private Button m_redeemButton;
    private Button m_submitButton;

    private LinearLayout m_viewContent;
    private ConstraintLayout m_submitContent;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);

        // Get Buttons
        m_viewButton = (Button)findViewById(R.id.button_view);
        m_redeemButton = (Button)findViewById(R.id.button_redeem);
        m_submitButton = (Button)findViewById(R.id.button_submit);

        // Get TabsViews
        m_viewContent = (LinearLayout)findViewById(R.id.linearLayout_view);
        m_submitContent = (ConstraintLayout)findViewById(R.id.constraintLayout_submit);

        // Add Button Listeners
        m_viewButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.i("viewButton", "Touched!");
                setTab(Tab.VIEW);
                return false;
            }
        });

        m_redeemButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.i("redeemButton", "Touched!");
                setTab(Tab.REDEEM);
                return false;
            }
        });

        m_submitButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.i("submitButton", "Touched!");
                setTab(Tab.SUBMIT);
                return false;
            }
        });

        // Set default tab
        setTab(Tab.VIEW);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void setTab(Tab tab) {
        // Update Activity
        resetTabs();

        if (tab == Tab.VIEW)
        {
            m_viewButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#17AAB9")));
            m_viewContent.setVisibility(View.VISIBLE);
        }
        else if (tab == Tab.REDEEM)
        {
            m_redeemButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#17AAB9")));
        }
        else if (tab == Tab.SUBMIT)
        {

            m_submitButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#17AAB9")));
            m_submitContent.setVisibility(View.VISIBLE);
        }
    }

    private void resetTabs() {
        m_viewButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#1CCADB")));
        m_redeemButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#1CCADB")));
        m_submitButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#1CCADB")));

        m_viewContent.setVisibility(View.GONE);
        m_submitContent.setVisibility(View.GONE);
    }
}