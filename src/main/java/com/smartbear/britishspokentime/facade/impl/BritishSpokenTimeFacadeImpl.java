package com.smartbear.britishspokentime.facade.impl;

import com.smartbear.britishspokentime.exception.InvalidInputTimeException;
import com.smartbear.britishspokentime.facade.BritishSpokenTimeFacade;
import com.smartbear.britishspokentime.service.BritishSpokenTimeService;
import com.smartbear.britishspokentime.service.InputTimeParser;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class BritishSpokenTimeFacadeImpl implements BritishSpokenTimeFacade {
    private final InputTimeParser inputTimeParser;
    private final BritishSpokenTimeService britishSpokenTimeService;

    public BritishSpokenTimeFacadeImpl(InputTimeParser inputTimeParser, BritishSpokenTimeService britishSpokenTimeService) {
        this.inputTimeParser = inputTimeParser;
        this.britishSpokenTimeService = britishSpokenTimeService;
    }

    @Override
    public String apply(String timeInput) {
        try {
            LocalTime time = inputTimeParser.parseTimeInput(timeInput);
            return britishSpokenTimeService.convertToBritishSpokenTime(time);
        } catch (InvalidInputTimeException e) {
            return e.getMessage();

        } catch (Exception e) {
            return "Internal error happened.";
        }
    }
}
