package com.example.libma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        handler.postDelayed(() -> {
            Intent loginIntent = new Intent(getApplicationContext(), login.class);
            System.out.println("Started");
            startActivity(loginIntent);
            finish();
            System.out.println("Finished");
        }, 2000);
    }
}