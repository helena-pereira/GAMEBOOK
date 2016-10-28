package com.example.helena.gamebook.db;

import android.provider.BaseColumns;

/**
 * Created by Helena on 28.10.2016.
 */

public class FeedReaderContract {

    public FeedReaderContract(){}

    //table GAME
    public static abstract class FeedEntryGAME implements BaseColumns {
        public static final String TABLE_NAME = "GAME";
        public static final String COLUMN_DATE ="date";
        public static final String COLUMM_HEURE = "heure";
        public static final String COLUMN_ATTRIBUTE1 ="attribute1";
        public static final String COLUMN_TEAM_RESIDENT ="team_resident";
        public static final String COLUMN_TEAM_EXTERIEUR ="team_exterieur";
        public static final String COLUMN_QUANTITE ="quantite";
        public static final String COLUMN_STATUT ="statut";
        public static final String COLUMN_NB_PLACES_DISPO ="nb_places dispos";
        public static final String COLUMN_FK_STADE ="fk_stade";
    }

    //table BOOKING
    public static abstract class FeedEntryBOOKING implements BaseColumns {
        public static final String TABLE_NAME = "BOOKING";
        public static final String COLUMN_FK_IDGAME ="fk_idGame";
        public static final String COLUMM_FK_IDCUSTOMER = "fk_idCustomer";
        public static final String COLUMN_NUM_SEAT ="num_seat";
    }

    //table CUSTOMER
    public static abstract class FeedEntryCUSTOMER implements BaseColumns {
        public static final String TABLE_NAME = "CUSTOMER";
        public static final String COLUMN_NOM ="nom";
        public static final String COLUMM_PRENOM = "prenom";
        public static final String COLUMN_EMAIL ="email";
        public static final String COLUMN_MDP ="mdp";
        public static final String COLUMN_FK_IDLIEU ="fk_idLieu";

    }

    //table STADE
    public static abstract class FeedEntrySTADE implements BaseColumns {
        public static final String TABLE_NAME = "STADE";
        public static final String COLUMN_NOM ="nom";
        public static final String COLUMM_NB_PLACES_TOTALES = "nb_places_totales";
        public static final String COLUMN_ADRESSE ="adresse";
        public static final String COLUMN_NPA ="npa";
        public static final String COLUMN_VILLE ="ville";
        public static final String COLUMN_FK_IDLIEU ="fk_idLieu";

    }



    private static final String TEXT_TYPE =" TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " +
            FeedEntryGAME.TABLE_NAME + " (" +
                FeedEntryGAME.COLUMN_DATE + TEXT_TYPE + COMMA_SEP +
                FeedEntryGAME.COLUMM_HEURE + " DATETIME" + COMMA_SEP +
                FeedEntryGAME.COLUMN_ATTRIBUTE1 + TEXT_TYPE + COMMA_SEP +
                FeedEntryGAME.COLUMN_TEAM_RESIDENT + TEXT_TYPE + COMMA_SEP +
                FeedEntryGAME.COLUMN_TEAM_EXTERIEUR + TEXT_TYPE + COMMA_SEP +
                FeedEntryGAME.COLUMN_QUANTITE + TEXT_TYPE + COMMA_SEP +
                FeedEntryGAME.COLUMN_STATUT + TEXT_TYPE + COMMA_SEP +
                FeedEntryGAME.COLUMN_NB_PLACES_DISPO + " INTEGER" + COMMA_SEP +
                FeedEntryGAME.COLUMN_FK_STADE + " INTEGER REFERENCES" + FeedEntrySTADE.TABLE_NAME
            + " )";



}



