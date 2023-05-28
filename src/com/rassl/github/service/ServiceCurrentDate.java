package com.rassl.github.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class allows get and change  current date
 */


public class ServiceCurrentDate {

    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate date = LocalDate.now();

    public static String getDate() {
        return date.format(FORMAT);
    }

    public static void setDate(String dateString) {
        date = LocalDate.parse(dateString, FORMAT);
    }
}
