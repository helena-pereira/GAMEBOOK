package com.example.helena.gamebook;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.helena.gamebook.db.SQLiteHelper;
import com.example.helena.gamebook.db.adapter.BookingAdapter;
import com.example.helena.gamebook.db.adapter.BookingDataSource;
import com.example.helena.gamebook.db.adapter.GameAdapter;
import com.example.helena.gamebook.db.adapter.GameDataSource;
import com.example.helena.gamebook.db.object.Booking;
import com.example.helena.gamebook.db.object.Game;

import java.util.ArrayList;
import java.util.List;

public class ListOfBooking extends AppCompatActivity {
    SQLiteHelper helper;
    private ListView bookingList;
    Booking booking;
    List<Booking> bookings;
    Integer idCustomer;
    Bundle bundle;


    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_booking);
        context = this;
 
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.football);
        getSupportActionBar().setDisplayShowTitleEnabled(true);


        
        if(savedInstanceState == null){
            bundle = getIntent().getExtras();
            if(bundle == null){
                idCustomer = null;
            } else {
                idCustomer = bundle.getInt("idCustomer");


                final BookingDataSource bds = new BookingDataSource(this);
                helper.getInstance(context);

                bookingList = (ListView)findViewById(R.id.Bookings_List);
                bookings = new ArrayList<Booking>();
                bookings= bds.getAllBookings();

                BookingAdapter bookingAdapter = new BookingAdapter(context, bookings);
                bookingList.setAdapter(bookingAdapter);

                bookingList.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        booking = (Booking) parent.getItemAtPosition(position);
                        int bookingSelectedId = booking.getId();
                        int a = idCustomer;
                        Intent intent = new Intent(ListOfBooking.this, EditBooking.class);
                        intent.putExtra("idBooking1", bookingSelectedId);
                        intent.putExtra("idCustomer", a);
                        startActivity(intent);
                    }

                });
            }
        }else{
            idCustomer = (int) savedInstanceState.getSerializable("idCustomer");
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_basic,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void backToHome(View view){
        Intent backToHome = new Intent(this,home.class);
        backToHome.putExtra("idCustomer", idCustomer);
        startActivity(backToHome);
    }

    public void onClickGoToAddNewBooking(View view){
        // Coder pour ajouter un Booking
    }


    // refresh pour le changement de langue ou redirection pour la déconnexion
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId()) {
            case R.id.logout:
                AlertDialog.Builder alertDeleteBooking = new AlertDialog.Builder(this);
                // Le titre du Dialog Alert
                alertDeleteBooking.setTitle(R.string.LogOutUserTitle);

                // Message du Dialog Alert
                alertDeleteBooking.setMessage(R.string.LogOutUserMessage);

                // Icon de suppression
                alertDeleteBooking.setIcon(R.mipmap.logout);

                // si on clique oui
                alertDeleteBooking.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {

                        Intent toMain = new Intent(ListOfBooking.this, MainActivity.class);
                        toMain.putExtra("idCustomer", idCustomer);
                        startActivity(toMain);
                    }
                });

                // Si on clique sur non
                alertDeleteBooking.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // ça ferme tout simplement le dialog
                        dialog.cancel();
                    }
                });
                // On montre l'alerte
                alertDeleteBooking.show();
                break;
            case R.id.id_enFlag:
                LocaleHelper.setLocale(this, "en");
                Intent toTheSame = new Intent(ListOfBooking.this, ListOfBooking.class);
                toTheSame.putExtra("idCustomer", idCustomer);
                startActivity(toTheSame);
                break;
            case R.id.id_frFlag:
                LocaleHelper.setLocale(this, "fr");
                toTheSame = new Intent(ListOfBooking.this, ListOfBooking.class);
                toTheSame.putExtra("idCustomer", idCustomer);
                startActivity(toTheSame);
                break;
        }
        return false;
    }
}
