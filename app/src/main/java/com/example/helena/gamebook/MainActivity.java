package com.example.helena.gamebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //j'essaie d'écire quelque chose

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void toMatch(View view) {
        Intent toMatch = new Intent(this,Register.class);
        startActivity(toMatch);

    }
}
