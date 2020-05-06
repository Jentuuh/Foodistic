package com.example.foodify.PointSystem;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.foodify.FontManager;
import com.example.foodify.R;

import java.util.ArrayList;
import java.util.Random;


/**
 * Fragment which handles the store points
 * @author Joris Bertram
 */
public class PointFragment extends Fragment {

    private enum Tab {
        REDEEM,
        SUBMIT
    }

    private Typeface iconFont;

    private Button m_redeemButton;
    private Button m_submitButton;

    private Button m_sendButton;
    private Button m_qrButton;

    private ListView m_redeemContent;
    private ConstraintLayout m_submitContent;

    private EditText m_codeText;

    private ArrayList<ShopPoint> shopPointList;
    private ShopPointAdapter adapter;
    private ListView shopPointView;

    public PointFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_point, container, false);
    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);

        iconFont = FontManager.getTypeface(getActivity(), FontManager.FONTAWESOME);

        setupTabSystem();
        setupViewTab();
        setupRedeemTab();

        // Set default tab
        setTab(Tab.REDEEM);
    }


    @Override
    public void onStart() {
        super.onStart();
    }


    /**
     * Callback functions which handles what needs to happen when send button is clicked
     */
    private void onCodeSend() {
        String code = m_codeText.getText().toString();

        if (!code.isEmpty()) {
            // Get random shop
            ShopPoint randomShop = shopPointList.get(getRandomInt(0, shopPointList.size() - 1));

            // Get random points
            int points = getRandomInt(5, 25);

            //TODO: [DB] get points -> add points -> set points

            // Display toast
            String toastText = "Je kreeg " + points + " punten voor " + randomShop.getName() +  "! \n code: " + code;
            Toast toast = Toast.makeText(getActivity(), toastText, Toast.LENGTH_SHORT);
            toast.show();
            m_codeText.setText("");

            // Add points to list
            randomShop.addPoints(points);
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * Callback functions which handles what needs to happen when shop button is clicked
     * @param position
     */
    private void onShopPress(int position) {
        ShopPoint shopPoint = shopPointList.get(position);
        Intent openPromotionIntent = new Intent(this.getContext(), ShopPromotionListActivity.class);
        openPromotionIntent.putExtra("SHOP_NAME", shopPoint.getName());
        openPromotionIntent.putExtra("SHOP_POINTS", shopPoint.getPoints());
        startActivity(openPromotionIntent);
    }

    private int getRandomInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    /**
     * SETUPS
     */
    private void setupTabSystem() {
        // Setup TabButtons
        m_redeemButton = (Button) getView().findViewById(R.id.button_redeem);
        m_submitButton = (Button) getView().findViewById(R.id.button_submit);

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
        m_redeemContent = (ListView) getView().findViewById(R.id.listView_shop_point_list);
        m_submitContent = (ConstraintLayout) getView().findViewById(R.id.constraintLayout_submit);
    }
    private void setupViewTab() {
        shopPointList = new ArrayList<ShopPoint>();
        //TODO: get from DB
        shopPointList.add(new ShopPoint(R.drawable.colruyt, "Colruyt", 20));
        shopPointList.add(new ShopPoint(R.drawable.carrefour,"Carrefour", 0));
        shopPointList.add(new ShopPoint(R.drawable.okay,"Okay", 15));
        shopPointList.add(new ShopPoint(R.drawable.delhaize,"Delhaize", 40));

        adapter = new ShopPointAdapter(this.requireContext(), shopPointList);

        shopPointView = (ListView) getView().findViewById(R.id.listView_shop_point_list);
        shopPointView.setAdapter(adapter);
        shopPointView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) { onShopPress(position); }
        });
    }
    private void setupRedeemTab() {
        // Setup SendButton
        m_sendButton = (Button) getView().findViewById(R.id.button_send);
        m_sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCodeSend();
            }
        });

        // Setup QRButton
        m_qrButton = getView().findViewById(R.id.button_qr);
        m_qrButton.setTypeface(iconFont);

        // Get CodeText
        m_codeText = (EditText) getView().findViewById(R.id.editText_code);
    }

    /**
     * Set tab to given tab
     * @param tab
     */
    private void setTab(PointFragment.Tab tab) {
        int activeButtonColor = getResources().getColor(R.color.buttonBlueActive);

        // Update Activity
        resetTabs();

        if (tab == PointFragment.Tab.REDEEM)
        {
            m_redeemButton.setBackgroundTintList(ColorStateList.valueOf(activeButtonColor));
            m_redeemContent.setVisibility(View.VISIBLE);
        }
        else if (tab == PointFragment.Tab.SUBMIT)
        {

            m_submitButton.setBackgroundTintList(ColorStateList.valueOf(activeButtonColor));
            m_submitContent.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Reset tabs (show none)
     */
    private void resetTabs() {
        int buttonColor = getResources().getColor(R.color.buttonBlue);
        m_redeemButton.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        m_submitButton.setBackgroundTintList(ColorStateList.valueOf(buttonColor));

        m_redeemContent.setVisibility(View.GONE);
        m_submitContent.setVisibility(View.GONE);
    }
}
