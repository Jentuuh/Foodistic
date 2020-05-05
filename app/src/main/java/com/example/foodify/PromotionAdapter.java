package com.example.foodify;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodify.Product.Promotion;

import java.util.ArrayList;

public class PromotionAdapter extends RecyclerView.Adapter<PromotionAdapter.MyViewHolder> {
    ArrayList<Promotion> mPromotionList;


    public PromotionAdapter(ArrayList<Promotion> promotionlist){
        mPromotionList = promotionlist;

    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView promotionImg;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            promotionImg = itemView.findViewById(R.id.promotion_img);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.promotionitem,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Promotion promotion = mPromotionList.get(position);
        holder.promotionImg.setImageDrawable(promotion.getPromotionImg());
    }

    @Override
    public int getItemCount() {
        return mPromotionList.size();
    }


}
