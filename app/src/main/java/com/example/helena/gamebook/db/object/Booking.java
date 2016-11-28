package com.example.helena.gamebook.db.object;

import android.widget.EditText;

/**
 * Created by Helena on 01.11.2016.
 */

public class Booking {
    private int _id ;
    private String num_seat ;
    private Game game;
    private Customer customer;


    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getNum_seat() {
        return num_seat;
    }

    public void setNum_seat(String num_seat) {
        this.num_seat = num_seat;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
