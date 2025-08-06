package com.smartbear.britishspokentime.service.impl;

import com.smartbear.britishspokentime.constants.Constants;
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
            if(timeInput == null) {
                throw new InvalidInputTimeException(Constants.INVALID_TIME_FORMAT_ERROR);
            }
            return LocalTime.parse(timeInput, TIME_FORMATTER);

        } catch (DateTimeParseException e) {
            throw new InvalidInputTimeException(Constants.INVALID_TIME_FORMAT_ERROR);
        }
    }
}
