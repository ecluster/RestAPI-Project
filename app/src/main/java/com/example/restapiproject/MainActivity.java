package com.example.restapiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUsername;
    EditText etPassword;
    View btnLogin;

    List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        users = new ArrayList<>();
        users.add(new User("Bret", "p123", 1));
        users.add(new User("Antonette", "p321", 2));
        users.add(new User("Samantha", "p468", 3));
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin) {
            etUsername = findViewById(R.id.etUsername);
            etPassword = findViewById(R.id.etPassword);

            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            if (hasInput(username, password)) {
                // check if user exist
                if (userExist(username, users)) {
                    // check if password is right
                    if (matchPassword(password, users)) {
                        Intent i = new Intent(this, LandingActivity.class);
                        startActivity(i);
                    }
                }
            }
        }
    }

    private boolean userExist(String u, List<User> users) {
        for (User user : users) {
            if (user.getUsername().equals(u)) {
                return true;
            }
        }
        Toast.makeText(this, "username does not match", Toast.LENGTH_SHORT).show();
        return false;
    }

    private boolean matchPassword(String p, List<User> users) {
        for (User user : users) {
            if (user.getPassword().equals(p)) {
                return true;
            }
        }
        Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();
        return false;
    }

    private boolean isUsernameEmpty(String u) {
        return u.length()==0;
    }

    private boolean isPasswordEmpty(String p) {
        return p.length()==0;
    }

    private boolean hasInput(String u, String p) {
        if (isUsernameEmpty(u) && isPasswordEmpty(p)){
            Toast.makeText(this, "Enter username and password", Toast.LENGTH_SHORT).show();
            return false;
        } else if (isUsernameEmpty(u)) {
            Toast.makeText(this, "Enter username", Toast.LENGTH_SHORT).show();
            return false;
        } else if (isPasswordEmpty(p)) {
            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}