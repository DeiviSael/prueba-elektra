package com.elektra.entrevista.deivi.common.Utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static boolean isValidDate(LocalDate date) {
        try {
            date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }
}
