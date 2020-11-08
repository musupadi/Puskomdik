package com.destinyapp.puskomdik.SharedPreferance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Helper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "puskomdik.db";
    private static final int DATABASE_VERSION = 1;
    //Account
    public static final String TABLE_NAME_ACCOUNT = "account";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TOKEN = "token";
    public static final String COLUMN_LEVEL = "level";
    public static final String COLUMN_PROFILE = "profile";

    public DB_Helper(Context context){super(
            context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME_ACCOUNT+" (" +
                COLUMN_USERNAME+" TEXT NOT NULL, "+
                COLUMN_PASSWORD+" TEXT NOT NULL, "+
                COLUMN_NAME+" TEXT NOT NULL, "+
                COLUMN_TOKEN+" TEXT NOT NULL, "+
                COLUMN_LEVEL+" TEXT NOT NULL, "+
                COLUMN_PROFILE+" TEXT NOT NULL);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ACCOUNT);
        this.onCreate(db);
    }
    //Save
    public void SaveUser(String username, String password, String name, String token, String level, String profile){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_TOKEN, token);
        values.put(COLUMN_LEVEL,level);
        values.put(COLUMN_PROFILE,profile);
        db.insert(TABLE_NAME_ACCOUNT,null,values);
        db.close();
    }
    //CHECKER
    public Cursor checkUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT * FROM "+TABLE_NAME_ACCOUNT;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
    //delete
    public void Logout(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME_ACCOUNT+"");
    }
}
