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

        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.football);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
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

    public void returnMainActivity(View view) {
        Intent returnMainActivity = new Intent(this,MainActivity.class);
        startActivity(returnMainActivity);

    }




    public void onClickRegister(View view){
        //SQLiteHelper helper = new SQLiteHelper(this);
        //SQLiteDatabase db = helper.getWritableDatabase();

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
                /*STEPHANIE Mdp ne correspond pas aux critère
                Votre mot de passe ou email ne correspond pas aux critères
                **/
                    Toast.makeText(getApplicationContext(), R.string.MessageRegisterPassword,
                            Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), R.string.MessageRegisterIncorrecte,
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void updateViews() {
        Resources resources = getResources();

        TextView id_new_user = (TextView)findViewById(R.id.id_new_user);
        TextView LabelName = (TextView) findViewById(R.id.LabelName);
        TextView LabelPrenom = (TextView) findViewById(R.id.EditFirstName);
        TextView LabelEmail = (TextView) findViewById(R.id.LabelEmail);
        TextView LabelPassword = (TextView) findViewById(R.id.LabelPassword);
        TextView EditPasswordConfirm = (TextView) findViewById(R.id.EditPasswordConfirm);
        Button buttonReturn = (Button) findViewById(R.id.buttonReturn);
        Button buttonEdit = (Button) findViewById(R.id.buttonEdit);

        id_new_user.setContentDescription(resources.getString(R.string.id_new_user));
        LabelName.setContentDescription(resources.getString(R.string.name));
        LabelPrenom.setContentDescription(resources.getString(R.string.firstName));
        LabelEmail.setContentDescription(resources.getString(R.string.email));
        LabelPassword.setContentDescription(resources.getString((R.string.password)));
        EditPasswordConfirm.setContentDescription(resources.getString((R.string.Confirmpassword)));
        buttonReturn.setContentDescription(resources.getString((R.string.retour)));
        buttonEdit.setContentDescription(resources.getString((R.string.enregistrer)));
    }

}
