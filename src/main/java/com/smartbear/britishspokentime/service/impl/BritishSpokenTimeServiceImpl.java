package com.smartbear.britishspokentime.service.impl;

import com.smartbear.britishspokentime.service.BritishSpokenTimeService;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class BritishSpokenTimeServiceImpl implements BritishSpokenTimeService {
    private static final String[] units = {
            "", "one", "two", "three", "four", "five", "six", "seven",
            "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen",
            "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };

    private static final String[] tens = {
            "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy",
            "eighty", "ninety"
    };


    @Override
    public String convertToBritishSpokenTime(LocalTime time) {
        String output = "...";

        int hour24 = time.getHour();
        int hour12 = time.getHour() % 12;
        int min = time.getMinute();

        // handle o`clock cases
        if(min == 0) {
            // Edge o`clock cases
            if(hour24 == 0) {
                return "midnight";
            }
            if(hour24 == 12) {
                return "noon";
            }

            // Else all should be the same
            return String.format("%s %s", numberToWord(hour12), "o`clock");
        }

        // if min isn't multiple of 5, then read the time directly without expressions
        if(min % 5 != 0) {
            return String.format("%s %s", numberToWord(hour12), numberToWord(min));
        }

        // handle cases where mins less than or equal 30
        if(min <= 30) {
            String minSpokenWord = numberToWord(min);

            // Edge cases for quarter/half
            if(min == 15) {
                minSpokenWord = "quarter";
            }
            if(min == 30) {
                minSpokenWord = "half";
            }

            return String.format("%s past %s", minSpokenWord, numberToWord(hour12));

        } // handle cases where mins more than 30
        else {
            int remainderMins = 60 - min;
            int nextHour = hour12 + 1;
            String remainderMinsSpokenWord = numberToWord(remainderMins);

            // Edge case for quarter
            if(remainderMins == 15) {
                remainderMinsSpokenWord = "quarter";
            }

            return String.format("%s to %s", remainderMinsSpokenWord, numberToWord(nextHour));
        }
    }

    private String numberToWord(int number) {
        if (number < 0 || number > 99) {
            throw new IllegalArgumentException("Number out of range (0-99)");
        }

        if (number < 20) {
            return units[number];
        }

        int tenPart = number / 10;
        int unitPart = number % 10;

        return tens[tenPart] + (unitPart != 0 ? " " + units[unitPart] : "");
    }
}
