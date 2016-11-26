package com.example.helena.gamebook;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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


    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_basic,menu);
        return super.onCreateOptionsMenu(menu);

    }

    public void returnMainActivity(View view) {
        Intent returnMainActivity = new Intent(this,MainActivity.class);
        startActivity(returnMainActivity);

    }




    public void onClickRegister(View view){
        SQLiteHelper helper = new SQLiteHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        EditText Nom, Prenom, Email, Mdp, ConfirmMdp;

        Nom = (EditText)findViewById(R.id.EditLastName);
        Prenom = (EditText)findViewById(R.id.EditFirstName);
        Email = (EditText)findViewById(R.id.EditEmail);
        Mdp = (EditText)findViewById(R.id.EditPassword);
        ConfirmMdp = (EditText)findViewById(R.id.EditPasswordConfirm);

        if(Mdp.equals(ConfirmMdp) || Mdp != null){
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
        else{


            /*STEPHANIE ici il faut mettre une boite de dialog qui à l'utilisaqteur
            que son mdp ne correspond pas aux critère..blablalbla

            Votre mot de passe ou email ne correspond pas aux critères

            Merci
            **/


            Intent refresh = new Intent(Register.this, Register.class);
            startActivity(refresh);
            this.finish();
        }






    }

}
