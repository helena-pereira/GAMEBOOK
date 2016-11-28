package com.example.helena.gamebook;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.IdRes;
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

import com.example.helena.gamebook.db.SQLiteHelper;
import com.example.helena.gamebook.db.adapter.CustomerDataSource;
import com.example.helena.gamebook.db.object.Customer;


/**
 * Created by Helena 26.11.2016
 * Cette classe permet l'enregistrement d'un nouveau Customer
 */

public class Register extends AppCompatActivity {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context = this;

        // paramètres de la bar d'action
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.football);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    // sélection du menu adéquat
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    // SDP :refresh lors du changement de langue
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId()) {
            case R.id.id_enFlag:
                LocaleHelper.setLocale(this, "en");
                Intent toTheSame = new Intent(Register.this, Register.class);
                startActivity(toTheSame);
                break;
            case R.id.id_frFlag:
                LocaleHelper.setLocale(this, "fr");
                toTheSame = new Intent(Register.this, Register.class);
                startActivity(toTheSame);
                break;
        }
        return false;
    }

    // retour dans le Main (page de login)
    public void returnMainActivity(View view) {
        Intent returnMainActivity = new Intent(this,MainActivity.class);
        startActivity(returnMainActivity);
    }

    // enregistrement du nouvel utilisateur
    public void onClickRegister(View view){

        EditText Nom, Prenom, Email, Mdp, ConfirmMdp;

        Nom = (EditText)findViewById(R.id.EditLastName);
        Prenom = (EditText)findViewById(R.id.EditFirstName);
        Email = (EditText)findViewById(R.id.EditEmail);
        Mdp = (EditText)findViewById(R.id.EditPassword);
        ConfirmMdp = (EditText)findViewById(R.id.EditPasswordConfirm);


        if(Email.getText().toString().trim().length()>0 && Mdp.getText().toString().trim().length()>0 && ConfirmMdp.getText().toString().trim().length()>0){
            if(Mdp.getText().toString().equals(ConfirmMdp.getText().toString()))
            {
                Customer customer = new Customer();

                customer.setNom(Nom.getText().toString());
                customer.setPrenom(Prenom.getText().toString());
                customer.setEmail(Email.getText().toString());
                customer.setMdp(Mdp.getText().toString());

                CustomerDataSource cds = new CustomerDataSource(context);
                cds.createCustomer(customer);

                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
            }
            else
            {
                /* SDP Mdp n'est pas égal avec la confirmation de Mdp
                **/
                    Toast.makeText(getApplicationContext(), R.string.MessageRegisterPassword,
                            Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            /* SDP Mdp ne correspond pas aux critères
            Votre mot de passe ou email ne correspond pas aux critères **/
            Toast.makeText(getApplicationContext(), R.string.MessageRegisterIncorrecte,
                    Toast.LENGTH_SHORT).show();
        }
    }
}
