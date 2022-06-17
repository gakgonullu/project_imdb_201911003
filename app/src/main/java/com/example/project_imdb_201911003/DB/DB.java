package com.example.project_imdb_201911003.DB;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {

    private final static String databaseName = "FilmList";
    private final static int databaseVersion = 1;
    private String FILM_TABLE = "saved_film";
    private static DB dbInstance = null;

    private DB(Context context)
    {
        super(context, databaseName, null, databaseVersion);
    }

    public synchronized static DB getInstance(Context context){
        if(dbInstance==null){
            dbInstance=new DB(context.getApplicationContext());
        }
        return dbInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE " + FILM_TABLE + " ("
                + " imdbID STRING PRIMARY KEY ,"
                + " filmName TEXT,"
                + " filmYear TEXT,"
                + " image TEXT,"
                + " awards TEXT,"
                + " writers TEXT,"
                + " director TEXT,"
                + " plot TEXT,"
                + " genre TEXT"
                + " )";
        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
