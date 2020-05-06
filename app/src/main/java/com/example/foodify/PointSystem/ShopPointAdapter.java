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

import com.example.foodify.R;

import java.util.ArrayList;
import java.util.List;

public class ShopPointAdapter extends ArrayAdapter<ShopPoint> {
    private Context context;
    private List<ShopPoint> shopPointList;

    private ImageView logo;
    private TextView shopName;
    private TextView points;

    public ShopPointAdapter(@NonNull Context context, ArrayList<ShopPoint> list) {
        super(context, 0, list);
        this.context = context;
        shopPointList = list;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        View shopPointItem = convertView;
        if (shopPointItem == null) {
            shopPointItem = LayoutInflater.from(context).inflate(R.layout.shop_point_item, container, false);
        }

        final ShopPoint currentShopPoint = shopPointList.get(position);

        // Get layout
        logo = (ImageView) shopPointItem.findViewById(R.id.imageView_shop_icon);
        shopName = (TextView) shopPointItem.findViewById(R.id.textView_shop_list_name);
        points = (TextView) shopPointItem.findViewById(R.id.textView_points);

        // Set layout
        logo.setImageResource(currentShopPoint.getLogo());
        shopName.setText(currentShopPoint.getName().toUpperCase());
        points.setText(Integer.toString(currentShopPoint.getPoints()));

        return shopPointItem;
    }
}
