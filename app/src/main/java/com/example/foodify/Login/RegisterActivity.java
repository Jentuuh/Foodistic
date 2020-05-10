package com.example.foodify.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.example.foodify.Database.AppDatabase;
import com.example.foodify.Database.Entities.UserEntity;
import com.example.foodify.MainActivity;
import com.example.foodify.R;

/**
 * @author jentevandersanden
 */
public class RegisterActivity extends AppCompatActivity {

    private Button m_login_button;
    private Button m_register_button;
    private EditText m_first_name;
    private EditText m_last_name;
    private EditText m_email;
    private EditText m_address;
    private EditText m_password;
    private EditText m_confirm_password;

    /**
     * @author jentevandersanden
     * This class represents the first window (login/register) that will be presented in the application.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        m_register_button = (Button) findViewById(R.id.registerButton);
        m_login_button = (Button) findViewById(R.id.loginButton);
        m_first_name = (EditText) findViewById(R.id.firstName);
        m_last_name = (EditText) findViewById(R.id.lastName);
        m_email = (EditText) findViewById(R.id.email);
        m_address = (EditText) findViewById(R.id.address);
        m_password = (EditText) findViewById(R.id.password);
        m_confirm_password = (EditText) findViewById(R.id.passwordConfirm);


        m_register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRegisterClick();
            }
        });

        m_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginClick();
            }
        });
    }


    /**
     * Callback method that handles what needs to happen when the register button is clicked.
     */
    public void onRegisterClick(){
        if(checkFilledIn() && checkPasswords()){
            // Create user
            insertIntoDataBase((m_first_name.getText()).toString(), (m_last_name.getText()).toString(), (m_email.getText()).toString() , (m_address.getText()).toString(), (m_password.getText()).toString());
            // Log in
            login(m_first_name.getText().toString(), m_email.getText().toString(), m_password.getText().toString());
        }

    }

    /**
     * Callback method that handles what needs to happen when the login button is clicked.
     */
    public void onLoginClick(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Method that checks if password and confirm_password match.
     * @return boolean
     */
    public boolean checkPasswords(){
        if(m_password.getText().toString().matches(m_confirm_password.getText().toString())){
            return true;
        }
        else {
            // Display a short toast to give feedback to the user that the passwords didn't match.
            Toast.makeText(this, "Zorg ervoor dat de paswoorden overeen komen.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    /**
     * Method that checks whether all text fields were filled in.
     * @return boolean
     */
    public boolean checkFilledIn(){
        if(!(m_first_name.getText().toString().matches("") || m_last_name.getText().toString().matches("") || m_address.getText().toString().matches("") || m_password.getText().toString().matches("") || m_confirm_password.getText().toString().matches("") || m_email.getText().toString().matches(""))){
            return true;
        }
        else{
            // Display a short toast to give feedback to the user that they didn't fill in every textfield.
            Toast.makeText(this, "Vul eerst alle velden in.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    /**
     * Method that inserts the register data into the database. (Creates a user account)
     * @param firstname
     * @param lastname
     * @param email
     * @param address
     * @param password
     */
    public boolean insertIntoDataBase(String firstname, String lastname, String email, String address, String password){

        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        UserEntity new_user = new UserEntity();
        new_user.setFirstname(firstname);
        new_user.setLastname(lastname);
        new_user.setEmail(email);
        new_user.setAddress(address);
        new_user.setPassword(password);

        db.m_foodisticDAO().createUser(new_user);
        return true;
    }

    /**
     * Method that logs the user in.
     */
    public void login(String name, String email, String password){

        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        UserEntity user =  db.m_foodisticDAO().getUserByName(name);

        if (password.equals(user.getPassword())){
            // Save this logged in account
            SaveSharedPreference.setUserName(getApplicationContext(), name);
            // Start main activity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else{
            SaveSharedPreference.setUserName(getApplicationContext(), "");
            // User feedback
            Toast.makeText(getApplicationContext(), "Inloggen mislukt. Ben je zeker dat je gegevens kloppen?", Toast.LENGTH_SHORT).show();
        }
    }


}
