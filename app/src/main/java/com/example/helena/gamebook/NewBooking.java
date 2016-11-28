package com.example.helena.gamebook;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

public class NewBooking extends AppCompatActivity {
    Integer idGame ;
    Integer idCustomer;
    Integer TheidBooking;
    Integer idBooking;
    Bundle bundle;
    Bundle bundle2, bundle3;
    Context context;

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


        if(savedInstanceState == null){
            bundle3 = getIntent().getExtras();
            if(bundle3 == null){
                idBooking = null;
            } else {
                idBooking = bundle3.getInt("idBooking");
            }
        }else{
            idBooking = (int) savedInstanceState.getSerializable("idBooking");
        }



        load();


    }

    public void load() {

        idClientName = (TextView) findViewById(R.id.idClientName);
        idSeat = (EditText)findViewById(R.id.idEditSeat);
        idEditGame = (TextView)findViewById(R.id.idEitTheGame);


        GameDataSource gds = new GameDataSource(context);
        Game game1 = gds.getGameById(idGame);

        CustomerDataSource cds = new CustomerDataSource(context);
        Customer customer = cds.getCustomerById(idCustomer);

        Booking booking = new Booking();
        BookingDataSource bds = new BookingDataSource(context);
        booking.setGame(game1);
        booking.setCustomer(customer);

        booking.setNum_seat(idSeat.getText().toString());


        idEditGame.setText(("N° :" + game1.getId() + " - " + game1.getTeam_res() + " vs. "+ game1.getTeam_ext()));
        idClientName.setText(customer.getNom() + " "+ customer.getPrenom());
        //idSeat.setText(booking.getNum_seat());


        bds.createBooking(booking);
        TheidBooking = booking.getId();



    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_basic,menu);
        return super.onCreateOptionsMenu(menu);

    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId()) {
            case R.id.id_enFlag:
                LocaleHelper.setLocale(this,"en");
                updateViews();
                break;
            case R.id.id_frFlag:
                LocaleHelper.setLocale(this,"fr");
                updateViews();
                break;
            default:
                LocaleHelper.setLocale(this,"en");
                updateViews();
                break;
        }
        return true;
    }

    public void toTheMatch(View view) {
        idSeat = (EditText)findViewById(R.id.idEditSeat);

        Booking booking = new Booking();
        BookingDataSource bds = new BookingDataSource(context);

        booking.setNum_seat(idSeat.getText().toString());
        //booking.setCustomer(booking.getCustomer().getId());
        booking.setGame(booking.getGame());

        bds.createBooking(booking);



        Intent toTheMatch = new Intent(this,MatchList.class);
        toTheMatch.putExtra("idCustomer", idCustomer);
        toTheMatch.putExtra("idBooking", idBooking);
        startActivity(toTheMatch);
    }

    private void updateViews() {
        Resources resources = getResources();

    }
}
