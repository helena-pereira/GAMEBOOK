package com.example.helena.gamebook;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.CollapsibleActionView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class user extends AppCompatActivity {

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        context = this;

        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.football);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6C7CE2")));
    }



    public void returnMainActivity(View view){
        Intent returnMainActivity = new Intent(this,MainActivity.class);
        startActivity(returnMainActivity);

    }


    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_basic,menu);
        return super.onCreateOptionsMenu(menu);



    }




    //Merci de faire le nécessaire steph
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
