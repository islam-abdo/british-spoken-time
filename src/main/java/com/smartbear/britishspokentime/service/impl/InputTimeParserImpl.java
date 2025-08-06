package com.smartbear.britishspokentime.service.impl;

import com.smartbear.britishspokentime.exception.InvalidInputTimeException;
import com.smartbear.britishspokentime.service.InputTimeParser;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class InputTimeParserImpl implements InputTimeParser {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("H:mm");

    @Override
    public LocalTime parseTimeInput(String timeInput) {
        try {
            return LocalTime.parse(timeInput, TIME_FORMATTER);

        } catch (DateTimeParseException e) {
            throw new InvalidInputTimeException("Invalid time format. Please use HH:mm (e.g., 09:30, 14:00).");
        }
    }
}
