package com.rassl.github.Service;

import java.time.LocalDate;

import static com.rassl.github.Const.Constants.getFormat;

public class ServiceCurrentDate {
    public static LocalDate date = LocalDate.now();

    public static String getDate() {
        return date.format(getFormat());
    }

    public static void setDate(String dateString) {
        date = LocalDate.parse(dateString, getFormat());
    }
}
