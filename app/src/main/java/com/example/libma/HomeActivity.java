package com.example.libma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.libma.helpers.databaseHelper;
import com.example.libma.models.User;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        databaseHelper dbhelper = new databaseHelper(getApplicationContext());

        Button logout = (Button) findViewById(R.id.btnLogout);
        User user = (User) getIntent().getSerializableExtra("CURRENT_USER");

        logout.setOnClickListener(e->{
            dbhelper.updateUserLoginState(user.getUserId(), 0);
            Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginIntent);
            finish();
        });
    }
}