package com.smartbear.britishspokentime.service;

import java.time.LocalTime;

public interface SpokenTimeService {
    String getLocale();
    String convert(LocalTime time);
}
