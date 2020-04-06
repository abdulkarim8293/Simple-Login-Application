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

    private Button registerButton;
    private EditText userNameEditText, passwordEditText,retypePasswordEditText;
    private TextView loginTextView;

    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);

        prefManager = new PrefManager(this);

        init();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                String userName = userNameEditText.getText().toString().trim();
                String userPassword = passwordEditText.getText().toString().trim();
                String userRetypePassword = retypePasswordEditText.getText().toString().trim();

                if (!userName.matches(emailPattern)){
                    Toast.makeText(RegisterActivity.this, "Enter Valid Email Address", Toast.LENGTH_SHORT).show();
                }else if (!userPassword.equals(userRetypePassword)){
                    Toast.makeText(RegisterActivity.this, "Password Not Match", Toast.LENGTH_SHORT).show();
                }else {

                    prefManager.createUserLoginSession(userName,userPassword);
                    startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                    finish();

                }
            }
        });

        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }

    private void init() {
        registerButton = findViewById(R.id.registerButton);
        userNameEditText = findViewById(R.id.userNameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginTextView = findViewById(R.id.registerTextViewId);
    }
}
