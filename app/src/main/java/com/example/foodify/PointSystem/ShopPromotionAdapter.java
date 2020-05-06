package com.example.foodify.PointSystem;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.foodify.R;

import java.util.ArrayList;
import java.util.List;

public class ShopPromotionAdapter extends ArrayAdapter<ShopPromotion> {
    private Context context;
    private List<ShopPromotion> shopPromotionList;

    private ImageView image;
    private TextView name;

    private TextView points;
    private ImageView coins;

    public ShopPromotionAdapter(@NonNull Context context, ArrayList<ShopPromotion> list) {
        super(context, 0, list);
        this.context = context;
        shopPromotionList = list;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup container) {
        View shopPromotionItem = convertView;
        if (shopPromotionItem == null) {
            shopPromotionItem = LayoutInflater.from(context).inflate(R.layout.shop_promotion_item, container, false);
        }

        final ShopPromotion currentShopPromotion = shopPromotionList.get(position);

        // Get layout
        image = (ImageView) shopPromotionItem.findViewById(R.id.imageView_promotion_image);
        name = (TextView) shopPromotionItem.findViewById(R.id.textView_promotion_name);

        points = (TextView) shopPromotionItem.findViewById(R.id.textView_promotion_points);
        coins = (ImageView) shopPromotionItem.findViewById(R.id.imageView_coins2);

        // Set layout
        image.setImageResource(currentShopPromotion.getImage());
        name.setText(currentShopPromotion.getName());

        points.setText(Integer.toString(currentShopPromotion.getPoints()));
        coins.setImageResource(R.drawable.ic_coins_solid);

        return shopPromotionItem;
    }
}
