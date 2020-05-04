package com.example.foodify;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

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

    private void login(String email, String password){
        // TODO: implement
    }
}
