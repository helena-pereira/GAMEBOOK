package com.example.helena.gamebook.db.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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

    //GET 1 STADE

    public Stade getStadeById(long id){
        String sql = "SELECT * FROM " + FeedReaderContract.tableSTADE.TABLE_NAME +
                " WHERE " + FeedReaderContract.tableSTADE.STADE_ID + " = " + id;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        Stade stade = new Stade();
        stade.setId(cursor.getInt(cursor.getColumnIndex(FeedReaderContract.tableSTADE.STADE_ID)));
        stade.setNom(cursor.getString(cursor.getColumnIndex(FeedReaderContract.tableSTADE.STADE_NOM)));
        stade.setNb_places_totales(cursor.getInt(cursor.getColumnIndex(FeedReaderContract.tableSTADE.STADE_NB_PLACES_TOTALES)));
        stade.setAdresse(cursor.getString(cursor.getColumnIndex(FeedReaderContract.tableSTADE.STADE_ADRESSE)));
        stade.setNpa(cursor.getString(cursor.getColumnIndex(FeedReaderContract.tableSTADE.STADE_NPA)));
        stade.setVille(cursor.getString(cursor.getColumnIndex(FeedReaderContract.tableSTADE.STADE_VILLE)));

        return stade;

    }

    //GET ALL STADES
    //normalement on aura pas besoin de cette méthode, mais je la laisse dans un but évolutif
    public List<Stade> getAllStades(){
        List<Stade> stades = new ArrayList<Stade>();
        String sql = "SELECT * FROM " + FeedReaderContract.tableSTADE.TABLE_NAME + " ORDER BY " +
                FeedReaderContract.tableSTADE.STADE_NOM;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                Stade stade = new Stade();
                stade.setId(cursor.getInt(cursor.getColumnIndex(FeedReaderContract.tableSTADE.STADE_ID)));
                stade.setNom(cursor.getString(cursor.getColumnIndex(FeedReaderContract.tableSTADE.STADE_NOM)));
                stade.setNb_places_totales(cursor.getInt(cursor.getColumnIndex(FeedReaderContract.tableSTADE.STADE_NB_PLACES_TOTALES)));
                stade.setAdresse(cursor.getString(cursor.getColumnIndex(FeedReaderContract.tableSTADE.STADE_ADRESSE)));
                stade.setNpa(cursor.getString(cursor.getColumnIndex(FeedReaderContract.tableSTADE.STADE_NPA)));
                stade.setVille(cursor.getString(cursor.getColumnIndex(FeedReaderContract.tableSTADE.STADE_VILLE)));

                stades.add(stade);
            }
            while (cursor.moveToNext());
        }

        return stades;
    }

    //UPDATE STADE
    //normalement on aura pas besoin de cette méthode, mais je la laisse dans un but évolutif
    public int updateStade(Stade stade){
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.tableSTADE.STADE_NOM, stade.getNom());
        values.put(FeedReaderContract.tableSTADE.STADE_NB_PLACES_TOTALES, stade.getNb_places_totales());
        values.put(FeedReaderContract.tableSTADE.STADE_ADRESSE, stade.getAdresse());
        values.put(FeedReaderContract.tableSTADE.STADE_NPA, stade.getNpa());
        values.put(FeedReaderContract.tableSTADE.STADE_VILLE, stade.getVille());

        return this.db.update(FeedReaderContract.tableSTADE.TABLE_NAME, values, FeedReaderContract.tableSTADE.STADE_ID + " = ?",
                new String[]{String.valueOf(stade.getId())});
    }

    //DELETE STADE
    //normalement on aura pas besoin de cette méthode, mais je la laisse dans un but évolutif
    public void deleteStade(long id){

        this.db.delete(FeedReaderContract.tableSTADE.TABLE_NAME, FeedReaderContract.tableSTADE.STADE_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

}
