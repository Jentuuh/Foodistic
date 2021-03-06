package com.example.foodify.ShoppingList;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodify.Database.AppDatabase;
import com.example.foodify.Database.Entities.ProductEntity;
import com.example.foodify.Database.Entities.ProductOnListEntity;
import com.example.foodify.Enums.FoodStyle;
import com.example.foodify.Product.Comment;
import com.example.foodify.Product.ProductItem;
import com.example.foodify.R;
import com.example.foodify.ShoppingCart.ShoppingCartItem;
import com.example.foodify.User.User;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author jentevandersanden
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements SearchView.OnQueryTextListener {

    private String m_list_name;
    private ListView m_productcontainer;
    private TextView m_total_price;
    private Button m_uncheck_all_button;
    private Button m_remove_checked_button;
    private ShoppingList m_list_to_display;
    private ShopListAdapter adapter;
    private TextView m_emptyMessage;
    private SearchView mSearchFilter;
    private Menu mMenu;


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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        mMenu = menu;
        setupSearchFilter();
    }

    @Override
    public void onStart() {
        super.onStart();

        m_emptyMessage = (TextView)getActivity().findViewById(R.id.emptyMessage);
        m_total_price = (TextView)getActivity().findViewById(R.id.total_price);

        // Retrieve the name of the list through the navigation arguments
        m_list_name = getArguments().getString("listname");
        // Set the list title in the view
        ((TextView) getActivity().findViewById(R.id.listTitle)).setText(m_list_name);

        // Set a listener for the 'uncheck all button'
        m_uncheck_all_button = (Button) getActivity().findViewById(R.id.uncheck_button);
        m_uncheck_all_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (ShoppingCartItem item : m_list_to_display.getProducts()){
                    item.setChecked(false);
                    AppDatabase db = AppDatabase.getDatabase(getContext());
                    db.m_foodisticDAO().uncheckAll(false, m_list_to_display.getName());
                    adapter.notifyDataSetChanged();
                }
            }
        });

        m_remove_checked_button = (Button) getActivity().findViewById(R.id.remove_checked);
        m_remove_checked_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    AppDatabase db = AppDatabase.getDatabase(getContext());
                    db.m_foodisticDAO().deleteAllChecked(true);
                    getListData();
                    Toast.makeText(getContext(), "Geselecteerde producten werden van '" + m_list_name + "' verwijderd.", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                    adapter.fillFilteredList();
            }
        });

        m_list_to_display = new ShoppingList(m_list_name, getArguments().getInt("listID"));

        // Retrieve list container from layout
        m_productcontainer = (ListView) getView().findViewById(R.id.ListItemView);

        adapter = new ShopListAdapter(getActivity(), R.layout.shoplistitem, m_list_to_display, this);

        // Set the adapter for the listcontainer
        m_productcontainer.setAdapter(adapter);
        // Retrieve the list data
        getListData();
        adapter.fillFilteredList();

    }




    /**
     * Retrieves the data for a certain list from the database.
     */
    public void getListData(){
        // TODO: retrieve data from DB
        // Reset
        m_list_to_display.getProducts().clear();

        // Get data from db
        AppDatabase db = AppDatabase.getDatabase(getContext());

//        // TEST PRODUCT "peren"
//        ProductEntity product = new ProductEntity();
//        product.setID(01);
//        product.setName("peren");
//        product.setPrice(2.33f);
//        product.setDescription("Nice pears");
//        product.setLikability(0.33f);
//        product.setDiscount(1.01f);
//        db.m_foodisticDAO().createProduct(product);
//
//        ProductOnListEntity test = new ProductOnListEntity();
//        test.setID(02);
//        test.setProductid(01);
//        test.setListID(02);
//        test.setProductname(product.getName());
//        test.setQuantity(3);
//        db.m_foodisticDAO().addProductToList(test);

        // Retrieve items on this list
        List<ProductOnListEntity> results = db.m_foodisticDAO().getItemsOnList(m_list_to_display.getName());
        Log.v("itemcount", Integer.toString(results.size()));

        // Parse it into usable objects
        parseIntoListItems(results);
        updateEmptyMessage();
        updatePrice();

//        // TEST PRODUCT
//        ArrayList<Comment> comments = new ArrayList<>();
//        comments.add(new Comment(new User("testuser", "test", new Date(11052019), FoodStyle.OMNIVORE, "test"),"This is a tescomment"));
//        Drawable img = getResources().getDrawable(R.drawable.itemplaceholder);
//        m_list_to_display.addItem(new ProductItem("Jonagold Apples", 2.5f, "These are the best premium apples you can get.", 50.4f, comments, img), getContext());
//        adapter.notifyDataSetChanged();
    }


    /**
     * Parses db data into usable ProductItem objects.
     * @param db_data
     */
    public void parseIntoListItems(List<ProductOnListEntity> db_data) {
        AppDatabase db = AppDatabase.getDatabase(getContext());

        for (ProductOnListEntity product_on_list : db_data) {
            ProductEntity product_to_add = db.m_foodisticDAO().getProduct(product_on_list.getProductname());

            if (product_to_add != null) {
                ProductItem to_add = new ProductItem(product_to_add.getName(), product_to_add.getPrice(), product_to_add.getDescription(), product_to_add.getLikability(), null, product_to_add.getDiscount(), null, product_to_add.getEnumCategory());
                ShoppingCartItem list_item = new ShoppingCartItem(to_add);

                // Make sure the quantity is set to the quantity from the database
                list_item.setQuantity(product_on_list.getQuantity());;
                list_item.setChecked(product_on_list.isChecked());
                m_list_to_display.addItem(list_item);

            }
        }
    }

    private float calculatePrice(){
        float result = 0.0f;
        for(ShoppingCartItem item: m_list_to_display.getProducts()){
            result += (item.getItem().getPrice()) * item.getQuantity();
        }
        return result;
    }

    public void updatePrice(){
        m_total_price.setText("Totaal: €" + new DecimalFormat("###.##").format(calculatePrice()));
    }

    public void updateEmptyMessage(){

        if(m_list_to_display.getProducts().isEmpty()){
            m_emptyMessage.setVisibility(View.VISIBLE);
        }
        else{
            m_emptyMessage.setVisibility(View.INVISIBLE);
        }
    }

    public void setupSearchFilter(){
        mMenu.findItem(R.id.search_icon).setVisible(true);
        mSearchFilter = (SearchView) mMenu.findItem(R.id.search_icon).getActionView();
        //mSearchFilter = (SearchView) getView().findViewById(R.id.search_filter);
        //mSearchFilter.setIconifiedByDefault(false);
        mSearchFilter.setOnQueryTextListener(this);
        mSearchFilter.setSubmitButtonEnabled(true);
        mSearchFilter.setQueryHint("Search Here");
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return true;
    }
}
