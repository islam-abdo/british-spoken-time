package com.smartbear.britishspokentime.service;

import java.time.LocalTime;

public interface InputTimeParser {
    LocalTime parseTimeInput(String timeInput);
}
