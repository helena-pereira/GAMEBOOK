package com.example.helena.gamebook;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.helena.gamebook.db.adapter.BookingDataSource;
import com.example.helena.gamebook.db.adapter.GameDataSource;
import com.example.helena.gamebook.db.object.Booking;
import com.example.helena.gamebook.db.object.Game;

import org.w3c.dom.Text;

public class EditBooking extends AppCompatActivity {

    Context context;
    Integer idBooking ,idCustomer;
    Bundle bundle, bundle2;

    TextView etidGame , etidClientName;
    EditText etidSeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_booking);

        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.football);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6C7CE2")));

        if(savedInstanceState == null){
            bundle2 = getIntent().getExtras();
            if(bundle2 == null){
                idCustomer = null;
            } else {
                idCustomer = bundle2.getInt("idCustomer");
            }
        }else{
            idCustomer = (int) savedInstanceState.getSerializable("idCustomer");
        }

        if(savedInstanceState == null){
            bundle = getIntent().getExtras();
            if(bundle == null){
                idBooking = null;
            } else {
                idBooking = bundle.getInt("idBooking");

                etidGame = (TextView) findViewById(R.id.idGame);
                etidClientName = (TextView)findViewById(R.id.idClientName);
                etidSeat = (EditText)findViewById(R.id.idSeat);

                Booking booking = new Booking();
                BookingDataSource bds = new BookingDataSource(context);
                booking = bds.getBookingById(idBooking);

                etidGame.setText("N°: " + booking.getGame().getId() + " - "+booking.getGame().getTeam_res() + " vs. " + booking.getGame().getTeam_ext());
                etidClientName.setText(booking.getCustomer().getNom() + " " + booking.getCustomer().getPrenom());
                etidSeat.setText(booking.getNum_seat().toString());


            }
        }else{
            idBooking = (int) savedInstanceState.getSerializable("idBooking");
        }

    }


    public void save(View view){
        etidSeat = (EditText)findViewById(R.id.idSeat);

        Booking booking = new Booking();
        BookingDataSource bds = new BookingDataSource(context);
        booking = bds.getBookingById(idBooking);


        booking.setNum_seat(etidSeat.toString());
        booking.setCustomer(booking.getCustomer());
        booking.setGame(booking.getGame());

        //booking = bds.getBookingById(idBooking);

        bds.createBooking(booking);
        //BookingDataSource bds2 = new BookingDataSource(context);
        //bds2.updateBooking(booking);

        Intent toListMatch = new Intent(this,ListOfBooking.class);
            toListMatch.putExtra("idCustomer", idCustomer);
        startActivity(toListMatch);
    }


    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_basic,menu);
        return super.onCreateOptionsMenu(menu);

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

                        Intent toMain = new Intent(EditBooking.this, MainActivity.class);
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
                Intent toTheSame = new Intent(EditBooking.this, EditBooking.class);
                toTheSame.putExtra("idBooking", idBooking);
                startActivity(toTheSame);
                break;
            case R.id.id_frFlag:
                LocaleHelper.setLocale(this, "fr");
                toTheSame = new Intent(EditBooking.this, EditBooking.class);
                toTheSame.putExtra("idBooking", idBooking);
                startActivity(toTheSame);
                break;
        }
        return false;
    }

    public void toTheMatch(View view) {
        Intent toTheMatch = new Intent(this,ListOfBooking.class);
        toTheMatch.putExtra("idBooking", idBooking);
        //toTheMatch.putExtra("idCustomer", a);
        startActivity(toTheMatch);
    }
}
