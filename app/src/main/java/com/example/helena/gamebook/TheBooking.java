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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TheBooking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_booking);

        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.football);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6C7CE2")));
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_basic,menu);
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

    public void toEditBooking(View view) {
        Intent toEditBooking = new Intent(this,EditBooking.class);
        startActivity(toEditBooking);
    }

    //suppression de la base de donnée
    public void toListBooking(View view) {
        Intent toListBooking = new Intent(this,ListOfBooking.class);
        startActivity(toListBooking);
    }

    public void toDelete(View view){
        AlertDialog.Builder alertDeleteBooking = new AlertDialog.Builder(this);
        // Le titre du Dialog Alert
        alertDeleteBooking.setTitle(R.string.deleteBookingTitle);

        // Message du Dialog Alert
        alertDeleteBooking.setMessage(R.string.deleteBookingMessage);

        // Icon de suppression
        alertDeleteBooking.setIcon(R.mipmap.delete);

        // Si on clique sur oui
        alertDeleteBooking.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {

                /*
                Helena : suppression de la base de données
                Si on clique sur oui on supprime de la base de données
                et on retourne dans la liste des réservations
                 */
                dialog.cancel();
                Intent toListBooking = new Intent(TheBooking.this,ListOfBooking.class);
                startActivity(toListBooking);
            }
        });

        // Si on clique sur non
        alertDeleteBooking.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // ça ferme tout simplemet le dialog
                dialog.cancel();
            }
        });
        // On montre l'alerte
        alertDeleteBooking.show();
    }

    private void updateViews() {
        Resources resources = getResources();

        TextView TheBooking = (TextView) findViewById(R.id.idNameMatch);
        TextView LabelGame = (TextView) findViewById(R.id.LabelGame);
        TextView LabelClientName = (TextView) findViewById(R.id.LabelClientName);
        TextView LabelSeat = (TextView) findViewById(R.id.LabelSeat);
        Button delete = (Button) findViewById(R.id.buttonCancel);
        Button edit = (Button) findViewById(R.id.buttonEditBooking);

        TheBooking.setContentDescription(resources.getString(R.string.TheBooking));
        LabelGame.setContentDescription(resources.getString(R.string.game));
        LabelClientName.setContentDescription(resources.getString(R.string.ClientName));
        LabelSeat.setContentDescription(resources.getString(R.string.seat));
        delete.setContentDescription(resources.getString(R.string.delete));
        edit.setContentDescription(resources.getString(R.string.editBooking));
    }


}
