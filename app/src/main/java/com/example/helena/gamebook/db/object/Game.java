package com.example.helena.gamebook.db.object;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Helena on 01.11.2016.
 */


// fk id
public class Game {


    private int id ;
    private String date ;
    private String heure ;
    private String team_res ;
    private String team_ext ;
    private int quantity ;
    private String statut;
    private int nb_places_dispo;
    //private Stade stade;
    private String stade ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		Date date = new Date();
		return dateFormat.format(date);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {

        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getTeam_res() {
        return team_res;
    }

    public void setTeam_res(String team_res) {
        this.team_res = team_res;
    }

    public String getTeam_ext() {
        return team_ext;
    }

    public void setTeam_ext(String team_ext) {
        this.team_ext = team_ext;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public int getNb_places_dispo() {
        return nb_places_dispo;
    }

    public void setNb_places_dispo(int nb_places_dispo) {
        this.nb_places_dispo = nb_places_dispo;
    }

   /* public Stade getStade() { return stade;}

    public void setStade(Stade stade) {
        this.stade = stade;
    }*/

    public String getStade() {
        return stade;
    }

    public void setStade(String stade) {
        this.stade = stade;
    }
}





