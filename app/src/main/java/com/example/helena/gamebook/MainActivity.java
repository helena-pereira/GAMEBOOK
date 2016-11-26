package com.example.helena.gamebook;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helena.gamebook.db.SQLiteHelper;
import com.example.helena.gamebook.db.adapter.CustomerDataSource;
import com.example.helena.gamebook.db.object.Customer;

public class MainActivity extends AppCompatActivity {


    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_basic,menu);
        return super.onCreateOptionsMenu(menu);

    }


    /**Create by Helena 26.11.2016
     * Faire le login
     * @param view
     */
    public void toRegister(View view) {
        Intent toRegister = new Intent(this,Register.class);
        startActivity(toRegister);
    }

    public void goToNewMatch(View view){
        Intent goToNewMatch = new Intent(this,NewMatch.class);
        startActivity(goToNewMatch);
    }





    public void signIn(View view){
        /*SQLiteHelper helper = new SQLiteHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        TextView email = (TextView)findViewById(R.id.email) ;
        TextView mdp = (TextView)findViewById(R.id.password);

        Customer customer = new Customer();
        customer.setId(1);
        customer.setEmail(email.getText().toString());
        customer.setMdp(mdp.getText().toString());

        CustomerDataSource cds = new CustomerDataSource(getApplicationContext());

        cds.getCustomerById(1);*/


    }


    //marcus
    public void login(View view) {
        String admin1="helder";
        String admin2="marc";
        String driver="driver";
        EditText login  =(EditText)findViewById(R.id.ListMatch);
        String log = login.getText().toString();

        if(log.equals(admin1)||log.equals(admin2)){
            Intent intentAdmin = new Intent(this,home.class);
            startActivity(intentAdmin);
            if(LocaleHelper.getLanguage(context)!="fr"){
                Toast.makeText(getApplicationContext(),"Welcome "+log+" to the Admin Page.",Toast.LENGTH_SHORT).show();}
            else{Toast.makeText(getApplicationContext(),"Bienvenue "+log+" a la page administrative.",Toast.LENGTH_SHORT).show();}
        }else if(log.equals(driver)){
            Intent intentDriver = new Intent(this,home.class);
            startActivity(intentDriver);
            if(LocaleHelper.getLanguage(context)!="fr"){
                Toast.makeText(getApplicationContext(),"Welcome "+log+" to your Driver Page.",Toast.LENGTH_SHORT).show();
            }
            else{Toast.makeText(getApplicationContext(),"Bienvenue "+log+" a votre page chauffeur.",Toast.LENGTH_SHORT).show();}
        }else{
            if(LocaleHelper.getLanguage(context)!="fr"){
                Toast.makeText(getApplicationContext(), "Your Username or Password is not correct!", Toast.LENGTH_SHORT).show();

            }else{Toast.makeText(getApplicationContext(), "Votre nom et/ou votre mot de passe n'est pas correct!", Toast.LENGTH_SHORT).show();
            }
        }


    }
}
