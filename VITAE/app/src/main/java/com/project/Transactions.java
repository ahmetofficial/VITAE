// Developer: Ahmet Kaymak
// Date: 25.02.2016

package com.project;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transactions {

    public static Date stringToDate(String aDate, String aFormat) {

        if(aDate==null) return null;
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat simpledateformat = new SimpleDateFormat(aFormat);
        Date stringDate = simpledateformat.parse(aDate, pos);
        return stringDate;

    }
}
