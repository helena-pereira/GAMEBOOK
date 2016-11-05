package com.example.helena.gamebook.db.adapter;

import java.util.ArrayList;
import java.util.List;

import android.database.sqlite.SQLiteDatabase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.helena.gamebook.db.FeedReaderContract;
import com.example.helena.gamebook.db.SQLiteHelper;
import com.example.helena.gamebook.db.object.Game;
import com.example.helena.gamebook.db.object.Stade;
import com.example.helena.gamebook.db.FeedReaderContract;


import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Helena on 05.11.2016.
 */

public class StadeDataSource {
    private SQLiteDatabase db;
    private Context context;

    public StadeDataSource(Context context){
        SQLiteHelper sqliteHelper = SQLiteHelper.getInstance(context);
        db = sqliteHelper.getWritableDatabase();
        this.context = context;
    }

    //nous n'allons pas créer des stades directement depuis l'application
    //mais je mets tout de même le code
    //INSERT STADIUM
    public long createStade(Stade stade){
        long id;
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.tableSTADE.STADE_NOM, stade.getNom());
        values.put(FeedReaderContract.tableSTADE.STADE_NB_PLACES_TOTALES, stade.getNb_places_totales());
        values.put(FeedReaderContract.tableSTADE.STADE_ADRESSE, stade.getAdresse());
        values.put(FeedReaderContract.tableSTADE.STADE_NPA, stade.getNpa());
        values.put(FeedReaderContract.tableSTADE.STADE_VILLE, stade.getVille());

        id = this.db.insert(FeedReaderContract.tableSTADE.TABLE_NAME, null, values);

        return id;
    }

}
