package com.example.helena.gamebook;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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
                        Intent intent = new Intent(ListOfBooking.this, TheBooking.class);
                        intent.putExtra("idBooking", bookingSelectedId);
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
        inflater.inflate(R.menu.menu_search,menu);
        return super.onCreateOptionsMenu(menu);

    }

    public void backToHome(View view){
        Intent backToHome = new Intent(this,home.class);
        backToHome.putExtra("idCustomer", idCustomer);
        startActivity(backToHome);
    }


    //*MErci de faire le nécessaire concernant les update et
    //les traductions
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


    private void updateViews() {
        Resources resources = getResources();

        ImageButton football = (ImageButton)findViewById(R.id.imageButtonFootball);
        ImageButton booking = (ImageButton)findViewById(R.id.imageButtonBooking);
        ImageButton user = (ImageButton)findViewById(R.id.imageButtonUser);

        football.setContentDescription(resources.getString(R.string.idfootball));
        booking.setContentDescription(resources.getString(R.string.idBooking));
        user.setContentDescription(resources.getString(R.string.idUser));
    }




}
