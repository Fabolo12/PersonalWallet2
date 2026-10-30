package com.tests;

import com.models.Category;
import com.models.Transaction;
import com.models.TransactionType;
import com.ui.TransactionMapper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class TransactionMapperTest {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss yyyy-MM-dd");

    static void main() {
        positiveFormatScenario();
        positiveParseScenario();
    }

    private static void positiveParseScenario() {
        final TransactionType type = TransactionType.INCOME;
        final Category category = Category.AUTOMOTIVE;
        final String description = "Salary";
        final long centsAmount = 100000;
        final UUID id = UUID.randomUUID();
        final LocalDateTime timestamp =  LocalDateTime.now(ZoneId.systemDefault());

        final String csv = String.format("%s,%s,%s,%s,%d,%s",
                id, type, category, description, centsAmount, timestamp.format(FORMATTER)
        );
        final Transaction expected = new Transaction(id, type, category, description, centsAmount, timestamp);
        final Transaction actual = TransactionMapper.parseFromCsv(csv);

        if (!expected.equals(actual)) {
            throw new AssertionError(String.format("%nExpected: %s,%nbut got: %s", expected, actual));
        }
        System.out.println("Positive parse scenario passed");
    }

    private static void positiveFormatScenario() {
        final TransactionType type = TransactionType.INCOME;
        final Category category = Category.AUTOMOTIVE;
        final String description = "Salary";
        final long centsAmount = 100000;
        final UUID id = UUID.randomUUID();
        final LocalDateTime timestamp =  LocalDateTime.now(ZoneId.systemDefault());

        final Transaction transaction = new Transaction(id, type, category, description, centsAmount, timestamp);

        final String expected = String.format("%s,%s,%s,%s,%d,%s\n",
                id, type, category, description, centsAmount, timestamp.format(FORMATTER)
        );

        final String actual = TransactionMapper.formatToCsv(transaction);

        if (!actual.equals(expected)) {
            throw new AssertionError(String.format("Expected: %s, but got: %s", expected, actual));
        }
        System.out.println("Positive scenario passed");
    }
}
