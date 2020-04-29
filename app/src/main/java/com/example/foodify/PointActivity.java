package com.example.foodify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

    private ImageButton m_backButton;

    private Button m_viewButton;
    private Button m_redeemButton;
    private Button m_submitButton;

    private Button m_sendButton;
    private Button m_qrButton;

    private LinearLayout m_viewContent;
    private ConstraintLayout m_submitContent;

    private EditText m_codeText;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);

        Typeface iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);


        // Setup BackButton
        m_backButton = (ImageButton)findViewById(R.id.button_back);
        m_backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPress();
            }
        });

        // Setup TabButtons
        m_viewButton = (Button)findViewById(R.id.button_view);
        m_redeemButton = (Button)findViewById(R.id.button_redeem);
        m_submitButton = (Button)findViewById(R.id.button_submit);

        m_viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTab(Tab.VIEW);
            }
        });
        m_redeemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTab(Tab.REDEEM);
            }
        });
        m_submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTab(Tab.SUBMIT);
            }
        });

        // Get TabsContents
        m_viewContent = (LinearLayout)findViewById(R.id.linearLayout_view);
        m_submitContent = (ConstraintLayout)findViewById(R.id.constraintLayout_submit);


        // Setup SendButton
        m_sendButton = (Button)findViewById(R.id.button_send);
        m_sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCodeSend();
            }
        });

        // Setup QRButton
        m_qrButton = findViewById(R.id.button_qr);
        m_qrButton.setTypeface(iconFont);

        // Get CodeText
        m_codeText = (EditText)findViewById(R.id.editText_code);

        // Set default tab
        setTab(Tab.VIEW);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * Callback functions which handles what needs to happen when back button is clicked
     */
    private void onBackPress() {
        String toastText = "Gaat een Activity terug...";
        Toast toast = Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * Callback functions which handles what needs to happen when send button is clicked
     */
    private void onCodeSend() {
        String code = m_codeText.getText().toString();

        if (!code.isEmpty()) {
            String toastText = "Je kreeg 100 punten met code: " + code;
            Toast toast = Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_SHORT);
            toast.show();
            m_codeText.setText("");
        }
    }

    /**
     * Set tab to given tab
     * @param tab
     */
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

    /**
     * Reset tabs (show none)
     */
    private void resetTabs() {
        m_viewButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#1CCADB")));
        m_redeemButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#1CCADB")));
        m_submitButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#1CCADB")));

        m_viewContent.setVisibility(View.GONE);
        m_submitContent.setVisibility(View.GONE);
    }
}