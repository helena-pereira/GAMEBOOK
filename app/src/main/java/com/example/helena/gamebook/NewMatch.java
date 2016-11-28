package com.example.helena.gamebook;

import android.app.DialogFragment;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
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

                        Intent toMain = new Intent(NewMatch.this, MainActivity.class);
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
                Intent toTheSame = new Intent(NewMatch.this, NewMatch.class);
                toTheSame.putExtra("idGame", idGame);
                toTheSame.putExtra("idCustomer", idCustomer);
                startActivity(toTheSame);
                break;
            case R.id.id_frFlag:
                LocaleHelper.setLocale(this, "fr");
                toTheSame = new Intent(NewMatch.this, NewMatch.class);
                toTheSame.putExtra("idGame", idGame);
                toTheSame.putExtra("idCustomer", idCustomer);
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
        toListMatch.putExtra("idCustomer", idCustomer);
        startActivity(toListMatch);
    }

    public void onClickRegister(View view) {

            EditText Date, Heure, Stade, Resident, Visiteur, Quantite;

            Date = (EditText) findViewById(R.id.idEditDate);
            Heure = (EditText) findViewById(R.id.idHeure);
            Stade = (EditText) findViewById(R.id.idStade);
            Resident = (EditText) findViewById(R.id.idResident);
            Visiteur = (EditText) findViewById(R.id.idVisiteur);
            Quantite = (EditText) findViewById(R.id.idQuantite);

        if(Date.getText().toString().trim().length() > 0 &&
                Heure.getText().toString().trim().length() > 0 &&
                Stade.getText().toString().trim().length() > 0 &&
                Resident.getText().toString().trim().length() > 0 &&
                Visiteur.getText().toString().trim().length() > 0 &&
                Quantite.getText().toString().trim().length() > 0)
        {
            Game game = new Game();
            //Stade stade = new Stade();

            game.setDate(Date.getText().toString());
            game.setHeure(Heure.getText().toString());
            //game.setStade(Stade.getText().toString());
            game.setTeam_res(Resident.getText().toString());
            game.setTeam_ext(Visiteur.getText().toString());

            game.setQuantity(Quantite.getText().toString());

            GameDataSource gds = new GameDataSource(context);
            gds.createGame(game);

            Intent toListMatch = new Intent(this,MatchList.class);
            toListMatch.putExtra("idGame", idGame);
            toListMatch.putExtra("idCustomer", idCustomer);
            startActivity(toListMatch);
        }
        else
        {
            /* SDP : il faut remplir tous les champs obligatoires
                **/
            Toast.makeText(getApplicationContext(), R.string.MessageNewMatch,
                    Toast.LENGTH_SHORT).show();
        }
    }
}
