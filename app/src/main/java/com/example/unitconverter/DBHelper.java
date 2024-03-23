package com.example.unitconverter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//import com.example.healthcare.Model.DoctorModel;
//import com.example.healthcare.Model.UserModel;
import com.example.unitconverter.Model.UserModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "UNITCONVERTER";
    private static final int DB_VERSION = 3;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE USERS(id INTEGER PRIMARY KEY, Name TEXT, Email TEXT, Phone TEXT, Password TEXT)";
        String createUnitConversions = "CREATE TABLE CONVERSIONS(id INTEGER PRIMARY KEY, userInput TEXT, km TEXT, m TEXT, cm TEXT, mm TEXT, nm TEXT , mile TEXT, yard TEXT, foot TEXT, inch TEXT)";
        db.execSQL(createUserTable);
        db.execSQL(createUnitConversions);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS USERS");
        db.execSQL("DROP TABLE IF EXISTS CONVERSIONS");
        onCreate(db);
    }

    public boolean registerUser(String name, String email, String phone, String pass) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("Email", email);
        contentValues.put("Phone", phone);
        contentValues.put("Password", pass);
        Long l = sqLiteDatabase.insert("USERS", null, contentValues);
        sqLiteDatabase.close();
        if (l > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean loginUser(String email, String pass) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM USERS WHERE Email='" + email + "' AND Password='" + pass + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            // User is LoggedIn
            return true;

        } else {
            // User is  Not LoggedIn
            return false;
        }
    }


    public Cursor displayUserData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT * FROM USERS";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }


    public Cursor displayConversionsData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT * FROM CONVERSIONS";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }



    public ArrayList<UserModel> getLoggedInUserDetails(String email) {
        ArrayList<UserModel> userModels = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM USERS WHERE Email='" + email + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            String name = cursor.getString(1);
            String user_email = cursor.getString(2);
            String phone = cursor.getString(3);
            userModels.add(new UserModel(name, user_email, phone));
        }
        return userModels;
    }


    public boolean addDataToHistory(String userInput, String km, String m, String cm, String mm, String nm, String mile, String yard, String foot, String inch) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userInput", userInput);
        contentValues.put("km", km);
        contentValues.put("m", m);
        contentValues.put("cm", cm);
        contentValues.put("mm", mm);
        contentValues.put("nm", nm);
        contentValues.put("mile", mile);
        contentValues.put("yard", yard);
        contentValues.put("foot", foot);
        contentValues.put("inch", inch);
        Long l = sqLiteDatabase.insert("CONVERSIONS", null, contentValues);
        sqLiteDatabase.close();
        if (l > 0) {
            return true;
        } else {
            return false;
        }
    }



    public boolean updateUserDetails(String name, String email, String phone) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name", name);
        values.put("Email", email);
        values.put("Phone", phone);
        String selection = "email = ?";
        String[] selectionArgs = {email};
        int count = db.update("USERS", values, selection, selectionArgs);
        db.close();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteHistory(String userInput) {
        SQLiteDatabase db = getWritableDatabase();
        long l = db.delete("CONVERSIONS", "userInput=?", new String[]{String.valueOf(userInput)});
        db.close();
        if (l > 0) {
            return true;
        } else {
            return false;
        }
    }
}

