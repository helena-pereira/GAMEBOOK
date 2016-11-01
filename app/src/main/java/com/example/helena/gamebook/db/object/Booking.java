package com.example.helena.gamebook.db.object;

/**
 * Created by Helena on 01.11.2016.
 */

public class Booking {
    private  int id ;
    private int num_seat ;


    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id ;
    }


    //pour le siège nous devons donner un nombre aléatoire entre 1 et
    //le total des nombre de siège
    public int getNum_seat(){
        return num_seat;
    }

    public void setNum_seat(){
        this.num_seat = num_seat;
    }
}
