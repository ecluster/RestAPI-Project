package com.example.restapiproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText etUsername;
    EditText etPassword;
    View btnLogin;
    TextView tvError;

    List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        users = new ArrayList<>();

        users.add(new User("Bret", "p123", "1"));
        users.add(new User("Antonette", "p321", "2"));
        users.add(new User("Samantha", "p468", "3"));

    }

    public void nextActivity(View v) {
        if (v.getId() == R.id.btnLogin) {
            etUsername = findViewById(R.id.etUsername);
            etPassword = findViewById(R.id.etPassword);
            tvError = findViewById(R.id.tvError);

            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            String allPosts;

            if (hasInput(username, password)) {
                if (userExist(username, users) && matchPassword(password, users)) {
                    tvError.setBackgroundColor(Color.WHITE);
                    tvError.setText("");
                    etUsername.setBackgroundColor(Color.GREEN);
                    etPassword.setBackgroundColor(Color.GREEN);
                    User user1 = getUser(username, users);
                    Intent i = IntentFactory.getIntent("LandingActivity", getApplicationContext(), user1.getUserId(), user1.getUsername());
                    startActivity(i);
                } else if (userExist(username, users) && !matchPassword(password, users)) {
                    etUsername.setBackgroundColor(Color.GREEN);
                    etPassword.setBackgroundColor(Color.RED);
                    tvError.setBackgroundColor(Color.rgb(0, 162, 255));
                    tvError.setText("Wrong Password");
                } else if (!userExist(username, users) && !isPasswordEmpty(password)) {
                    tvError.setBackgroundColor(Color.rgb(0, 162, 255));
                    tvError.setText("Username does not exist");
                    etUsername.setBackgroundColor(Color.RED);
                    etPassword.setBackgroundColor(Color.GREEN);
                } else if (isUsernameEmpty(username) && !isPasswordEmpty(password) ) {
                    tvError.setBackgroundColor(Color.rgb(0, 162, 255));
                    tvError.setText("Username is empty");
                    etUsername.setBackgroundColor(Color.RED);
                    etPassword.setBackgroundColor(Color.GREEN);
                }
            } else {
                etUsername.setBackgroundColor(Color.RED);
                etPassword.setBackgroundColor(Color.RED);
            }
        }
    }

    public static User getUser(String u, List<User> users) {
        for (User user : users) {
            if (user.getUsername().equals(u)) {
                return user;
            }
        }
        return null;
    }


    public static boolean userExist(String u, List<User> users) {
        for (User user : users) {
            if (user.getUsername().equals(u)) {
                return true;
            }
        }
        //Toast.makeText(this, "username does not match", Toast.LENGTH_SHORT).show();
        return false;
    }

    public static boolean matchPassword(String p, List<User> users) {
        for (User user : users) {
            if (user.getPassword().equals(p)) {
                return true;
            }
        }
        // Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();
        return false;
    }

    public static boolean isUsernameEmpty(String u) {
        return u.length()==0;
    }

    public static boolean isPasswordEmpty(String p) {
        return p.length()==0;
    }

    public static boolean hasInput(String u, String p) {

        if (isUsernameEmpty(u) && isPasswordEmpty(p)){
            //Toast.makeText(this, "Enter username and password", Toast.LENGTH_SHORT).show();
            return false;
        } else if (isUsernameEmpty(u) || isPasswordEmpty(p)) {
            return true;
        } else if (isUsernameEmpty(u)) {
            //Toast.makeText(this, "Enter username", Toast.LENGTH_SHORT).show();
            return false;
        } else if (isPasswordEmpty(p)) {
            //Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}