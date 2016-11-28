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
import android.webkit.ConsoleMessage;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.helena.gamebook.db.adapter.BookingDataSource;
import com.example.helena.gamebook.db.adapter.CustomerDataSource;
import com.example.helena.gamebook.db.adapter.GameDataSource;
import com.example.helena.gamebook.db.object.Booking;
import com.example.helena.gamebook.db.object.Customer;
import com.example.helena.gamebook.db.object.Game;

import org.w3c.dom.Text;

import java.util.Random;

public class NewBooking extends AppCompatActivity {
    Integer idGame;
    Integer idCustomer;
    Bundle bundle;
    Bundle bundle2;
    Context context;

    Customer customer;
    Game game1;

    TextView idEditGame,idClientName;
    EditText idSeat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_booking);

        context = this;

        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.football);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        if(savedInstanceState == null){
            bundle = getIntent().getExtras();
            if(bundle == null){
                idGame = null;
            } else {
                idGame = bundle.getInt("idGame");
            }
        }else{
            idGame = (int) savedInstanceState.getSerializable("idGame");
        }


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


        load();


    }

    public void load() {

        idClientName = (TextView) findViewById(R.id.idClientName);
        idSeat = (EditText)findViewById(R.id.idEditSeat);
        idEditGame = (TextView)findViewById(R.id.idEitTheGame);

        GameDataSource gds = new GameDataSource(context);
        game1 = gds.getGameById(idGame);

        CustomerDataSource cds = new CustomerDataSource(context);
        customer = cds.getCustomerById(idCustomer);

        idEditGame.setText(("N° :" + game1.getId() + " - " + game1.getTeam_res() + " vs. "+ game1.getTeam_ext()));
        idClientName.setText(customer.getNom() + " "+ customer.getPrenom());

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

                        Intent toMain = new Intent(NewBooking.this, MainActivity.class);
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
                Intent toTheSame = new Intent(NewBooking.this, NewBooking.class);
                toTheSame.putExtra("idCustomer", idCustomer);
                startActivity(toTheSame);
                break;
            case R.id.id_frFlag:
                LocaleHelper.setLocale(this, "fr");
                toTheSame = new Intent(NewBooking.this, NewBooking.class);
                toTheSame.putExtra("idCustomer", idCustomer);
                startActivity(toTheSame);
                break;
        }
        return false;
    }

    public void toTheMatch(View view) {
        idSeat = (EditText)findViewById(R.id.idEditSeat);

        Booking booking = new Booking();

        BookingDataSource bds = new BookingDataSource(context);
        GameDataSource gds = new GameDataSource(context);
        CustomerDataSource cds = new CustomerDataSource(context);

        Integer x = (int)(Math.random()* 5 + 3 );

        booking.setId(x);
        booking.setNum_seat(idSeat.getText().toString());
        booking.setCustomer(customer);
        booking.setGame(game1);

        bds.createBooking(booking);

        Intent toTheMatch = new Intent(this,MatchList.class);
        toTheMatch.putExtra("idCustomer", idCustomer);
        //toTheMatch.putExtra("idBooking", idBooking1);
        startActivity(toTheMatch);
    }
}
