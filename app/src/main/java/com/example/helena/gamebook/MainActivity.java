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
        SQLiteHelper helper = new SQLiteHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        TextView email = (TextView)findViewById(R.id.email) ;
        TextView mdp = (TextView)findViewById(R.id.password);

        CustomerDataSource cds = new CustomerDataSource(context);
        Customer customer = null;
        customer = cds.getCustomerByEmail(email.getText().toString());

        if(customer != null)
        {
            if(email.equals(customer.getEmail().toString()) && mdp.equals(customer.getMdp().toString()))
            {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        }

       /* customer.setEmail(email.getText().toString());
        customer.setMdp(mdp.getText().toString());

        String theEmail = customer.setEmail(email.getText().toString());

        cds.getCustomerByEmail(email)

           */


    }



}
