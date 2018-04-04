package com.demo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shabana Khatri on 02-04-2018.
 */

public class DbHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "users_db";

    private Context context;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create Table
        sqLiteDatabase.execSQL(Users.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Drop table if exists
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Users.TABLE_NAME);

        // Create tables again
        onCreate(sqLiteDatabase);
    }

    public boolean insert(Users users) {
        SQLiteDatabase db;
        String[] columns = {
                Users.COLUMN_ID
        };

        db = this.getReadableDatabase();

        // selection criteria
        String selection = Users.COLUMN_EMAIL + " = ?";

        // selection arguments
        String[] selectionArgs = {users.getEmail()};

        // query user table with conditions

        Cursor cursor = db.query(Users.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return false;
        } else {

            // get writable database as we want to write data
            db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(Users.COLUMN_NAME, users.getName());
            values.put(Users.COLUMN_EMAIL, users.getEmail());
            values.put(Users.COLUMN_CONTACT_NO, users.getContact_no());
            values.put(Users.COLUMN_PASSWORD, users.getPassword());
            values.put(Users.COLUMN_PROFILE_PIC_PATH, users.getProfilePic());
            // insert row
            long id = db.insertWithOnConflict(Users.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);

            // close db connection
            db.close();

            // return newly inserted row id
            return true;
        }
    }

    public List<Users> getAllUsers() {
        List<Users> usersList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + Users.TABLE_NAME + " ORDER BY " + Users.COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Users user = new Users();
                    user.setId(cursor.getInt(cursor.getColumnIndex(Users.COLUMN_ID)));
                    user.setName(cursor.getString(cursor.getColumnIndex(Users.COLUMN_NAME)));
                    user.setEmail(cursor.getString(cursor.getColumnIndex(Users.COLUMN_EMAIL)));
                    user.setContact_no(cursor.getString(cursor.getColumnIndex(Users.COLUMN_CONTACT_NO)));
                    user.setPassword(cursor.getString(cursor.getColumnIndex(Users.COLUMN_PASSWORD)));
                    user.setProfilePic(cursor.getString(cursor.getColumnIndex(Users.COLUMN_PROFILE_PIC_PATH)));
                    usersList.add(user);
                } while (cursor.moveToNext());
            }
        }

        // close db connection
        db.close();
        return usersList;
    }

    public boolean getUser(String email, String password) {
        // array of columns to fetch
        String[] columns = {
                Users.COLUMN_ID
        };

        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = Users.COLUMN_EMAIL + " = ?" + " AND " + Users.COLUMN_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions

        Cursor cursor = db.query(Users.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

}
