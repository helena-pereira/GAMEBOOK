package com.example.helena.gamebook;

import android.app.DialogFragment;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import com.example.helena.gamebook.db.SQLiteHelper;
import com.example.helena.gamebook.db.adapter.CustomerDataSource;
import com.example.helena.gamebook.db.adapter.GameDataSource;
import com.example.helena.gamebook.db.object.Customer;
import com.example.helena.gamebook.db.object.Game;
import com.example.helena.gamebook.db.object.Stade;

public class NewMatch extends AppCompatActivity {

    Integer idGame ;
    Integer idCustomer ;
    Context context;
    Bundle bundle;
    Bundle bundle2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_match);
        context = this;

        this.getSupportActionBar().setHomeButtonEnabled(false);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.getSupportActionBar().setDisplayUseLogoEnabled(true);
        this.getSupportActionBar().setLogo(R.mipmap.football);
        this.getSupportActionBar().setDisplayShowTitleEnabled(true);
        //this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6C7CE2")));
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

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);
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
                Intent toTheSame = new Intent(this, NewMatch.class);
                startActivity(toTheSame);
                break;
            case R.id.id_frFlag:
                LocaleHelper.setLocale(this, "fr");
                toTheSame = new Intent(this, NewMatch.class);
                startActivity(toTheSame);
                break;
        }
        return false;
    }

    public void toTheMatch(View view) {
        Intent toTheMatch = new Intent(this,TheMatch.class);
        startActivity(toTheMatch);
    }

    public void toListMatch(View view) {
        Intent toListMatch = new Intent(this,MatchList.class);
        startActivity(toListMatch);
    }



        public void onClickRegister(View view) {

            SQLiteHelper helper = new SQLiteHelper(this);

            SQLiteDatabase db = helper.getWritableDatabase();

            EditText Date, Heure, Stade, Resident, Visiteur, Quantite;
            //ToggleButton Statut;

            Date = (EditText) findViewById(R.id.idEditDate);
            Heure = (EditText) findViewById(R.id.idHeure);
            Stade = (EditText) findViewById(R.id.idStade);
            Resident = (EditText) findViewById(R.id.idResident);
            Visiteur = (EditText) findViewById(R.id.idVisiteur);
            //Statut = (ToggleButton) findViewById(R.id.idStatut);
            Quantite = (EditText) findViewById(R.id.idQuantite);

            Game game = new Game();
            //Stade stade = new Stade();

            game.setDate(Date.getText().toString());
            game.setHeure(Heure.getText().toString());
            //game.setStade(Stade.getText().toString());
            game.setTeam_res(Resident.getText().toString());
            game.setTeam_ext(Visiteur.getText().toString());
            //game.setStatut(Statut.getText().toString());

            game.setQuantity(Quantite.getText().toString());

            GameDataSource gds = new GameDataSource(context);
            gds.createGame(game);

            /*Intent intent = new Intent(NewMatch.this, MainActivity.class);
            startActivity(intent);
    */

            Intent toListMatch = new Intent(this,MatchList.class);
            toListMatch.putExtra("idGame", idGame);
            toListMatch.putExtra("idCustomer", idCustomer);
            startActivity(toListMatch);

    }
/*
    // pour la date
    public void showDatePickerDialog(View view){
        DialogFragment fragment = new DatePickerFragment();
        fragment.show(fragment.getFragmentManager(),"date");
    }*/
}
