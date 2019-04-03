package com.example.westagile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "student.db";
    public static final String TABLE_USER = "StudentDetails";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_EMAILID = "email_id";
    public static final String COLUMN_DOB = "dob";
    public static final String COLUMN_MOBILENO = "mobile_no";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_USER + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_NAME + " TEXT ," +
                COLUMN_EMAILID + " TEXT ," +
                COLUMN_GENDER + " TEXT ," +
                COLUMN_DOB + " TEXT ," +
                COLUMN_MOBILENO + " TEXT " +
                ");";
        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    //Add new row to database
    public void addUser(StudentDetails studentDetails){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, studentDetails.get_name());
        values.put(COLUMN_GENDER, studentDetails.get_gender());
        values.put(COLUMN_EMAILID, studentDetails.get_email_id());
        values.put(COLUMN_DOB, studentDetails.get_dob());
        values.put(COLUMN_MOBILENO, studentDetails.get_mobile_no());
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
    public ArrayList<StudentDetails> databaseToString(){
        SQLiteDatabase db =  getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_USER;

        //Cursor point to a location in your result
        Cursor c = db.rawQuery(query, null);
        //Move to first row in your result

        ArrayList<StudentDetails> u = new ArrayList<>();

        c.moveToFirst();
        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_NAME)) != null) {
                StudentDetails studentDetails = new StudentDetails();
                studentDetails.set_id(c.getInt(c.getColumnIndex(COLUMN_ID)));
                studentDetails.set_name(c.getString(c.getColumnIndex(COLUMN_NAME)));
                studentDetails.set_email_id(c.getString(c.getColumnIndex(COLUMN_EMAILID)));
                studentDetails.set_gender(c.getString(c.getColumnIndex(COLUMN_GENDER)));
                studentDetails.set_dob(c.getString(c.getColumnIndex(COLUMN_DOB)));
                studentDetails.set_mobile_no(c.getString(c.getColumnIndex(COLUMN_MOBILENO)));
                u.add(studentDetails);
            }
            c.moveToNext();
        }
        db.close();
        return u;
    }
}