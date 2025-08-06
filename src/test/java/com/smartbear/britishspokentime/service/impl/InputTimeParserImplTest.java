package com.smartbear.britishspokentime.service.impl;

import com.smartbear.britishspokentime.constants.Constants;
import com.smartbear.britishspokentime.exception.InvalidInputTimeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class InputTimeParserImplTest {

    @InjectMocks
    private InputTimeParserImpl inputTimeParser;

    @Test
    void testParseTimeInput_validTime_returnsLocalTime() {
        LocalTime expected = LocalTime.of(14, 0);
        LocalTime result = inputTimeParser.parseTimeInput("14:00");

        assertEquals(expected, result);
    }

    @Test
    void testParseTimeInput_singleDigitHour_returnsLocalTime() {
        LocalTime expected = LocalTime.of(9, 30);
        LocalTime result = inputTimeParser.parseTimeInput("9:30");

        assertEquals(expected, result);
    }

    @Test
    void testParseTimeInput_invalidFormat_throwsInvalidInputTimeException() {
        String input = "09.30";

        InvalidInputTimeException exception = assertThrows(
                InvalidInputTimeException.class,
                () -> inputTimeParser.parseTimeInput(input)
        );

        assertEquals(Constants.INVALID_TIME_FORMAT_ERROR, exception.getMessage());
    }

    @Test
    void testParseTimeInput_emptyString_throwsInvalidInputTimeException() {
        InvalidInputTimeException exception = assertThrows(
                InvalidInputTimeException.class,
                () -> inputTimeParser.parseTimeInput("")
        );

        assertEquals(Constants.INVALID_TIME_FORMAT_ERROR, exception.getMessage());
    }

    @Test
    void testParseTimeInput_nullInput_throwsInvalidInputTimeException() {
        InvalidInputTimeException exception = assertThrows(
                InvalidInputTimeException.class,
                () -> inputTimeParser.parseTimeInput(null)
        );

        assertEquals(Constants.INVALID_TIME_FORMAT_ERROR, exception.getMessage());
    }
}
