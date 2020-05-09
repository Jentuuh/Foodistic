package com.example.foodify.ProductPage;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.foodify.Product.Comment;
import com.example.foodify.R;

import java.util.List;

/**
 * @author jentevandersanden
 */
public class CommentAdapter extends ArrayAdapter<Comment> {
    private int resourceLayout;
    private Context mContext;
    private List<Comment> comments_to_display;
    private Fragment mFragment;

    public CommentAdapter(@NonNull Context context, int resource, List<Comment> comments_to_display, Fragment fragment) {
        super(context, 0, comments_to_display);
        resourceLayout = resource;
        this.mContext = context;
        this.comments_to_display = comments_to_display;
        mFragment = fragment;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(resourceLayout, null);

        }
        final Comment comment = comments_to_display.get(position);


        if (comment != null) {

            // Set the Author of the comment and the comment's text to the data retrieved from the dataobject
            TextView username = (TextView) view.findViewById(R.id.user_name);
            TextView comment_text = (TextView) view.findViewById(R.id.comment_text);

            if (username != null) {
                username.setText(comment.getM_author_name());
            }
            if (comment_text != null) {
                comment_text.setText(comment.getM_text());
            }
        }
        return view;
    }
}