package com.example.helena.gamebook;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
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

import com.example.helena.gamebook.db.SQLiteHelper;
import com.example.helena.gamebook.db.adapter.GameDataSource;
import com.example.helena.gamebook.db.object.Game;

/**
 * Created by Stéphanie Pinto
 * Cette classe permet de modifier les données d'un match
 */

public class EditMatch extends AppCompatActivity {

    Integer idGame ;
    Context context;
    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_match);

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

        loadGameSelected();



    }

    private void loadGameSelected() {

            GameDataSource gds = new GameDataSource(context);
            Game game = new Game();

            EditText idDate, idHeure, idStade, idResident, idVisiteur, idStatut, idQuantite, idNameMatch ;


            idDate = (EditText)findViewById(R.id.idEditDate);
            idHeure = (EditText)findViewById(R.id.idEditHeure);
            //idStade = (TextView)findViewById(R.id.idStade);
            idResident = (EditText)findViewById(R.id.idEditResident);
            idVisiteur = (EditText)findViewById(R.id.idEditVisiteur);
            //idStatut = (EditText)findViewById(R.id.idStatut);
            idQuantite = (EditText)findViewById(R.id.idEditQuantite);

            game = gds.getGameById(idGame);

            idDate.setText(game.getDate());
            idHeure.setText(game.getHeure());
            //idStade.setText(tst);
            idResident.setText(game.getTeam_res());
            idVisiteur.setText(game.getTeam_ext());
            //idStatut.setText(game.getStatut());
            idQuantite.setText(game.getQuantity());

    }



    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_basic,menu);
        return super.onCreateOptionsMenu(menu);

    }

    public void save(View view){

        SQLiteHelper helper = new SQLiteHelper(this);

        SQLiteDatabase db = helper.getWritableDatabase();

        EditText Date, Heure, Stade, Resident, Visiteur, Quantite;
        //ToggleButton Statut;

        Date = (EditText) findViewById(R.id.idEditDate);
        Heure = (EditText) findViewById(R.id.idEditHeure);
        //Stade = (EditText) findViewById(R.id.idStade);
        Resident = (EditText) findViewById(R.id.idEditResident);
        Visiteur = (EditText) findViewById(R.id.idEditVisiteur);
        //Statut = (ToggleButton) findViewById(R.id.idStatut);
        Quantite = (EditText) findViewById(R.id.idEditQuantite);

        Game game = new Game();
        //Stade stade = new Stade();

        game.setId(idGame);

        game.setDate(Date.getText().toString());
        game.setHeure(Heure.getText().toString());
        //game.setStade(Stade.getText().toString());
        game.setTeam_res(Resident.getText().toString());
        game.setTeam_ext(Visiteur.getText().toString());
        //game.setStatut(Statut.getText().toString());

        game.setQuantity(Quantite.getText().toString());

        GameDataSource gds = new GameDataSource(context);

        gds.updateGame(game);

        Intent toListMatch = new Intent(this,MatchList.class);
        startActivity(toListMatch);

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
        toTheMatch.putExtra("idGame", idGame);
        startActivity(toTheMatch);
    }

    private void updateViews() {
        Resources resources = getResources();

    }
}
