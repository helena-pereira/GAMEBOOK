package com.example.helena.gamebook;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;


/**
 * Created by Stéphanie Pinto on 27.11.2016.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener  {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day){
        // Formater la date
        TextView tv1= (TextView) getActivity().findViewById(R.id.idEditDate);
        tv1.setText(view.getYear()+" : "+view.getMonth()+" : "+view.getDayOfMonth());

    }
}