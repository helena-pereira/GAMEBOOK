package com.example.helena.gamebook;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.CollapsibleActionView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.helena.gamebook.db.adapter.CustomerDataSource;
import com.example.helena.gamebook.db.adapter.GameDataSource;
import com.example.helena.gamebook.db.object.Customer;
import com.example.helena.gamebook.db.object.Game;


public class user extends AppCompatActivity {
    Integer idCustomer ;
    Bundle bundle;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        context = this;

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.football);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6C7CE2")));

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



        loadCustomer();




    }

    private void loadCustomer() {


        CustomerDataSource cds = new CustomerDataSource(context);
        Customer customer = new Customer();

        TextView idUser, idHeure, idStade, idResident, idVisiteur, idStatut, idQuantite, idNameMatch ;

        idUser = (TextView)findViewById(R.id.idUser);
        customer = cds.getCustomerById(idCustomer);

        idUser.setText(customer.getPrenom() + " " + customer.getNom());






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
                toMain.putExtra("idCustomer", idCustomer);
                startActivity(toMain);
                break;
            case R.id.id_enFlag:
                LocaleHelper.setLocale(this, "en");
                updateViews();
                break;
            case R.id.id_frFlag:
                LocaleHelper.setLocale(this, "fr");
                updateViews();

                break;
        }
        return false;
    }


    //déconnexion
    public void toLogOut(View view){
        AlertDialog.Builder alertDeleteBooking = new AlertDialog.Builder(this);
        // Le titre du Dialog Alert
        alertDeleteBooking.setTitle(R.string.LogOutUserTitle);

        // Message du Dialog Alert
        alertDeleteBooking.setMessage(R.string.LogOutUserMessage);

        // Icon de suppression
        alertDeleteBooking.setIcon(R.mipmap.delete);

        // Si on clique sur oui
        alertDeleteBooking.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {

                //On se déconnecte et retourne au main
                dialog.cancel();
                Intent toMain = new Intent(user.this,MainActivity.class);
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
    }

    //suppression du compte utilisateur
    public void toDelete(View view){
        AlertDialog.Builder alertDeleteBooking = new AlertDialog.Builder(this);
        // Le titre du Dialog Alert
        alertDeleteBooking.setTitle(R.string.deleteUserTitle);

        // Message du Dialog Alert
        alertDeleteBooking.setMessage(R.string.deleteUserMessage);

        // Icon de suppression
        alertDeleteBooking.setIcon(R.mipmap.delete);

        // Si on clique sur oui
        alertDeleteBooking.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {

                /*
                Helena : suppression de la base de données
                Si on clique sur oui on supprime de la base de données
                et on retourne dans le main
                 */
                dialog.cancel();
                Intent toMain = new Intent(user.this,MainActivity.class);
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
    }

    private void updateViews() {
        Resources resources = getResources();

        TextView idUser = (TextView)findViewById(R.id.idUser);
        TextView LabelName = (TextView) findViewById(R.id.LabelName);
        TextView LabelFirstName = (TextView) findViewById(R.id.LabelFirstName);
        TextView LabelEmail = (TextView) findViewById(R.id.LabelEmail);
        TextView LabelPassword = (TextView) findViewById(R.id.LabelPassword);
        Button buttonEdit = (Button) findViewById(R.id.buttonEdit);
        Button buttonDelete = (Button) findViewById(R.id.buttonDelete);

        idUser.setContentDescription(resources.getString(R.string.idUser));
        LabelName.setContentDescription(resources.getString(R.string.name));
        LabelFirstName.setContentDescription(resources.getString(R.string.firstName));
        LabelEmail.setContentDescription(resources.getString(R.string.email));
        LabelPassword.setContentDescription(resources.getString((R.string.password)));
        buttonEdit.setContentDescription(resources.getString((R.string.edit)));
        buttonDelete.setContentDescription(resources.getString((R.string.delete)));
    }
}
