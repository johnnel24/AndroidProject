package com.example.libma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;

import com.example.libma.helpers.databaseHelper;

public class MainActivity extends AppCompatActivity {

    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        databaseHelper dbhelper = new databaseHelper(getApplicationContext());
        dbhelper.checkTableExist(new String[]{"user", "book", "booklist", "recovery"});

        Cursor result = dbhelper.getUser("select * from user where isLoggedIn=1", null);

        if (result.getCount() > 0)
            handler.postDelayed(() -> {
                Intent loginIntent = new Intent(getApplicationContext(), home.class);
                startActivity(loginIntent);
                finish();
            }, 2000);
        else
            handler.postDelayed(() -> {
                Intent loginIntent = new Intent(getApplicationContext(), login.class);
                //dbhelper.insertUser("jsparagas1@gmail.com", "Jerbee", "Paragas", 1, 0);
                startActivity(loginIntent);
                finish();
            }, 2000);
    }
}