package com.smartbear.britishspokentime.service.impl;

import com.smartbear.britishspokentime.service.SpokenTimeLocaleResolverService;
import com.smartbear.britishspokentime.service.SpokenTimeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class SpokenTimeLocaleResolverServiceImpl implements SpokenTimeLocaleResolverService {
    private final Map<String, SpokenTimeService> spokenTimeServiceMap = new HashMap<>();

    public SpokenTimeLocaleResolverServiceImpl(List<SpokenTimeService> spokenTimeServices) {
        for (SpokenTimeService spokenTimeService : spokenTimeServices) {
            this.spokenTimeServiceMap.put(spokenTimeService.getLocale(), spokenTimeService);
        }
    }

    @Override
    public SpokenTimeService resolve(String locale) {
        return spokenTimeServiceMap.getOrDefault(locale, spokenTimeServiceMap.get(Locale.UK.toString()));
    }
}
