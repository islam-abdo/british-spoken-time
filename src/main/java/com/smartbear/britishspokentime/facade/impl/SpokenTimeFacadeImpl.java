package com.smartbear.britishspokentime.facade.impl;

import com.smartbear.britishspokentime.exception.InvalidInputTimeException;
import com.smartbear.britishspokentime.facade.SpokenTimeFacade;
import com.smartbear.britishspokentime.service.SpokenTimeLocaleResolverService;
import com.smartbear.britishspokentime.service.InputTimeParser;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class SpokenTimeFacadeImpl implements SpokenTimeFacade {
    private final InputTimeParser inputTimeParser;
    private final SpokenTimeLocaleResolverService spokenTimeLocaleResolverService;

    public SpokenTimeFacadeImpl(InputTimeParser inputTimeParser, SpokenTimeLocaleResolverService spokenTimeLocaleResolverService) {
        this.inputTimeParser = inputTimeParser;
        this.spokenTimeLocaleResolverService = spokenTimeLocaleResolverService;
    }

    @Override
    public String apply(String timeInput, String locale) {
        try {
            LocalTime time = inputTimeParser.parseTimeInput(timeInput);
            return spokenTimeLocaleResolverService.resolve(locale).convert(time);
        } catch (InvalidInputTimeException e) {
            return e.getMessage();

        } catch (Exception e) {
            return "Internal error happened.";
        }
    }
}
