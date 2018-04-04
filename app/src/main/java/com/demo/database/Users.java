package com.demo.database;

import android.graphics.Bitmap;

/**
 * Created by Shabana Khatri on 02-04-2018.
 */

public class Users {

    public static final String TABLE_NAME = "user";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_CONTACT_NO = "contact_no";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_PROFILE_PIC_PATH = "profile_pic_path";


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_PROFILE_PIC_PATH + " BLOB,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_EMAIL + " TEXT,"
                    + COLUMN_CONTACT_NO + " TEXT,"
                    + COLUMN_PASSWORD + " TEXT"
                    + ")";

    public Users() {
    }

    public Users(int id, String name, String email, String contact_no, String password, String profilePic) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact_no = contact_no;
        this.password = password;
        this.profilePic = profilePic;
    }


    private int id;
    private String name;
    private String email;
    private String contact_no;
    private String password;
    private String profilePic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}
