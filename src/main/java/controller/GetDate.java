package controller;

import java.time.LocalDate;

public class GetDate {
    public static String getDate(){
        LocalDate now = LocalDate.now();
        return now.toString();
    }
}
