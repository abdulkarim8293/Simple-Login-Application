package com.abdulkarim.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText userName, password;
    private Button loginButton;
    private TextView registerTextView;

    private LoginSession loginSession;
    private InputValidation inputValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        if (loginSession.isUserLoggedIn()){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createUserLogin();
            }
        });

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void init() {

        userName = findViewById(R.id.userNameEditText);
        password = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        registerTextView = findViewById(R.id.registerTextViewId);

        loginSession = new LoginSession(this);
        inputValidation = new InputValidation();
    }

    private void createUserLogin(){

        if (!inputValidation.isEmptyEditText(userName,"Please Enter Your Email")) {
            return;
        }
        if (!inputValidation.isValidEmail(userName,"Enter Valid Email Address")) {
            return;
        }
        if (!inputValidation.isEmptyEditText(password,"Please Enter Your Password")) {
            return;
        }

        Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
        loginSession.createLoginSession(userName.getText().toString(), password.getText().toString());
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }
}
