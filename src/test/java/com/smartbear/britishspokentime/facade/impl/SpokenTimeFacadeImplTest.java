package com.smartbear.britishspokentime.facade.impl;

import com.smartbear.britishspokentime.exception.InvalidInputTimeException;
import com.smartbear.britishspokentime.service.SpokenTimeLocaleResolverService;
import com.smartbear.britishspokentime.service.InputTimeParser;

import java.time.LocalTime;
import java.util.Locale;

import com.smartbear.britishspokentime.service.impl.BritishSpokenTimeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpokenTimeFacadeImplTest {

    @Mock
    private InputTimeParser inputTimeParser;

    @Mock
    private BritishSpokenTimeServiceImpl britishSpokenTimeService;

    @Mock
    private SpokenTimeLocaleResolverService spokenTimeLocaleResolverService;

    @InjectMocks
    private SpokenTimeFacadeImpl facade;

    @Test
    void testApply_validTimeInput_returnsSpokenTime() {
        String input = "14:00";
        LocalTime expectedTime = LocalTime.of(14, 0);
        String expectedSpoken = "two o'clock";

        when(spokenTimeLocaleResolverService.resolve(any())).thenReturn(britishSpokenTimeService);
        when(inputTimeParser.parseTimeInput(input)).thenReturn(expectedTime);
        when(britishSpokenTimeService.convert(expectedTime)).thenReturn(expectedSpoken);

        String result = facade.apply(input, Locale.UK.toString());

        assertEquals(expectedSpoken, result);
        verify(inputTimeParser).parseTimeInput(input);
        verify(britishSpokenTimeService).convert(expectedTime);
    }

    @Test
    void testApply_runtimeException_returnsInternalError() {
        String input = "10:00";

        when(inputTimeParser.parseTimeInput(input)).thenThrow(new RuntimeException("DB down"));

        String result = facade.apply(input, Locale.UK.toString());

        assertEquals("Internal error happened.", result);
        verify(inputTimeParser).parseTimeInput(input);
        verifyNoInteractions(britishSpokenTimeService);
    }

    @Test
    void testApply_invalidInputTimeException_returnsExceptionMessage() {
        String input = "10:00";

        InvalidInputTimeException invalidInputTimeException = new InvalidInputTimeException("Issue parsing the time");
        when(inputTimeParser.parseTimeInput(input)).thenThrow(invalidInputTimeException);

        String result = facade.apply(input, Locale.UK.toString());

        assertEquals(invalidInputTimeException.getMessage(), result);
        verify(inputTimeParser).parseTimeInput(input);
        verifyNoInteractions(britishSpokenTimeService);
    }
}
