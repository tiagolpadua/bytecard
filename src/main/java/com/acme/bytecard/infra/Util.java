package com.acme.bytecard.infra;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
    public static String format(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return dateTime.format(formatter);
    }
}
