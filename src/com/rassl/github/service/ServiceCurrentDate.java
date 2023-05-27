package com.rassl.github.service;

import java.time.LocalDate;

import static com.rassl.github.config.Constants.FORMAT;

public class ServiceCurrentDate {
    public static LocalDate date = LocalDate.now();

    public static String getDate() {
        return date.format(FORMAT);
    }

    public static void setDate(String dateString) {
        date = LocalDate.parse(dateString, FORMAT);
    }
}
