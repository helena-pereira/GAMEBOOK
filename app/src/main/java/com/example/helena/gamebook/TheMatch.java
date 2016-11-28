package com.example.helena.gamebook;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.helena.gamebook.db.SQLiteHelper;
import com.example.helena.gamebook.db.adapter.GameDataSource;
import com.example.helena.gamebook.db.object.Game;

/***
 * Created by Stéphanie Pinto
 * Cette classe affiche le match selectionné dans la liste des matchs
 */

public class TheMatch extends AppCompatActivity {

    Integer idGame ;
    Integer idCustomer ;
    Context context;
    Bundle bundle;
    Bundle bundle2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_match);

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


        loadGameSelected();



    }

    private void loadGameSelected() {

        GameDataSource gds = new GameDataSource(context);
        Game game = new Game();

        TextView idDate, idHeure, idStade, idResident, idVisiteur, idStatut, idQuantite, idNameMatch ;

        idDate = (TextView)findViewById(R.id.idDate);
        idHeure = (TextView)findViewById(R.id.idHeure);
        //idStade = (TextView)findViewById(R.id.idStade);
        idResident = (TextView)findViewById(R.id.idResident);
        idVisiteur = (TextView)findViewById(R.id.idVisiteur);
        idStatut = (TextView)findViewById(R.id.idStatut);
        idQuantite = (TextView)findViewById(R.id.idQuantite);
        idNameMatch = (TextView)findViewById(R.id.idNameMatch);

        game = gds.getGameById(idGame);


        idDate.setText(game.getDate());
        idHeure.setText(game.getHeure());
        //idStade.setText(tst);
        idResident.setText(game.getTeam_res());
        idVisiteur.setText(game.getTeam_ext());
        idStatut.setText(game.getStatut());
        idQuantite.setText(game.getQuantity());
        idNameMatch.setText(game.getTeam_res() + " vs. "+ game.getTeam_ext());

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_basic,menu);
        return super.onCreateOptionsMenu(menu);

    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId()) {
            case R.id.logout:
                Intent toMain = new Intent(this, MainActivity.class);
                startActivity(toMain);
                break;
            case R.id.id_enFlag:
                LocaleHelper.setLocale(this, "en");
                Intent toTheSame = new Intent(this, TheMatch.class);
                toTheSame.putExtra("idGame", idGame);
                startActivity(toTheSame);
                break;
            case R.id.id_frFlag:
                LocaleHelper.setLocale(this, "fr");
                toTheSame = new Intent(this, TheMatch.class);
                toTheSame.putExtra("idGame", idGame);
                startActivity(toTheSame);
                break;
        }
        return false;
    }

    //Revenir MatchList
    public void goToAllMatch(View view){
        Intent goToAllMatch = new Intent(this,MatchList.class);
        goToAllMatch.putExtra("idGame", idGame);
        goToAllMatch.putExtra("idCustomer", idCustomer);
        startActivity(goToAllMatch);
    }

    //Nouvelle Réservation
    public void toNewBooking(View view) {
        Intent toNewBooking = new Intent(this, NewBooking.class);
        toNewBooking.putExtra("idGame", idGame);
        toNewBooking.putExtra("idCustomer", idCustomer);
        startActivity(toNewBooking);
    }

    //Modifier le match
    public void toEditMatch(View view) {
        Intent toEditMatch = new Intent(this,EditMatch.class);
        toEditMatch.putExtra("idGame", idGame);
        toEditMatch.putExtra("idCustomer", idCustomer);
        startActivity(toEditMatch);
    }

    //suppression de la base de donnée
    public void toListMatch(View view) {
        Intent toListMatch = new Intent(this,MatchList.class);
        toListMatch.putExtra("idGame", idGame);
        toListMatch.putExtra("idCustomer", idCustomer);
        startActivity(toListMatch);
    }


    public void toDelete(View view){
        AlertDialog.Builder alertDeleteBooking = new AlertDialog.Builder(this);
        // Titre
        alertDeleteBooking.setTitle(R.string.deleteBookingTitle);

        // Message
        alertDeleteBooking.setMessage(R.string.deleteBookingMessage);

        // Icon de suppression
        alertDeleteBooking.setIcon(R.mipmap.delete);

        // Si on clique sur oui ça supprime un message
        alertDeleteBooking.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {

                /*
                Helena : suppression d'un match
                 */
                GameDataSource gds = new GameDataSource(context);
                gds.deleteGame(idGame);

                dialog.cancel();
                Intent toListMatch = new Intent(TheMatch.this,MatchList.class);
                toListMatch.putExtra("idCustomer", idCustomer);
                toListMatch.putExtra("idGame", idGame);
                startActivity(toListMatch);
            }
        });

        // Si on clique sous non ça ferme le dialog
        alertDeleteBooking.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        // Montrer le dialog
        alertDeleteBooking.show();
    }


}
