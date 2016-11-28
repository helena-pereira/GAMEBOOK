package com.example.helena.gamebook.db.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.helena.gamebook.R;
import com.example.helena.gamebook.db.object.Booking;
import com.example.helena.gamebook.db.object.Game;

import java.util.List;

/**
 * Created by Helena on 28.11.2016.
 */

public class BookingAdapter extends ArrayAdapter<Booking> {

    private final Context context;
    private final List<Booking> bookings;


    public BookingAdapter(Context context, List<Booking> bookings) {
        super(context, -1 , bookings);
        this.context = context;
        this.bookings = bookings;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.simple_list_view, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.tv_list);
        textView.setText(bookings.get(position).getNum_seat()+ " vs. " + bookings.get(position).getCustomer().getNom() + " - " /*+ games.get(position).getStade()*/);
        return rowView;
    }
}
