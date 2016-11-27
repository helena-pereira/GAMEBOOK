package com.example.helena.gamebook;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.helena.gamebook.db.adapter.BookingDataSource;
import com.example.helena.gamebook.db.adapter.GameDataSource;
import com.example.helena.gamebook.db.object.Booking;
import com.example.helena.gamebook.db.object.Game;

public class NewBooking extends AppCompatActivity {
    Integer idGame ;
    Bundle bundle;
    Context context;
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

        load();


    }

    private void load() {

        Game game = new Game();
        Booking booking = new Booking();

        GameDataSource gds = new GameDataSource(context);
        BookingDataSource bds = new BookingDataSource(context);

        EditText idEditGame ;

        idEditGame = (EditText)findViewById(R.id.idGame);

        /////customer.setNom(editName.getText().toString());

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_basic,menu);
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
        Intent toTheMatch = new Intent(this,TheMatch.class);
        startActivity(toTheMatch);
    }

    private void updateViews() {
        Resources resources = getResources();

    }
}
