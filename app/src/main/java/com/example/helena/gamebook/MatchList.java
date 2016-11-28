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
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.helena.gamebook.db.SQLiteHelper;
import com.example.helena.gamebook.db.adapter.GameAdapter;
import com.example.helena.gamebook.db.adapter.GameDataSource;
import com.example.helena.gamebook.db.object.Game;

import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView;

/**
 * Created by Stéphanie Pinto
 * Cette classe affiche la liste de tous les matchs existants
 */

public class MatchList extends AppCompatActivity {

    private ListView gameList;
    Context context;
    List<Game> games;
    Game game;
    SQLiteHelper helper;
    Integer idCustomer;
    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_list);
        context = this;

        // paramètres de la nav bar
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.football);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        // affichage de la liste des matchs
        if(savedInstanceState == null){
            bundle = getIntent().getExtras();
            if(bundle == null){
                idCustomer = null;
            } else {
                idCustomer = bundle.getInt("idCustomer");

                final GameDataSource gds = new GameDataSource(this);
                helper.getInstance(context);

                gameList = (ListView)findViewById(R.id.Games_List);
                games = new ArrayList<Game>();
                games = gds.getAllGames();

                GameAdapter gameAdapter = new GameAdapter(context, games);
                gameList.setAdapter(gameAdapter);

                gameList.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        game = (Game) parent.getItemAtPosition(position);
                        int gameSelectedId = game.getId();
                        int a = idCustomer;

                        Intent intent = new Intent(MatchList.this, TheMatch.class);
                        intent.putExtra("idGame", gameSelectedId);
                        intent.putExtra("idCustomer", a);
                        startActivity(intent);
                    }

                });
            }
        }else{
            idCustomer = (int) savedInstanceState.getSerializable("idCustomer");
        }
    }

    // sélection du menu adéquat
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_basic,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //onClick to go back to the SelectAction Layout
    public void onClickBackToSelectAction(View w) {
        Intent intent = new Intent(MatchList.this, home.class);
        intent.putExtra("idCustomer", idCustomer);
        startActivity(intent);
    }

    // redirection à l'ajout d'un match
    public void onClickGoToAddNewGame(View w) {
        Intent intent = new Intent(MatchList.this, NewMatch.class);
        intent.putExtra("idCustomer", idCustomer);
        startActivity(intent);
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

                        Intent toMain = new Intent(MatchList.this, MainActivity.class);
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
                Intent toTheSame = new Intent(MatchList.this, MatchList.class);
                toTheSame.putExtra("idCustomer", idCustomer);
                startActivity(toTheSame);
                break;
            case R.id.id_frFlag:
                LocaleHelper.setLocale(this, "fr");
                toTheSame = new Intent(MatchList.this, MatchList.class);
                toTheSame.putExtra("idCustomer", idCustomer);
                startActivity(toTheSame);
                break;
        }
        return false;
    }

    // selection d'un match de la liste
    public void toTheMatch(View view) {
        Intent toTheMatch = new Intent(this,TheMatch.class);
        toTheMatch.putExtra("idCustomer", idCustomer);
        startActivity(toTheMatch);
    }
}
