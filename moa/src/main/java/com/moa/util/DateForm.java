package com.moa.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateForm {

    public String formatDate(LocalDateTime date) {
        LocalDateTime now = LocalDateTime.now();
        if (date.toLocalDate().isEqual(now.toLocalDate())) {
            // If the date is today, only display the time
            return date.format(DateTimeFormatter.ofPattern("HH:mm"));
        } else {
            // Otherwise, display the full date
            return date.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        }
    }
}
