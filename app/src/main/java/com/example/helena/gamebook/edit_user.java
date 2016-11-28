package com.example.helena.gamebook;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
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

import com.example.helena.gamebook.db.adapter.CustomerDataSource;
import com.example.helena.gamebook.db.adapter.GameDataSource;
import com.example.helena.gamebook.db.object.Customer;
import com.example.helena.gamebook.db.object.Game;

public class edit_user extends AppCompatActivity {
    Integer idCustomer ;
    Bundle bundle;
    Context context;

    EditText editName, editFirstname, editEmail, editPassword, idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        context = this;

        // paramètres de la nav bar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.football);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        editName = (EditText)findViewById(R.id.EditName);
        editFirstname = (EditText)findViewById(R.id.EditFirstName);
        editEmail = (EditText)findViewById(R.id.EditEmail);
        editPassword = (EditText)findViewById(R.id.EditPassword);

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

    // sélection du menu adéquat
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

                        Intent toMain = new Intent(edit_user.this, MainActivity.class);
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
                Intent toTheSame = new Intent(edit_user.this, edit_user.class);
                toTheSame.putExtra("idCustomer", idCustomer);
                startActivity(toTheSame);
                break;
            case R.id.id_frFlag:
                LocaleHelper.setLocale(this, "fr");
                toTheSame = new Intent(edit_user.this, edit_user.class);
                toTheSame.putExtra("idCustomer", idCustomer);
                startActivity(toTheSame);
                break;
        }
        return false;
    }


    // redirection vers l'accueil
    public void goHome(View view){
        Intent back = new Intent(this,home.class);
        back.putExtra("idCustomer", idCustomer);
        startActivity(back);
    }

    // modification du customer
    private void loadCustomer() {
        CustomerDataSource cds = new CustomerDataSource(context);
        Customer customer = new Customer();

        customer = cds.getCustomerById(idCustomer);

        editName.setText(customer.getNom());
        editFirstname.setText(customer.getPrenom());
        editEmail.setText(customer.getEmail());
        editPassword.setText(customer.getMdp());

    }


    // Alert Dialog pour l'enregistrement des modifications
    public void toSave(View view){
        AlertDialog.Builder alertDeleteBooking = new AlertDialog.Builder(this);
        // Le titre du Dialog Alert
        alertDeleteBooking.setTitle(R.string.RegisterUserTitle);

        // Message du Dialog Alert
        alertDeleteBooking.setMessage(R.string.RegisterUserMessage);

        // Icon de suppression
        alertDeleteBooking.setIcon(R.mipmap.update);

        // Si on clique sur oui
        alertDeleteBooking.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {

                //On modifie
                editName = (EditText)findViewById(R.id.EditName);
                editFirstname = (EditText)findViewById(R.id.EditFirstName);
                editEmail = (EditText)findViewById(R.id.EditEmail);
                editPassword = (EditText)findViewById(R.id.EditPassword);

                Customer customer = new Customer();

                customer.setId(idCustomer);
                customer.setNom(editName.getText().toString());
                customer.setPrenom(editFirstname.getText().toString());
                customer.setEmail(editEmail.getText().toString());
                customer.setMdp(editPassword.getText().toString());

                CustomerDataSource cds = new CustomerDataSource(context);
                cds.updateCustomer(customer);

                Intent back = new Intent(edit_user.this,home.class);
                back.putExtra("idCustomer", idCustomer);
                startActivity(back);

                //et on retourne au home
                dialog.cancel();
                Intent toHome = new Intent(edit_user.this,home.class);
                toHome.putExtra("idCustomer", idCustomer);
                startActivity(toHome);
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

    // Alert Dialog pour la suppression du compte utilisateur
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
                CustomerDataSource cds = new CustomerDataSource(context);
                cds.deleteCustomer(idCustomer);

                dialog.cancel();
                Intent toMain = new Intent(edit_user.this,MainActivity.class);
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

}
