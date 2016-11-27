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
import android.widget.ImageButton;

public class ListOfBooking extends AppCompatActivity {

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
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);
        return super.onCreateOptionsMenu(menu);

    }

    public void backToHome(View view){
        Intent backToHome = new Intent(this,home.class);
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
