package com.example.libma.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;

public class User implements Serializable {

    private int userId;
    private String email;
    private String fname;
    private String lname;
    private String password;
    private int role;
    private int isLoggedIn;
    private Date dateJoined;

    public User(int userId, String email, String fname, String lname, String password, int role, int isLoggedIn, Date dateJoined) {
        this.userId = userId;
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.role = role;
        this.isLoggedIn = isLoggedIn;
        this.dateJoined = dateJoined;
    }

    public String getEmail() {
        return email;
    }

    public int getUserId() {
        return userId;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getPassword () { return password; }

    public int getRole() {
        return role;
    }

    public int getIsLoggedIn() {
        return isLoggedIn;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public static String toISODateString(Date date) {
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        sdf.setTimeZone(TimeZone.getTimeZone("CET"));
        String ISO = sdf.format(date);
        System.out.println(ISO);
        return ISO;
    }

    public static Date fromIoDateStringToDate(String ISODateString){ return Date.from(ZonedDateTime.parse(ISODateString).toInstant()); }
}
