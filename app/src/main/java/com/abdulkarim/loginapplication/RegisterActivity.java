package com.abdulkarim.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText userName, password, retypePassword;
    private Button registerButton;
    private TextView loginTextView;

    private InputValidation inputValidation;
    private LoginSession loginSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);

        init();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createUserForLogin();
            }
        });

        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    private void createUserForLogin() {

        if (!inputValidation.isEmptyEditText(userName, "Please Enter Your Email")) {
            return;
        }
        if (!inputValidation.isValidEmail(userName, "Enter Valid Email Address")) {
            return;
        }
        if (!inputValidation.isEmptyEditText(password, "Please Enter Your Password")) {
            return;
        }
        if (!inputValidation.isEmptyEditText(retypePassword, "Please Enter Confirm Password")) {
            return;
        }
        if (!inputValidation.isPasswordMatches(password, retypePassword, "Don't Match Your Password")) {
            return;
        }

        Toast.makeText(this, "Registration \nAnd Login Successful", Toast.LENGTH_SHORT).show();
        loginSession.createLoginSession(userName.getText().toString(),password.getText().toString());
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }

    private void init() {

        registerButton = findViewById(R.id.registerButton);
        userName = findViewById(R.id.userNameEditText);
        password = findViewById(R.id.passwordEditText);
        retypePassword = findViewById(R.id.retypePasswordEditText);
        loginTextView = findViewById(R.id.registerTextViewId);

        inputValidation = new InputValidation();
        loginSession = new LoginSession(this);
    }
}
