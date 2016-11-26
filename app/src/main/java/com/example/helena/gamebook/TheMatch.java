package com.example.helena.gamebook;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/***
 * Created by Stéphanie Pinto
 * Cette classe affiche le match selectionné dans la liste des matchs
 */

public class TheMatch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_match);

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

    public void toNewBooking(View view) {
        Intent toNewBooking = new Intent(this,NewBooking.class);
        startActivity(toNewBooking);
    }

    public void toEditMatch(View view) {
        Intent toEditMatch = new Intent(this,EditMatch.class);
        startActivity(toEditMatch);
    }

    //suppression de la base de donnée
    public void toListMatch(View view) {
        Intent toListMatch = new Intent(this,MatchList.class);
        startActivity(toListMatch);
    }


    public void toDelete(View view){
        AlertDialog.Builder alertDeleteBooking = new AlertDialog.Builder(this);
        // Setting Dialog Title
        alertDeleteBooking.setTitle(R.string.deleteMatchTitle);

        // Setting Dialog Message
        alertDeleteBooking.setMessage(R.string.deleteMatchMessage);

        // Setting Icon to Dialog
        alertDeleteBooking.setIcon(R.mipmap.delete);

        // Setting Positive "Yes" Button
        alertDeleteBooking.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {

                // Write your code here to invoke YES event
                dialog.cancel();
                Intent toListMatch = new Intent(TheMatch.this,MatchList.class);
                startActivity(toListMatch);
            }
        });

        // Setting Negative "NO" Button
        alertDeleteBooking.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event
                dialog.cancel();
            }
        });
        // Showing Alert Message
        alertDeleteBooking.show();
    }

    private void updateViews() {
        Resources resources = getResources();

        TextView NameMatch = (TextView) findViewById(R.id.idNameMatch);
        TextView LabelDate = (TextView) findViewById(R.id.LabelDate);
        TextView LabelHeure = (TextView) findViewById(R.id.LabelHeure);
        TextView LabelStade = (TextView) findViewById(R.id.LabelStade);
        TextView LabelResident = (TextView) findViewById(R.id.LabelResident);
        TextView LabelVisiteur = (TextView) findViewById(R.id.LabelVisiteur);
        TextView LabelStatut = (TextView) findViewById(R.id.LabelStatut);
        TextView LabelQuantite = (TextView) findViewById(R.id.LabelQuantite);
        Button buttonDelete = (Button) findViewById(R.id.buttonDelete);
        Button buttonEdit = (Button) findViewById(R.id.buttonEdit);

        NameMatch.setContentDescription(resources.getString(R.string.TheMatch));
        LabelDate.setContentDescription(resources.getString(R.string.date));
        LabelHeure.setContentDescription(resources.getString(R.string.heure));
        LabelStade.setContentDescription(resources.getString(R.string.stade));
        LabelResident.setContentDescription(resources.getString(R.string.resident));
        LabelVisiteur.setContentDescription(resources.getString(R.string.visiteur));
        LabelStatut.setContentDescription(resources.getString(R.string.statut));
        LabelQuantite.setContentDescription(resources.getString(R.string.quantite));
        buttonDelete.setContentDescription(resources.getString(R.string.delete));
        buttonEdit.setContentDescription(resources.getString(R.string.edit));
    }


}
