package com.example.helena.gamebook.db;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.KeyCharacterMap.KeyData;

/**
 * Created by Helena on 01.11.2016.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db ;

    //infos de la db
    private static final String DATABASE_NAME = "gamebook.db" ;
    private static final int DATABASE_VERSION = 1 ;
    private static SQLiteHelper instance ;

    //avec singleton pour instantier qu'une fois la base de données
    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
        db = this.getWritableDatabase();

    }

    public static SQLiteHelper getInstance(Context context){
        if(instance == null){
            instance = new SQLiteHelper(context.getApplicationContext()) ;
            //enable foreign key suppor
            instance.db.execSQL("PRAGMA foreign_key = ON;");
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FeedReaderContract.tableGAME.CREATE_TABLE_GAME);
        db.execSQL(FeedReaderContract.tableCUSTOMER.CREATE_TABLE_CUSTOMER);
        db.execSQL(FeedReaderContract.tableSTADE.CREATE_TABLE_STADE);
        db.execSQL(FeedReaderContract.tableBOOKING.CREATE_TABLE_BOOKING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop old tables
        db.execSQL("DROP TABLE IF EXISTS " + FeedReaderContract.tableGAME.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FeedReaderContract.tableCUSTOMER.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FeedReaderContract.tableSTADE.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FeedReaderContract.tableBOOKING.TABLE_NAME);

        //create new tables
        onCreate(db);
    }
}
