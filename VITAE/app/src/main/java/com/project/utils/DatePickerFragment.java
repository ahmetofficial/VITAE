package com.project.utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private TextView birthdayText;
    private Date date;

    public DatePickerFragment(TextView birthdayText,Date date) {
        this.birthdayText=birthdayText;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get( Calendar.YEAR ) - 2000;
        int month = c.get( Calendar.MONTH );
        int day = c.get( Calendar.DAY_OF_MONTH );
        return new DatePickerDialog( getActivity(), this, year, month, day );
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        date = new Date(new GregorianCalendar( year,month,day ).getTime().getTime());
        birthdayText.setText( String.valueOf(day)+"."+String.valueOf(month)+"."+String.valueOf(year) );
    }

    public Date getDate() {
        return date;
    }
}