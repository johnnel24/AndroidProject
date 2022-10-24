package com.example.libma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.libma.models.User;

import java.util.Date;
import com.example.libma.helpers.databaseHelper;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        Button login = (Button) findViewById(R.id.btnLogin);
        databaseHelper dbhelper = new databaseHelper(getApplicationContext());

        login.setOnClickListener(e -> {
            String email = ((EditText) findViewById(R.id.email)).getText().toString();
            String password = ((EditText) findViewById(R.id.password)).getText().toString();
            User user = new User(1, "jsparagas@gmail.com", "Jerbee", "Paragas", "helloworld",0, 1, new Date());
            dbhelper.updateUserLoginState(user.getUserId(), user.getIsLoggedIn());

            Intent homeIntent = new Intent(getApplicationContext(), home.class);
            homeIntent.putExtra("CURRENT_USER", user);
            startActivity(homeIntent);
        });
    }
}