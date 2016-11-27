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

    Integer idCustomer ;
    Bundle bundle;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.football);
        getSupportActionBar().setDisplayShowTitleEnabled(true);


        /*
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
*/



    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);

    }



    /********************************************************/
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


    public void goHome(View view){
        Intent goHome = new Intent(this,home.class);
        startActivity(goHome);
    }



    public void signIn(View view) {

        EditText email = (EditText) findViewById(R.id.email);
        EditText mdp = (EditText) findViewById(R.id.password);

        Customer customer1 = new Customer();
        customer1.setEmail(email.getText().toString());
        customer1.setMdp(mdp.getText().toString());

        Customer customer2 = new Customer();
        CustomerDataSource cds = new CustomerDataSource(context);
        customer2 = cds.getCustomerByEmail(email.getText().toString());

        String customer2Email = customer2.getEmail();
        String customer2Mdp = customer2.getMdp();
        idCustomer = customer2.getId();

        if(email.getText().toString().trim().length() == 0 || mdp.getText().toString().trim().length() == 0){
            //Message qui dit qu'il faut remplir les champs obligatoires : login et mdp
            Toast.makeText(getApplicationContext(), R.string.MessageNoLogin,
                    Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(customer1.getEmail().toString().equals(customer2Email)
                    && customer1.getMdp().toString().equals(customer2Mdp)){
                Intent toHome = new Intent(this,home.class);
                toHome.putExtra("idCustomer", idCustomer);
                startActivity(toHome);
            }
            else
            {
                //Message qui dit que le login ou le mdp est incorrect
                Toast.makeText(getApplicationContext(), R.string.MessageLoginIncorrect,
                        Toast.LENGTH_SHORT).show();
            }
        }
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
