package com.example.helena.gamebook;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

    public void goToAllMatch(View view){
        Intent goToAllMatch = new Intent(this,MatchList.class);
        startActivity(goToAllMatch);

    }

    public void signIn(View view) {
        SQLiteHelper helper = new SQLiteHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        TextView email = (TextView) findViewById(R.id.email);
        TextView mdp = (TextView) findViewById(R.id.password);

        CustomerDataSource cds = new CustomerDataSource(context);
        Customer customer = null;
        customer = cds.getCustomerByEmail(email.getText().toString());

        if (customer != null) {
            if (email.equals(customer.getEmail().toString()) && mdp.equals(customer.getMdp().toString())) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            } else {
                //Message qui dit que le login ou le mdp est incorrect
                Toast.makeText(getApplicationContext(), R.string.MessageLoginIncorrect,
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            //Message qui dit qu'il faut remplir les champs obligatoires : login et mdp
            Toast.makeText(getApplicationContext(), R.string.MessageNoLogin,
                    Toast.LENGTH_SHORT).show();
        }

       /* customer.setEmail(email.getText().toString());
        customer.setMdp(mdp.getText().toString());

        String theEmail = customer.setEmail(email.getText().toString());

        cds.getCustomerByEmail(email)

           */
    }

    private void updateViews() {
        Resources resources = getResources();

        AutoCompleteTextView email = (AutoCompleteTextView)findViewById(R.id.email);
        EditText password = (EditText)findViewById(R.id.password);
        Button action_sign_in = (Button) findViewById(R.id.email_sign_in_button);
        Button register_button = (Button) findViewById(R.id.register_button);

        email.setContentDescription(resources.getString(R.string.email));
        password.setContentDescription(resources.getString(R.string.password));
        action_sign_in.setContentDescription(resources.getString(R.string.action_sign_in));
        register_button.setContentDescription(resources.getString(R.string.register_button));
    }

}
