package com.abdulkarim.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Button logOutButton;
    private TextView userName;
    private LoginSession loginSession;

    private HashMap<String,String> user = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.userName);
        logOutButton = findViewById(R.id.logOutButton);

        loginSession = new LoginSession(this);
        user = loginSession.getUserDetails();

        userName.setText(user.get(LoginSession.KEY_USER_NAME));

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginSession.logoutUser();
            }
        });
    }
}
