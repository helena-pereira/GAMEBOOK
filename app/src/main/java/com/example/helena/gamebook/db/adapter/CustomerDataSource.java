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
import com.example.helena.gamebook.db.object.Customer;
import com.example.helena.gamebook.db.object.Game;


/**
 * Created by Helena on 01.11.2016.
 */

public class CustomerDataSource {

    private SQLiteDatabase db;
    private Context context;

    public CustomerDataSource(Context context) {
        SQLiteHelper sqLiteHelper = SQLiteHelper.getInstance(context);
        db = sqLiteHelper.getWritableDatabase();
        this.context = context;
    }

    //Insert Customer
    public long createCustomer(Customer customer) {

        long id;
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.tableCUSTOMER.CUSTOMER_NOM, customer.getNom());
        values.put(FeedReaderContract.tableCUSTOMER.CUSTOMER_PRENOM, customer.getPrenom());
        values.put(FeedReaderContract.tableCUSTOMER.CUSTOMER_EMAIL, customer.getEmail());
        values.put(FeedReaderContract.tableCUSTOMER.CUSTOMER_MDP, customer.getMdp());

        id = this.db.insert(FeedReaderContract.tableCUSTOMER.TABLE_NAME, null, values);
        return id;
    }

    /*********************************************************************************************/


    //find customer by id
    public Customer getCustomerById(long id) {
        String sql = "SELECT * FROM " + FeedReaderContract.tableCUSTOMER.TABLE_NAME + " WHERE "
                + FeedReaderContract.tableCUSTOMER.CUSTOMER_ID + " = " + id;

        Cursor cursor = this.db.rawQuery(sql, null);


        if (cursor != null && cursor.getCount()>0) {
            cursor.moveToFirst();

        }
        Customer customer = new Customer();

        customer.setId(cursor.getInt(cursor.getColumnIndex(FeedReaderContract.tableCUSTOMER.CUSTOMER_ID)));
        customer.setNom(cursor.getString(cursor.getColumnIndex(FeedReaderContract.tableCUSTOMER.CUSTOMER_NOM)));
        customer.setPrenom(cursor.getString(cursor.getColumnIndex(FeedReaderContract.tableCUSTOMER.CUSTOMER_PRENOM)));
        customer.setEmail(cursor.getString(cursor.getColumnIndex(FeedReaderContract.tableCUSTOMER.CUSTOMER_EMAIL)));
        customer.setMdp(cursor.getString(cursor.getColumnIndex(FeedReaderContract.tableCUSTOMER.CUSTOMER_MDP)));

        db.close();
        return customer;

    }

    /*********************************************************************************************/

    //find customer by email
    public Customer getCustomerByEmail(String email) {
        String sql = "SELECT * FROM " + FeedReaderContract.tableCUSTOMER.TABLE_NAME + " WHERE "
                + FeedReaderContract.tableCUSTOMER.CUSTOMER_EMAIL + " = '" + email + "'";

        Cursor cursor = this.db.rawQuery(sql, null);
        Customer customer = new Customer();

        if (cursor != null && (cursor.getCount() > 0)) {
            cursor.moveToFirst();

            customer.setId(cursor.getInt(cursor.getColumnIndex(FeedReaderContract.tableCUSTOMER.CUSTOMER_ID)));
            customer.setNom(cursor.getString(cursor.getColumnIndex(FeedReaderContract.tableCUSTOMER.CUSTOMER_NOM)));
            customer.setPrenom(cursor.getString(cursor.getColumnIndex(FeedReaderContract.tableCUSTOMER.CUSTOMER_PRENOM)));
            customer.setEmail(cursor.getString(cursor.getColumnIndex(FeedReaderContract.tableCUSTOMER.CUSTOMER_EMAIL)));
            customer.setMdp(cursor.getString(cursor.getColumnIndex(FeedReaderContract.tableCUSTOMER.CUSTOMER_MDP)));
        }





        /*Cursor cursor = this.db.rawQuery(sql, null);
        Customer customer = new Customer();

        if ((cursor != null) && (cursor.getCount() > 0)) {
            cursor.moveToFirst();
            //customer.setNom(cursor.getString(cursor.getColumnIndex(FeedReaderContract.tableCUSTOMER.CUSTOMER_NOM)));
            //customer.setPrenom(cursor.getString(cursor.getColumnIndex(FeedReaderContract.tableCUSTOMER.CUSTOMER_PRENOM)));
            customer.setEmail(cursor.getString(cursor.getColumnIndex(email)));
            customer.setMdp(cursor.getString(cursor.getColumnIndex(FeedReaderContract.tableCUSTOMER.CUSTOMER_MDP)));

        }
*/

        return customer;

    }

    /*********************************************************************************************/


    //modification d'un customer
    public int updateCustomer(Customer customer) {
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.tableCUSTOMER.CUSTOMER_NOM, customer.getId());
        values.put(FeedReaderContract.tableCUSTOMER.CUSTOMER_NOM, customer.getNom());
        values.put(FeedReaderContract.tableCUSTOMER.CUSTOMER_PRENOM, customer.getPrenom());
        values.put(FeedReaderContract.tableCUSTOMER.CUSTOMER_EMAIL, customer.getEmail());
        values.put(FeedReaderContract.tableCUSTOMER.CUSTOMER_MDP, customer.getMdp());


        return this.db.update(FeedReaderContract.tableCUSTOMER.TABLE_NAME, values, FeedReaderContract.tableCUSTOMER.CUSTOMER_ID + " = ?",
                new String[] {String.valueOf(customer.getId())} );
    }
    /*********************************************************************************************/


    //suppression d'un customer

    public void deleteCustomer (long id){
        //le prof a supprimé tous les enregistrements
        //mais nous cela nous est pas utile

        /*
        RecordDataSource pra = new RecordDataSource(context);
        //get all records of the user
        List<Record> records = pra.getAllRecordsByPerson(id);

        for(Record record : records){
            pra.deleteRecord(record.getId());
        }

        */


        this.db.delete(FeedReaderContract.tableCUSTOMER.TABLE_NAME, FeedReaderContract.tableCUSTOMER.CUSTOMER_ID + " = ?",
               new String [] {String.valueOf(id)} );


    }


}
