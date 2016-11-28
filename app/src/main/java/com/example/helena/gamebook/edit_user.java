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


    public void goHome(View view){
        Intent back = new Intent(this,home.class);
        back.putExtra("idCustomer", idCustomer);
        startActivity(back);
    }


    private void loadCustomer() {
        CustomerDataSource cds = new CustomerDataSource(context);
        Customer customer = new Customer();

        customer = cds.getCustomerById(idCustomer);

        editName.setText(customer.getNom());
        editFirstname.setText(customer.getPrenom());
        editEmail.setText(customer.getEmail());
        editPassword.setText(customer.getMdp());

    }


    // Enregistrement des modifications
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
