package com.example.helena.gamebook;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.helena.gamebook.db.FeedReaderContract;
import com.example.helena.gamebook.db.SQLiteHelper;
import com.example.helena.gamebook.db.adapter.GameDataSource;
import com.example.helena.gamebook.db.object.Game;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;

public class TEST_MATCHS extends AppCompatActivity {

    Context context;
    private ListView choiceList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test__matchs);

        Intent intent = getIntent();
        context = this;

        this.getSupportActionBar().setHomeButtonEnabled(false);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.getSupportActionBar().setDisplayUseLogoEnabled(true);
        this.getSupportActionBar().setLogo(R.mipmap.football);
        this.getSupportActionBar().setDisplayShowTitleEnabled(true);
        //this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6C7CE2")));
        //readSQL();

        read();

    }

    private void read() {
        GameDataSource gds = new GameDataSource(context);

        Game newGame = new Game();
        newGame = gds.getGameById(1);
        String a = newGame.getDate().toString();

        TextView v ;

        v = (TextView)findViewById(R.id.textView2);

        v.setText(a);



    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);
        return super.onCreateOptionsMenu(menu);
    }


    //Load the data from the table choice
    public void readSQL() {
        SQLiteHelper mDbHelper = new SQLiteHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] choiceListDescritption = new String[]{
                FeedReaderContract.tableGAME.GAME_ID,
                //FeedReaderContract.tableGAME.GAME_STADE,
                FeedReaderContract.tableGAME.GAME_DATE,
                FeedReaderContract.tableGAME.GAME_HEURE
        };

        Cursor cursor = db.query(false, FeedReaderContract.tableGAME.TABLE_NAME, choiceListDescritption,null, null, null, null, null, null);
        ArrayList<String> array = new ArrayList<String>();

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            array.add(cursor.getString(0));
            cursor.moveToNext();
        }

        db.close();
        generateChoiceList(array);
    }

    public void generateChoiceList(ArrayList<String> array) {

        Collections.sort(array);

        //choiceList = (ListView) findViewById(R.id.Choice_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.simple_list_view, R.id.tv_list, array);

        choiceList.setAdapter(adapter);

    }
}
