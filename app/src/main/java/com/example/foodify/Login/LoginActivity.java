package com.example.foodify.Login;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodify.Database.AppDatabase;
import com.example.foodify.Database.Entities.UserEntity;
import com.example.foodify.MainActivity;
import com.example.foodify.R;

/**
 * @author jentevandersanden
 * This class represents the login activity where the user needs to fill in their email and password
 * to log into the application.
 */
public class LoginActivity extends AppCompatActivity {

    private Button m_login_button;
    private Button m_register_button;
    private EditText m_email;
    private EditText m_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        m_login_button = (Button) findViewById(R.id.loginButton);
        m_register_button = (Button) findViewById(R.id.toRegisterActivity);
        m_email = (EditText) findViewById(R.id.email);
        m_password = (EditText) findViewById(R.id.password);


        m_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginClick();
            }
        });
        m_register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRegisterClick();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("TabToStart", 1);
                startActivity(intent);
            }
        });

    }

    public void onLoginClick(){
        if(checkFilledIn())
        login(m_email.getText().toString(), m_password.getText().toString());
    }


    public void onRegisterClick(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
    private boolean checkFilledIn(){
        if(!(m_email.getText().toString().matches("") || m_password.getText().toString().matches(""))){
            return true;
        }
        else{
            // Display a short toast to give feedback to the user that they didn't fill in every textfield.
            Toast.makeText(this, "Vul eerst alle velden in.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void login( String email, String password){

        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        UserEntity user =  db.m_foodisticDAO().getUserByEmail(email);

        if (user != null && password.equals(user.getPassword())){
            // Save this logged in user
            SaveSharedPreference.setUserName(getApplicationContext(), user.getFirstname());
            // Start main activity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Je bent nu ingelogd als: " + SaveSharedPreference.getUserName(getApplicationContext()) + ".", Toast.LENGTH_SHORT).show();
        }
        else{
            SaveSharedPreference.setUserName(getApplicationContext(), "");
            Toast.makeText(getApplicationContext(), "Inloggen mislukt. Ben je zeker dat je gegevens kloppen?", Toast.LENGTH_SHORT).show();
        }
    }
}
