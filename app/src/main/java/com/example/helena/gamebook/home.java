package com.example.helena.gamebook;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.football);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6C7CE2")));
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

    public void toMatch(View view) {
        Intent toMatch = new Intent(this,MatchList.class);
        startActivity(toMatch);

    }

    public void toMonCompte(View view) {
        Intent toMonCompte = new Intent(this,user.class);
        startActivity(toMonCompte);

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
