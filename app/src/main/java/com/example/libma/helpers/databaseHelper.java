package com.example.libma.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class databaseHelper extends SQLiteOpenHelper {

    public databaseHelper(Context context) {
        super(context, "LibMa.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void checkTableExist(String[] tables) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (String target : tables) {
            String queryCreate = "CREATE TABLE IF NOT EXISTS user (" +
                    "userId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "email TEXT, " +
                    "fname TEXT, " +
                    "lname TEXT, " +
                    "role TEXT, " +
                    "isLoggedIn INTEGER, " +
                    "datejoined TEXT);";
            db.execSQL(queryCreate);
        }
    }

    public boolean dropDb(SQLiteDatabase db, String dbName) {
        db.execSQL(String.format("drop Table if exists %s", dbName));
        return true;
    }

    public Boolean insertUser(String email, String fname, String lname, int isLoggedIn, int role) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put("email", email);
            values.put("fname", fname);
            values.put("lname", lname);
            values.put("role", role);
            values.put("isLoggedIn", isLoggedIn);
            values.put("dateJoined", formatDateTime(new Date()));

            db.insert("user", null, values);
            db.close();
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public void updateUserLoginState(int userId, int toState) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(String.format("update user set isLoggedIn = %d where userId = %d", userId, toState));
    }

    public Cursor getUser(String query, String[] args) {
        Cursor result = this.getWritableDatabase().rawQuery(query, args);
        return result;
    }

    public static String formatDateTime(Date date) {
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        sdf.setTimeZone(TimeZone.getTimeZone("CET"));
        String ISO = sdf.format(date);
        System.out.println(ISO);
        return ISO;
    }
}
