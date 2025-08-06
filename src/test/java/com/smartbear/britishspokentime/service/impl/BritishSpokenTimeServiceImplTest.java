package com.smartbear.britishspokentime.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BritishSpokenTimeServiceImplTest {

    @InjectMocks
    private BritishSpokenTimeServiceImpl service;

    @Test
    @DisplayName("Should convert valid LocalTime inputs to expected British spoken time")
    void testConvert_validCases() {
        List<TestCase> cases = List.of(
                new TestCase("00:00", "midnight"),
                new TestCase("12:00", "noon"),
                new TestCase("09:00", "nine o`clock"),
                new TestCase("14:00", "two o`clock"),
                new TestCase("14:15", "quarter past two"),
                new TestCase("14:30", "half past two"),
                new TestCase("14:45", "quarter to three"),
                new TestCase("14:35", "twenty five to three"),
                new TestCase("14:05", "five past two"),
                new TestCase("14:25", "twenty five past two"),
                new TestCase("14:01", "two one"),
                new TestCase("14:32", "two thirty two"),
                new TestCase("23:59", "eleven fifty nine")
        );

        for (TestCase testCase : cases) {
            String actual = service.convert(testCase.localTime);
            assertEquals(testCase.expectedOutput, actual, "Failed for input: " + testCase.inputTime);
        }
    }

    private static class TestCase {
        String inputTime;
        String expectedOutput;
        LocalTime localTime;

        TestCase(String inputTime, String expectedOutput) {
            this.inputTime = inputTime;
            this.localTime = LocalTime.parse(inputTime);
            this.expectedOutput = expectedOutput;
        }
    }
}