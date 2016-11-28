package com.example.helena.gamebook;

import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.helena.gamebook.db.SQLiteHelper;
import com.example.helena.gamebook.db.adapter.GameDataSource;
import com.example.helena.gamebook.db.object.Game;

/**
 * Created by Stéphanie Pinto
 * Cette classe permet de modifier les données d'un match
 */

public class EditMatch extends AppCompatActivity  {

    Integer idGame ;
    Integer idCustomer;
    Context context;
    Bundle bundle;
    Bundle bundle2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_match);

        context = this;

        // paramètres de la nav bar
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.football);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        // affichage des données déjà inscrites
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

    // chargement des données du match
    private void loadGameSelected() {
            GameDataSource gds = new GameDataSource(context);
            Game game = new Game();

            EditText idDate, idHeure, idResident, idVisiteur, idQuantite, idNameMatch ;

            idDate = (EditText)findViewById(R.id.idEditDate);
            idHeure = (EditText)findViewById(R.id.idEditHeure);
            idResident = (EditText)findViewById(R.id.idEditResident);
            idVisiteur = (EditText)findViewById(R.id.idEditVisiteur);
            idQuantite = (EditText)findViewById(R.id.idEditQuantite);

            game = gds.getGameById(idGame);

            idDate.setText(game.getDate());
            idHeure.setText(game.getHeure());
            idResident.setText(game.getTeam_res());
            idVisiteur.setText(game.getTeam_ext());
            idQuantite.setText(game.getQuantity());

    }

    // sélection menu adéquat
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_basic,menu);
        return super.onCreateOptionsMenu(menu);
    }

    // sauvegarder
    public void save(View view){

        EditText Date, Heure, Resident, Visiteur, Quantite;

        Date = (EditText) findViewById(R.id.idEditDate);
        Heure = (EditText) findViewById(R.id.idEditHeure);
        Resident = (EditText) findViewById(R.id.idEditResident);
        Visiteur = (EditText) findViewById(R.id.idEditVisiteur);
        Quantite = (EditText) findViewById(R.id.idEditQuantite);

        Game game = new Game();

        game.setId(idGame);

        game.setDate(Date.getText().toString());
        game.setHeure(Heure.getText().toString());
        game.setTeam_res(Resident.getText().toString());
        game.setTeam_ext(Visiteur.getText().toString());

        game.setQuantity(Quantite.getText().toString());

        GameDataSource gds = new GameDataSource(context);

        gds.updateGame(game);

        Intent toListMatch = new Intent(this,MatchList.class);
        toListMatch.putExtra("idGame", idGame);
        toListMatch.putExtra("idCustomer", idCustomer);
        startActivity(toListMatch);
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

                        Intent toMain = new Intent(EditMatch.this, MainActivity.class);
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
                Intent toTheSame = new Intent(EditMatch.this, EditMatch.class);
                toTheSame.putExtra("idGame", idGame);
                startActivity(toTheSame);
                break;
            case R.id.id_frFlag:
                LocaleHelper.setLocale(this, "fr");
                toTheSame = new Intent(EditMatch.this, EditMatch.class);
                toTheSame.putExtra("idGame", idGame);
                startActivity(toTheSame);
                break;
        }
        return false;
    }

    // retour
    public void toTheMatch(View view) {
        Intent toTheMatch = new Intent(this,TheMatch.class);
        toTheMatch.putExtra("idGame", idGame);
        startActivity(toTheMatch);
    }
}
