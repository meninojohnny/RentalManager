package com.johnny.utilitarie;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    
    public static String dateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM");
        return format.format(date);
    }
}