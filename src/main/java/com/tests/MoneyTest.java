package com.tests;

import com.utils.Money;

import java.util.Map;

public class MoneyTest {
    static void main() {
        positiveParseScenario();
        negativeParseScenario();
        bigNumberParseScenario();

        positiveFormatScenario();
    }

    private static void positiveFormatScenario() {
        Map<Long, String> values = Map.of(
                -500L, "-5.00",
                -550L, "-5.50",
                -555L, "-5.55",
                0L, "0.00",
                500L, "5.00",
                550L, "5.50",
                555L, "5.55"
        );
        for (Map.Entry<Long, String> pair : values.entrySet()) {
            final Long amount = pair.getKey();
            final String expected = pair.getValue();
            final String actual = Money.format(amount);
            if (!actual.equals(expected)) {
                throw new AssertionError(String.format("Amount: %d Expected: %s, but got: %s", amount, expected, actual));
            }
        }
        System.out.println("Positive scenario passed");
    }

    private static void bigNumberParseScenario() {
        Map<String, Long> values = Map.of(
                "100,000.20", 10000020L,
                "100,000.25", 10000025L,
                "100,000.2", 10000020L,
                "100.000,20", 10000020L,
                "100.000,25", 10000025L,
                "100.000,2", 10000020L,
                "100_000.20", 10000020L,
                "100 000.20", 10000020L
        );
        for (Map.Entry<String, Long> pair : values.entrySet()) {
            final String amount = pair.getKey();
            final Long expected = pair.getValue();
            final long actual = Money.parse(amount);
            if (actual != expected) {
                throw new AssertionError(String.format("Amount: %s Expected: %d, but got: %d", amount, expected, actual));
            }
        }
        System.out.println("Big number scenario passed");
    }

    private static void negativeParseScenario() {
        String[] values = {
                "abc",
                "12.345",
                "12.3.4",
                "",
                null,
                "   ",
                "12,34,56"
        };
        for (String amount : values) {
            try {
                Money.parse(amount);
                throw new AssertionError(String.format("Amount: %s Expected: IllegalArgumentException, but got: no exception", amount));
            } catch (IllegalArgumentException e) {
                // Expected exception
            }
        }
        System.out.println("Negative scenario passed");
    }

    private static void positiveParseScenario() {
        Map<String, Long> values = Map.of(
                "-5", -500L,
                "-5.5", -550L,
                "-5.55", -555L,
                "0", 0L,
                "0.00", 0L,
                "5", 500L,
                "5,5", 550L,
                "5,55", 555L
                );
        for (Map.Entry<String, Long> pair : values.entrySet()) {
            final String amount = pair.getKey();
            final Long expected = pair.getValue();
            final long actual = Money.parse(amount);
            if (actual != expected) {
                throw new AssertionError(String.format("Amount: %s Expected: %d, but got: %d", amount, expected, actual));
            }
        }
        System.out.println("Positive scenario passed");
    }
}
