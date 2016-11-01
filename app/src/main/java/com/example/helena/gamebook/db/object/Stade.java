package com.example.helena.gamebook.db.object;

/**
 * Created by Helena on 01.11.2016.
 */

public class Stade {

    private int id ;
    private String nom;
    private int nb_places_totales ;
    private String adresse ;
    private String npa ;
    private String ville ;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNb_places_totales() {
        return nb_places_totales;
    }

    public void setNb_places_totales(int nb_places_totales) {
        this.nb_places_totales = nb_places_totales;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNpa() {
        return npa;
    }

    public void setNpa(String npa) {
        this.npa = npa;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}


