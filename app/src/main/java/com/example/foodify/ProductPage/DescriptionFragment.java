package com.example.foodify.ProductPage;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodify.Database.AppDatabase;
import com.example.foodify.Database.Entities.CommentEntity;
import com.example.foodify.Enums.FoodStyle;
import com.example.foodify.Login.LoginActivity;
import com.example.foodify.Login.SaveSharedPreference;
import com.example.foodify.MainActivity;
import com.example.foodify.Product.Comment;
import com.example.foodify.Product.ProductItem;
import com.example.foodify.R;
import com.example.foodify.User.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author jentevandersanden
 * This represents the tab in the product page that represents the description of the product
 * and the comments on the product.
 */
public class DescriptionFragment extends Fragment {

    private ProductItem mItem;
    private TextView description;
    private ListView comment_container;
    private EditText comment_text_field;
    private Button place_button;
    private CommentAdapter comment_adapter;
    private List<Comment> comments_to_display;
    private final Fragment self_ref = this;


    public DescriptionFragment(ProductItem item) {
        // Required empty public constructor
        mItem = item;
        Log.v("DescriptionFragment", "created");

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_description, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        description = getActivity().findViewById(R.id.description);
        // TODO: set text from description to textview
        if(description != null) {
            description.setText(mItem.getDescription());
        }
        comments_to_display = new ArrayList<Comment>();
        comment_container = (ListView) getActivity().findViewById(R.id.list_of_comments);


        comment_adapter = new CommentAdapter(getContext(),R.layout.comment_item ,comments_to_display, this);
        comment_container.setAdapter(comment_adapter);
        loadComments();
        comment_adapter.notifyDataSetChanged();

        comment_text_field = (EditText) getActivity().findViewById(R.id.place_reaction);

        place_button = (Button) getActivity().findViewById(R.id.place_button);
        place_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Check if the user is logged in
                if(SaveSharedPreference.getUserName(getActivity()).length() == 0){
                    // TODO: NAVIGATE TO THE LOGIN ACTIVITY
                    NavHostFragment.findNavController(getParentFragment()).navigate(R.id.action_itemFragment_to_loginActivity);
                }
                else{
                    // Place comment, you're already logged in!
                    // Check if the field isn't empty
                    if((comment_text_field.getText().toString().matches(""))){
                        Toast.makeText(getContext(), "Vul eerst het veld in met uw reactie.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        placeComment();
                    }

                }

            }
        });

    }


    /**
     * Loads all the comments belonging to the fragment that represents a certain product.
     */
    private void loadComments(){

        comments_to_display.clear();

        AppDatabase db = AppDatabase.getDatabase(getContext());
        List<CommentEntity> results = db.m_foodisticDAO().getCommentsForProduct(mItem.getName());
        parseIntoComments(results);

        // TEST COMMENT
        //comments_to_display.add(new Comment(new User("Jente","Vandersanden", new Date(9237497), FoodStyle.OMNIVORE, "teutenlaan 19"), "This is a test comment haha."));

        comment_adapter.notifyDataSetChanged();
    }

    private void placeComment(){

        String comment_text = comment_text_field.getText().toString();

        if(comment_text == ""){
            // Do nothing
        }
        else {
            // TODO: Change the username for the actual name of the logged in person

            AppDatabase db = AppDatabase.getDatabase(getContext());
            // Create a new comment entity
            CommentEntity comment_to_insert = new CommentEntity();
            comment_to_insert.setAuthor(SaveSharedPreference.getUserName(getContext()));
            comment_to_insert.setCommentText(comment_text);
            comment_to_insert.setProductname(mItem.getName());

            // Insert the entity into the database
            db.m_foodisticDAO().createComment(comment_to_insert);
            loadComments();
            comment_adapter.notifyDataSetChanged();

            // Feedback to the user
            Toast.makeText(getContext(), "Uw reactie werd geplaatst.", Toast.LENGTH_SHORT).show();
        }

    }

    private void parseIntoComments(List<CommentEntity> db_data){
        for(CommentEntity comment :db_data){
            Comment comment_to_display = new Comment(comment.getAuthor(), comment.getCommentText());
            comments_to_display.add(comment_to_display);
        }
    }

    public void setItem(ProductItem item){
        mItem = item;
    }
}
