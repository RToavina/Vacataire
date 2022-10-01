package com.itu.vacataire.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CalendarUtils {

    public static LocalDate stringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }

    public static LocalTime stringToLocaTime(String hour) {
        LocalTime localTime= null;
        try {
            localTime = LocalTime.parse(hour, DateTimeFormatter.ofPattern("kk:mm"));
        }catch (DateTimeParseException e) {
            localTime = LocalTime.parse(hour,  DateTimeFormatter.ofPattern("kk:mm:ss"));
        }
        return localTime;
    }
}
