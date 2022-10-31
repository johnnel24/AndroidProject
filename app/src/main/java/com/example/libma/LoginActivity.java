package com.example.libma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.libma.models.*;
import com.example.libma.helpers.*;

public class LoginActivity extends AppCompatActivity {

    private EditText input_email;
    private EditText input_password;
    private TextView signUpInstead;
    private TextView forgotMyPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        TextView signUpInstead = findViewById(R.id.signup_txt_view);
        signUpInstead.setPaintFlags(signUpInstead.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        Button login = (Button) findViewById(R.id.btnLogin);
        databaseHelper dbhelper = new databaseHelper(getApplicationContext());

        signUpInstead = findViewById(R.id.signup_txt_view);
        forgotMyPassword = findViewById(R.id.forgot_txt_view);

        signUpInstead.setOnClickListener(e->{
            Intent registerActivity = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(registerActivity);
        });

        forgotMyPassword.setOnClickListener(e ->{
            Intent recoverActivity = new Intent(getApplicationContext(), RecoverActivity.class);
            startActivity(recoverActivity);
        });

        input_email = findViewById(R.id.email);
        input_password = findViewById(R.id.password);

        login.setOnClickListener(e -> {
            String email = input_email.getText().toString();
            String password = input_password.getText().toString();

            String emailValidation = validator.multiValidate(email, new String[] {"validateIsNotEmpty", "validateIsEmail"});

            if(!emailValidation.contains("valid")){
                input_email.setError(emailValidation);
                return;
            }

            if(!validator.validateNotEmpty(password)){
                input_password.setError("Wrong Password");
                return;
            }

            Cursor findUser = dbhelper.getUser(String.format("SELECT * FROM user WHERE email = '%s';", email), null);
            if(findUser == null || findUser.getCount() == 0) {
                input_email.setError("User Not Found!");
                return;
            }

            findUser.moveToNext();
            User user = new User(findUser.getInt(0), findUser.getString(1), findUser.getString(2), findUser.getString(3), findUser.getString(4),findUser.getInt(5), User.fromIoDateStringToDate(findUser.getString(7)));
            if(!authHelper.comparePassword(password, user.getPassword())){
                input_password.setError("Wrong Password!");
                return;
            }

            dbhelper.updateUserLoginState(user.getUserId(), 1);
            Intent homeIntent = new Intent(getApplicationContext(), HomeActivity.class);
            homeIntent.putExtra("CURRENT_USER", user);
            startActivity(homeIntent);
        });
    }
}