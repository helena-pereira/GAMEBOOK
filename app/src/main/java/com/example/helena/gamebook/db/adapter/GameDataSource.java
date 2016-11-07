package com.example.helena.gamebook.db.adapter;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.helena.gamebook.db.FeedReaderContract;
import com.example.helena.gamebook.db.SQLiteHelper;
import com.example.helena.gamebook.db.object.Game;
import com.example.helena.gamebook.db.object.Stade;
import com.example.helena.gamebook.db.adapter.StadeDataSource;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Helena on 05.11.2016.
 */

public class GameDataSource {

    private SQLiteDatabase db;
    private Context context;

    public GameDataSource(Context context){
        SQLiteHelper sqLiteHelper = SQLiteHelper.getInstance(context);
        db = sqLiteHelper.getWritableDatabase();
        this.context = context;
    }

    //INSERT GAME
    public long createGame(Game game){
        long id;

        ContentValues values = new ContentValues();

        values.put(FeedReaderContract.tableGAME.GAME_DATE, game.getDate());
        values.put(FeedReaderContract.tableGAME.GAME_HEURE, game.getHeure());
        values.put(FeedReaderContract.tableGAME.GAME_FK_STADE, game.getStade().getId());
        values.put(FeedReaderContract.tableGAME.GAME_TEAM_RESIDENT, game.getTeam_res());
        values.put(FeedReaderContract.tableGAME.GAME_TEAM_EXTERIEUR, game.getTeam_ext());
        values.put(FeedReaderContract.tableGAME.GAME_QUANTITE, game.getQuantity());
        values.put(FeedReaderContract.tableGAME.GAME_STATUT, game.getStatut());
        values.put(FeedReaderContract.tableGAME.GAME_NB_PLACES_DISPO, game.getNb_places_dispo());

        id = this.db.insert(FeedReaderContract.tableGAME.TABLE_NAME, null, values);

        return id;
    }

    //GET 1 GAME
    public Game getGameById(long id){
        String sql = "SELECT * FROM " + FeedReaderContract.tableGAME.CREATE_TABLE_GAME +
                " WHERE " + FeedReaderContract.tableGAME.GAME_ID + " = " + id ;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        Game game = new Game();
        game.setId(cursor.getInt(cursor.getColumnIndex(FeedReaderContract.tableGAME.GAME_ID)));
        game.setDate(cursor.getString(cursor.getColumnIndex(FeedReaderContract.tableGAME.GAME_DATE)));
        game.setHeure(cursor.getString(cursor.getColumnIndex(FeedReaderContract.tableGAME.GAME_HEURE)));
        Stade stade = StadeDateSource.getStadeById(cursor.getClass(cursor.getColumnIndex(FeedReaderContract.tableGAME.GAME_FK_STADE));
        game.setStade(stade);
        game.setTeam_res(cursor.getString(cursor.getColumnIndex(FeedReaderContract.tableGAME.GAME_TEAM_RESIDENT)));
        game.setTeam_ext(cursor.getString(cursor.getColumnIndex(FeedReaderContract.tableGAME.GAME_TEAM_EXTERIEUR)));
        game.setQuantity(cursor.getInt(cursor.getColumnIndex(FeedReaderContract.tableGAME.GAME_QUANTITE)));
        game.setStatut(cursor.getString(cursor.getColumnIndex(FeedReaderContract.tableGAME.GAME_STATUT)));
        game.setNb_places_dispo(cursor.getInt(cursor.getColumnIndex(FeedReaderContract.tableGAME.GAME_NB_PLACES_DISPO)));


        return game;

    }

    //GET ALL GAMES
    public List<Game> getAllGames(){
        List<Game> games = new ArrayList<Game>();
        String sql = "SELECT * FROM " + FeedReaderContract.tableGAME.TABLE_NAME + " ORDER BY " + FeedReaderContract.tableGAME.GAME_DATE ;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                Game game = new Game();
                game.setId(cursor.getInt(cursor.getColumnIndex(FeedReaderContract.tableGAME.GAME_ID)));
                game.setDate(cursor.getString(cursor.getColumnIndex(FeedReaderContract.tableGAME.GAME_DATE)));
                game.setHeure(cursor.getString(cursor.getColumnIndex(FeedReaderContract.tableGAME.GAME_HEURE)));
                game.setStade(cursor.getClass(cursor.getColumnIndex(FeedReaderContract.tableSTADE.STADE_ID)));
                game.setTeam_res(cursor.getString(cursor.getColumnIndex(FeedReaderContract.tableGAME.GAME_TEAM_RESIDENT)));
                game.setTeam_ext(cursor.getString(cursor.getColumnIndex(FeedReaderContract.tableGAME.GAME_TEAM_EXTERIEUR)));
                game.setQuantity(cursor.getInt(cursor.getColumnIndex(FeedReaderContract.tableGAME.GAME_QUANTITE)));
                game.setStatut(cursor.getString(cursor.getColumnIndex(FeedReaderContract.tableGAME.GAME_STATUT)));
                game.setNb_places_dispo(cursor.getInt(cursor.getColumnIndex(FeedReaderContract.tableGAME.GAME_NB_PLACES_DISPO)));

                games.add(game);
            }
            while(cursor.moveToNext());
        }
        return games;
    }


    //UPDATE GAME
    public int updateGame(Game game){
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.tableGAME.GAME_DATE, game.getDate());
        values.put(FeedReaderContract.tableGAME.GAME_HEURE, game.getHeure());
        values.put(FeedReaderContract.tableGAME.GAME_FK_STADE, game.getStade().getId());
        values.put(FeedReaderContract.tableGAME.GAME_TEAM_RESIDENT, game.getTeam_res());
        values.put(FeedReaderContract.tableGAME.GAME_TEAM_EXTERIEUR, game.getTeam_ext());
        values.put(FeedReaderContract.tableGAME.GAME_QUANTITE, game.getQuantity());
        values.put(FeedReaderContract.tableGAME.GAME_STATUT, game.getStatut());
        values.put(FeedReaderContract.tableGAME.GAME_NB_PLACES_DISPO, game.getNb_places_dispo());

        return this.db.update(FeedReaderContract.tableGAME.TABLE_NAME, values, FeedReaderContract.tableGAME.GAME_ID + " = ?",
                new String[]{String.valueOf(game.getId()) });

    }


    //DELETE A GAME
    public void deleteGame(long id){
        this.db.delete(FeedReaderContract.tableGAME.TABLE_NAME, FeedReaderContract.tableGAME.GAME_ID + " = ?",
                new String[] {String.valueOf(id)} );
    }






}
