package com.example.westagile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "users.db";
    public static final String TABLE_USER = "UserDetail";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_EMAILID = "email_id";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_USER + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_NAME + " TEXT ," +
                COLUMN_EMAILID + " TEXT ," +
                COLUMN_GENDER + " TEXT " +
                ");";
        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    //Add new row to database
    public void addUser(User user){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.get_name());
        values.put(COLUMN_GENDER, user.get_gender());
        values.put(COLUMN_EMAILID, user.get_email_id());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    //Delete product from the database
    public void deleteUser(String Name){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_USER + " WHERE " + COLUMN_NAME + "=\"" + Name + "\";");
    }

   /* public void deleteTable(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE " + TABLE_USER);
    }*/

    //Print out the database as a string
    public ArrayList<User> databaseToString(){
        SQLiteDatabase db =  getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_USER;

        //Cursor point to a location in your result
        Cursor c = db.rawQuery(query, null);
        //Move to first row in your result

        ArrayList<User> u = new ArrayList<>();

        c.moveToFirst();
        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_NAME)) != null) {
                User user = new User();
                user.set_name(c.getString(c.getColumnIndex(COLUMN_NAME)));
                user.set_email_id(c.getString(c.getColumnIndex(COLUMN_EMAILID)));
                user.set_gender(c.getString(c.getColumnIndex(COLUMN_GENDER)));
                user.set_name(c.getString(c.getColumnIndex(COLUMN_NAME)));
                u.add(user);
            }
            c.moveToNext();
        }
        db.close();
        return u;
    }
}