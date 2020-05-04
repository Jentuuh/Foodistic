package com.example.foodify.ShoppingList;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.foodify.Enums.FoodStyle;
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
    private ShoppingList m_list_to_display;
    private ListView m_productcontainer;
    private ArrayList<ProductItem> m_items_to_display;
    ArrayAdapter<String> m_product_list_adapter;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);

    }

    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);

        // Retrieve list container from layout
        m_productcontainer = (ListView) getView().findViewById(R.id.ListItemView);

        // Load the shopping lists from the database
        m_items_to_display = new ArrayList<ProductItem>();

    }


    @Override
    public void onStart() {
        super.onStart();

        // Retrieve the name of the list through the navigation arguments
        m_list_name = getArguments().getString("listname");

        // Set the list title in the view
        ((TextView) getActivity().findViewById(R.id.listTitle)) .setText(m_list_name);

        // Retrieve the product items (we do this onStart, every time this fragment is started there
        // might have been updates that have occured.
        getListData();

        // Put the names of the lists in a new arraylist, for the arrayadapter
        ArrayList<String> product_names = new ArrayList<String>();
        for (ProductItem product: m_items_to_display) {
            product_names.add(product.getName());
        }

        // Create the arrayadapter and set it for the listcontainer
        m_product_list_adapter = new ArrayAdapter<String>(getActivity(), R.layout.shoppinglistitem, product_names);

        m_productcontainer.setAdapter(m_product_list_adapter);
    }

    public void openProduct(View view){
        // TODO : open Item activity if an item is clicked
    }




    /**
     * Retrieves the data for a certain list from the database.
     */
    private void getListData(){
        // TODO: retrieve data from DB
        m_list_to_display = new ShoppingList(m_list_name);
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(new Comment(new User("testuser", new Date(11052019), FoodStyle.OMNIVORE ),"This is a tescomment"));
        Drawable img = getResources().getDrawable(R.drawable.itemplaceholder);
        m_list_to_display.addItem(new ProductItem("Jonagold Apples", 2.5f, "These are the best premium apples you can get.", 50.4f, comments, img ));
    }

}
