package com.stacktips.app.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateUtils {

    public static LocalDate parseDate(String dateStr) {
        DateTimeFormatter dtfInput = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateStr, dtfInput);
    }
}
