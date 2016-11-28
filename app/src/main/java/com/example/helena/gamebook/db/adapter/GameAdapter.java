package com.example.helena.gamebook.db.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.helena.gamebook.R;
import com.example.helena.gamebook.db.object.Game;

import java.util.List;

/**
 * Created by Helena on 26.11.2016.
 */

public class GameAdapter extends ArrayAdapter<Game>{

    private final Context context;
    private final List<Game> games;


    public GameAdapter(Context context, List<Game> games) {
        super(context, -1, games);
        this.context = context;
        this.games = games;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.simple_list_view, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.tv_list);
        textView.setText(games.get(position).getTeam_res()+ " vs. " + games.get(position).getTeam_ext() + " - " + games.get(position).getDate());
        return rowView;
    }
}
