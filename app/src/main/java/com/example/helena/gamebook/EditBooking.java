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
                //etidSeat.setText(booking.getNum_seat());


            }
        }else{
            idBooking = (int) savedInstanceState.getSerializable("idBooking");
        }

    }


    public void save(View view){
        etidSeat = (EditText)findViewById(R.id.idSeat);

        Booking booking = new Booking();
       // booking.setNum_seat(etidSeat);

        BookingDataSource bds = new BookingDataSource(context);
        booking = bds.getBookingById(idBooking);

        BookingDataSource bds2 = new BookingDataSource(context);
        bds2.updateBooking(booking);

        Intent toListMatch = new Intent(this,ListOfBooking.class);
            toListMatch.putExtra("idCustomer", idCustomer);
        startActivity(toListMatch);
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
        Intent toTheMatch = new Intent(this,ListOfBooking.class);
        toTheMatch.putExtra("idBooking", idBooking);
        //toTheMatch.putExtra("idCustomer", a);
        startActivity(toTheMatch);
    }

    private void updateViews() {
        Resources resources = getResources();

    }
}
