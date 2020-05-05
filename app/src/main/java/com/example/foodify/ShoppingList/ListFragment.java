package com.example.foodify.ShoppingList;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodify.Enums.FoodStyle;
import com.example.foodify.ListAdapter;
import com.example.foodify.MainActivity;
import com.example.foodify.Product.Comment;
import com.example.foodify.Product.ProductItem;
import com.example.foodify.R;
import com.example.foodify.User.User;

import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    private String m_list_name;
    private ListView m_productcontainer;
    private ShoppingList m_list_to_display;
    private ArrayList<String> m_product_names;
    private ListAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);

    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);


    }


    @Override
    public void onStart() {
        super.onStart();

        // Retrieve the name of the list through the navigation arguments
        m_list_name = getArguments().getString("listname");
        // Set the list title in the view
        ((TextView) getActivity().findViewById(R.id.listTitle)).setText(m_list_name);

        m_list_to_display = new ShoppingList(m_list_name);

        // Retrieve list container from layout
        m_productcontainer = (ListView) getView().findViewById(R.id.ListItemView);

        adapter = new ListAdapter(getActivity(), R.layout.basket_item, m_list_to_display);

        // Set the adapter for the listcontainer
        m_productcontainer.setAdapter(adapter);

        // Retrieve the list data
        getListData();
    }


    /**
     * Retrieves the data for a certain list from the database.
     */
    private void getListData(){
        // TODO: retrieve data from DB
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(new Comment(new User("testuser", "test", new Date(11052019), FoodStyle.OMNIVORE, "test"),"This is a tescomment"));
        Drawable img = getResources().getDrawable(R.drawable.itemplaceholder);
        m_list_to_display.addItem(new ProductItem("Jonagold Apples", 2.5f, "These are the best premium apples you can get.", 50.4f, comments, img ));
        adapter.notifyDataSetChanged();
    }

}
