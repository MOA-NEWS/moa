package com.moa.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
    public static String formatDate(LocalDateTime date) {
        LocalDateTime now = LocalDateTime.now();
        if (date.toLocalDate().isEqual(now.toLocalDate())) {
            // If the date is today, only display the time
            return date.format(DateTimeFormatter.ofPattern("HH:mm"));
        } else {
            // Otherwise, display the full date
            return date.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        }
    }
    public static void existUserInSession(HttpServletRequest request) {

    }
}