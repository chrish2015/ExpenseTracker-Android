package asc.msc.coursework.com.expensetracker.util;

import java.util.ArrayList;
import java.util.Arrays;

public class Util {

    public ArrayList<Integer> getDate(int day, int month, int year) {
        return new ArrayList<Integer>(Arrays.asList(day, month, year));
    }

    public String getDateString(ArrayList<Integer> date) {
        int month = date.get(1) + 1;
        String monthString=month<10?"0" + String.valueOf(month):String.valueOf(month);
        Integer dateVal = date.get(0);
        String dateString= dateVal <10?"0" + String.valueOf(dateVal):String.valueOf(dateVal);
        return monthString + "/" + dateString + "/" + date.get(2);
    }
}
