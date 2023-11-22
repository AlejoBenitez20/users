package com.global.users.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Date {

    public static String getDateNow(){

        LocalDateTime currentDateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return currentDateTime.format(formatter);

    }

}
