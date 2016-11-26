package com.example.helena.gamebook.db;

import android.provider.BaseColumns;

/**
 * Created by Helena on 28.10.2016.
 */

public class FeedReaderContract {
    public static final String TEXT_TYPE = " TEXT";
    public static final String COMMA_SEP = ", ";

    public FeedReaderContract() {
    }

    // GAME
    public static abstract class tableGAME implements BaseColumns {

        public static final String TABLE_NAME = "GAME";

        public static final String GAME_ID = "_id";
        public static final String GAME_DATE = "date";
        public static final String GAME_HEURE = "heure";
        public static final String GAME_TEAM_RESIDENT = "team_resident";
        public static final String GAME_TEAM_EXTERIEUR = "team_exterieur";
        public static final String GAME_QUANTITE = "quantite";
        public static final String GAME_STATUT = "statut";
        public static final String GAME_NB_PLACES_DISPO = "nb_places dispos";
        public static final String GAME_STADE = "fk_stade";


        public static final String CREATE_TABLE_GAME = "CREATE TABLE " +
                tableGAME.TABLE_NAME + " (" +
                tableGAME.GAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                tableGAME.GAME_DATE + TEXT_TYPE + COMMA_SEP +
                tableGAME.GAME_HEURE + " DATETIME" + COMMA_SEP +
                tableGAME.GAME_TEAM_RESIDENT + TEXT_TYPE + COMMA_SEP +
                tableGAME.GAME_TEAM_EXTERIEUR + TEXT_TYPE + COMMA_SEP +
                tableGAME.GAME_QUANTITE + " INTEGER" + COMMA_SEP +
                tableGAME.GAME_STATUT + TEXT_TYPE + COMMA_SEP +
                tableGAME.GAME_NB_PLACES_DISPO + " INTEGER"

              /*  tableGAME.GAME_FK_STADE + " INTEGER,"
                + " FOREIGN KEY (" + tableGAME.GAME_FK_STADE + ") REFERENCES " + tableSTADE.TABLE_NAME + " (" + tableSTADE.STADE_ID + ") "
                + ");"; */

                + ");";

    }

    // BOOKING
    public static abstract class tableBOOKING implements BaseColumns {
        public static final String TABLE_NAME = "BOOKING";
        public static final String BOOKING_ID = "_id";
        public static final String BOOKING_FK_GAME = "fk_game";
        public static final String BOOKING_FK_CUSTOMER = "fk_customer";
        public static final String BOOKING_NUM_SEAT = "num_seat";

        public static final String CREATE_TABLE_BOOKING = "CREATE TABLE " +
                tableBOOKING.TABLE_NAME + " (" +
                tableBOOKING.BOOKING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                tableBOOKING.BOOKING_NUM_SEAT + " INTEGER" + COMMA_SEP +
                tableBOOKING.BOOKING_FK_GAME + " INTEGER, " +
                tableBOOKING.BOOKING_FK_CUSTOMER + " INTEGER, "
                + " FOREIGN KEY (" + tableBOOKING.BOOKING_FK_GAME + ") REFERENCES " + tableGAME.TABLE_NAME + " (" + tableGAME.GAME_ID + "), "
                + " FOREIGN KEY (" + tableBOOKING.BOOKING_FK_CUSTOMER + ") REFERENCES " + tableCUSTOMER.TABLE_NAME + " (" + tableCUSTOMER.CUSTOMER_ID + ")"
                + ");";
    }

    // CUSTOMER
    public static abstract class tableCUSTOMER implements BaseColumns {
        public static final String TABLE_NAME = "CUSTOMER";
        public static final String CUSTOMER_ID = "_id";
        public static final String CUSTOMER_NOM = "nom";
        public static final String CUSTOMER_PRENOM = "prenom";
        public static final String CUSTOMER_EMAIL = "email";
        public static final String CUSTOMER_MDP = "mdp";


        public static final String CREATE_TABLE_CUSTOMER = "CREATE TABLE " +
                tableCUSTOMER.TABLE_NAME + " (" +
                tableCUSTOMER.CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                tableCUSTOMER.CUSTOMER_NOM + TEXT_TYPE + COMMA_SEP +
                tableCUSTOMER.CUSTOMER_PRENOM + TEXT_TYPE + COMMA_SEP +
                tableCUSTOMER.CUSTOMER_EMAIL + TEXT_TYPE + COMMA_SEP +
                tableCUSTOMER.CUSTOMER_MDP + TEXT_TYPE +
                ")" ;


    }

    // STADE
    public static abstract class tableSTADE implements BaseColumns {
        public static final String TABLE_NAME = "STADE";
        public static final String STADE_ID = "_id";
        public static final String STADE_NOM = "nom";
        public static final String STADE_NB_PLACES_TOTALES = "nb_places_totales";
        public static final String STADE_ADRESSE = "adresse";
        public static final String STADE_NPA = "npa";
        public static final String STADE_VILLE = "ville";

        public static final String CREATE_TABLE_STADE = "CREATE TABLE " +
                tableSTADE.TABLE_NAME + " (" +
                tableSTADE.STADE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                tableSTADE.STADE_NOM + TEXT_TYPE + COMMA_SEP +
                tableSTADE.STADE_NB_PLACES_TOTALES + " INTEGER" + COMMA_SEP +
                tableSTADE.STADE_ADRESSE + TEXT_TYPE + COMMA_SEP +
                tableSTADE.STADE_NPA + TEXT_TYPE + COMMA_SEP +
                tableSTADE.STADE_VILLE + TEXT_TYPE  +
                ")";
    }


    //création des FK_id
    //https://github.com/codepath/android_guides/wiki/Local-Databases-with-SQLiteOpenHelper

    //création table GAME











}





