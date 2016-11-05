package com.example.helena.gamebook.db.object;

/**
 * Created by Helena on 01.11.2016.
 */

public class Booking {
    private  int id ;
    private int num_seat ;
    private Game game;
    private Customer customer;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_seat() {
        return num_seat;
    }

    public void setNum_seat(int num_seat) {
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
