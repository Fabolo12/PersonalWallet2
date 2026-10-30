package com.ui;

import com.models.Category;
import com.models.Transaction;
import com.models.TransactionType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class TransactionMapper {

    private TransactionMapper() {
    }

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss yyyy-MM-dd");

    public static String formatToCsv(final Transaction transaction) {
        final StringBuilder sb = new StringBuilder();
        sb.append(transaction.getId()).append(",");
        sb.append(transaction.getType()).append(",");
        sb.append(transaction.getCategory()).append(",");
        sb.append(transaction.getDescription()).append(",");
        sb.append(transaction.getCentsAmount()).append(",");
        sb.append(transaction.getTimestamp().format(FORMATTER)).append("\n");
        return sb.toString();
    }

    public static Transaction parseFromCsv(final String line) {
        final String[] parts = line.split(",");
        if (parts.length != 6) {
            throw new IllegalArgumentException("Invalid CSV format");
        }
        final UUID id = UUID.fromString(parts[0]);
        final TransactionType type = TransactionType.valueOf(parts[1]);
        final Category category = Category.valueOf(parts[2]);
        final String description = parts[3];
        final long centsAmount = Long.parseLong(parts[4]);
        final LocalDateTime timestamp = LocalDateTime.parse(parts[5], FORMATTER);
        return new Transaction(id, type, category, description, centsAmount, timestamp);
    }
}
