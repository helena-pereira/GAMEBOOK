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
import com.example.helena.gamebook.db.object.Booking;
import com.example.helena.gamebook.db.object.Customer;
import com.example.helena.gamebook.db.object.Game;
import com.example.helena.gamebook.db.object.Stade;

import static com.example.helena.gamebook.db.FeedReaderContract.*;

/**
 * Created by Helena on 07.11.2016.
 */

public class BookingDataSource {
    private SQLiteDatabase db;
    private Context context;

    public BookingDataSource(Context context) {
        SQLiteHelper sqliteHelper = SQLiteHelper.getInstance(context);
        db = sqliteHelper.getWritableDatabase();
        this.context = context;
    }

    //INSER BOOKING
    public long createBooking(Booking booking) {
        long id;
        ContentValues values = new ContentValues();
        values.put(tableBOOKING.BOOKING_ID, booking.getId());
        values.put(tableBOOKING.BOOKING_FK_GAME, booking.getGame().getId());
        values.put(tableBOOKING.BOOKING_FK_CUSTOMER, booking.getCustomer().getId());
        values.put(tableBOOKING.BOOKING_NUM_SEAT, booking.getNum_seat());

        id = this.db.insert(FeedReaderContract.tableBOOKING.TABLE_NAME, null, values);
        //db.close();

        return id;

    }

    //GET 1 BOOKING
    public Booking getBookingById(long id) {
        String sql = "SELECT * FROM " + tableBOOKING.TABLE_NAME +
                " WHERE " + tableBOOKING.BOOKING_ID + " = " + id;

        Cursor cursor = this.db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Booking booking = new Booking();
        booking.setId(cursor.getInt(cursor.getColumnIndex(tableBOOKING.BOOKING_ID)));

        CustomerDataSource cds = new CustomerDataSource(this.context);
        Customer customer = cds.getCustomerById(cursor.getLong(cursor.getColumnIndex(tableBOOKING.BOOKING_FK_CUSTOMER)));
        booking.setCustomer(customer);

        GameDataSource gds = new GameDataSource(this.context);
        Game game = gds.getGameById(cursor.getLong(cursor.getColumnIndex(tableBOOKING.BOOKING_FK_GAME)));
        booking.setGame(game);
        booking.setNum_seat(cursor.getString(cursor.getColumnIndex(tableBOOKING.BOOKING_NUM_SEAT)));

        //db.close();
        return booking;
    }


    //GET ALL Booking
    public List<Booking> getAllBookings(){
        List<Booking> bookings = new ArrayList<Booking>();
        String sql = "SELECT * FROM " + tableBOOKING.TABLE_NAME + " ORDER BY " + tableBOOKING.BOOKING_ID;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                Booking booking = new Booking();
                booking.setId(cursor.getInt(cursor.getColumnIndex(tableBOOKING.BOOKING_ID)));

                Customer customer = new Customer();
                CustomerDataSource cds = new CustomerDataSource(context);
                customer = cds.getCustomerById(cursor.getLong(cursor.getColumnIndex(tableBOOKING.BOOKING_FK_CUSTOMER)));

                booking.setCustomer(customer);

                GameDataSource gds = new GameDataSource(context);
                Game game = gds.getGameById(cursor.getLong(cursor.getColumnIndex(tableBOOKING.BOOKING_FK_GAME)));
                booking.setGame(game);
                booking.setNum_seat(cursor.getString(cursor.getColumnIndex(tableBOOKING.BOOKING_NUM_SEAT)));

                bookings.add(booking);
            }
            while(cursor.moveToNext());
        }
        //db.close();
        return bookings;
    }




    //modification d'un booking
    public int updateBooking(Booking booking) {
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.tableBOOKING.BOOKING_ID, booking.getId());
        values.put(FeedReaderContract.tableBOOKING.BOOKING_FK_GAME, booking.getGame().getId());
        values.put(FeedReaderContract.tableBOOKING.BOOKING_FK_CUSTOMER, booking.getCustomer().getId());
        values.put(FeedReaderContract.tableBOOKING.BOOKING_NUM_SEAT, booking.getNum_seat());

        return this.db.update(FeedReaderContract.tableBOOKING.TABLE_NAME, values, tableBOOKING.BOOKING_ID + " = ?",
                new String[]{String.valueOf(booking.getId())});
    }



}
