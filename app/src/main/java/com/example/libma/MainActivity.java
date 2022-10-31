package com.example.libma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;

import com.example.libma.helpers.*;
import com.example.libma.models.User;


public class MainActivity extends AppCompatActivity {

    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        databaseHelper dbhelper = new databaseHelper(getApplicationContext());

//        dbhelper.dropDbs(dbhelper.getWritableDatabase(), new String[]{"user"});
        dbhelper.checkTableExist();
//        dbhelper.insertUser("jsparagas1@gmail.com", "Jerbee", "Paragas", authHelper.hashPassword("helloworld"), 0, 0, new Date());

        Cursor result = dbhelper.getUser("select * from user where isLoggedIn=1", null);
        if (result != null && result.getCount() > 0)
            handler.postDelayed(() -> {
                Intent homeIntent = new Intent(getApplicationContext(), HomeActivity.class);
                result.moveToNext();
                User user = new User(result.getInt(0), result.getString(1), result.getString(2), result.getString(3), result.getString(4),result.getInt(5), User.fromIoDateStringToDate(result.getString(7)));
                homeIntent.putExtra("CURRENT_USER", user);
                startActivity(homeIntent);
                finish();
            }, 2000);
        else
            handler.postDelayed(() -> {
                Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }, 2000);
    }
}