package com.rassl.github.Const;

import java.time.format.DateTimeFormatter;

public class Constants {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String CONFIG_FILE = "C:\\Users\\rassl\\IdeaProjects\\fileSystem\\Config\\config.properties";

    private Constants() {
    }

    public static DateTimeFormatter getFormat() {
        return FORMAT;
    }

    public static String GetConfigFile() {
        return CONFIG_FILE;
    }
}
