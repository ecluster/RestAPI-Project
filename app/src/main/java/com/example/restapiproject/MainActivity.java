package com.example.restapiproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUsername;
    EditText etPassword;
    View btnLogin;

    List<User> users;

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson = new GsonBuilder().serializeNulls().create();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @NonNull
                    @Override
                    public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
                        Request originalRequest = chain.request();

                        Request newRequest = originalRequest.newBuilder()
                                .header("Interceptor-Header", "xyz")
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        users = new ArrayList<>();

        users.add(new User("Bret", "p123", "1"));
        users.add(new User("Antonette", "p321", "2"));
        users.add(new User("Samantha", "p468", "3"));

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin) {
            etUsername = findViewById(R.id.etUsername);
            etPassword = findViewById(R.id.etPassword);

            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            String allPosts;

            if (hasInput(username, password)) {
                // check if user exist
                if (userExist(username, users)) {
                    // check if password is right
                    if (matchPassword(password, users)) {
                        User user1 = getUser(username, users);
                        Intent i = new Intent(this, LandingActivity.class);
                        Bundle b = new Bundle();
                        b.putString("key", user1.getUserId());
                        i.putExtras(b);
                        startActivity(i);
                    }
                }
            }
        }
    }

    private User getUser(String u, List<User> users) {
        for (User user : users) {
            if (user.getUsername().equals(u)) {
                return user;
            }
        }
        return null;
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