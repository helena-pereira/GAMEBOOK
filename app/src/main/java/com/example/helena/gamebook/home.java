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
import android.widget.ImageButton;

/**
 * Created by Stéphanie Pinto
 * Classe accueil qui permet d'aller soit voir la liste des matchs
 * soit la liste des réservations ou encore la page Mon Compte
 */

public class home extends AppCompatActivity {

    Integer idCustomer ;
    Bundle bundle;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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

                        Intent toMain = new Intent(home.this, MainActivity.class);
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
                Intent toTheSame = new Intent(home.this, home.class);
                toTheSame.putExtra("idCustomer", idCustomer);
                startActivity(toTheSame);
                break;
            case R.id.id_frFlag:
                LocaleHelper.setLocale(this, "fr");
                toTheSame = new Intent(home.this, home.class);
                toTheSame.putExtra("idCustomer", idCustomer);
                startActivity(toTheSame);
                break;
        }
        return false;
    }

    public void toMatch(View view) {
        Intent toMatch = new Intent(this,MatchList.class);
        toMatch.putExtra("idCustomer", idCustomer);
        startActivity(toMatch);

    }

    public void toListBooking(View view) {
        Intent toListBooking = new Intent(this,ListOfBooking.class);
        toListBooking.putExtra("idCustomer", idCustomer);
        startActivity(toListBooking);

    }

    public void toMonCompte(View view) {
        Intent toMonCompte = new Intent(this, edit_user.class);
        toMonCompte.putExtra("idCustomer", idCustomer);
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
