package com.models;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

public class Transaction {
    private final UUID id;

    private final TransactionType type;

    private final Category category;

    private final String description;

    private final long centsAmount;

    private final LocalDateTime timestamp;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss yyyy-MM-dd");

    public Transaction(
            final TransactionType type,
            final Category category,
            final long centsAmount
    ) {
        this(null, type, category, null, centsAmount, null);
    }

    public Transaction(
            final TransactionType type,
            final Category category,
            final String description,
            final long centsAmount
    ) {
        this(null, type, category, description, centsAmount, null);
    }

    public Transaction(
            final UUID uuid,
            final TransactionType type,
            final Category category,
            final String description,
            final long centsAmount,
            final LocalDateTime timestamp
    ) {
        this.id = uuid == null ? UUID.randomUUID() : uuid;
        this.type = type;
        this.category = category;
        this.description = description;
        this.centsAmount = checkAmount(centsAmount);
        this.timestamp = normilizeTimestamp(timestamp);
    }

    public UUID getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public long getCentsAmount() {
        return centsAmount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    private long checkAmount(long centsAmount) {
        if (centsAmount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        return centsAmount;
    }

    private LocalDateTime normilizeTimestamp(final LocalDateTime timestamp) {
        final LocalDateTime dateTime = timestamp == null ? LocalDateTime.now(ZoneId.systemDefault()) : timestamp;
        final String format = dateTime.format(FORMATTER);
        return LocalDateTime.parse(format, FORMATTER);
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        final Transaction that = (Transaction) o;
        return centsAmount == that.centsAmount && type == that.type && category == that.category && Objects.equals(description, that.description) && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, category, description, centsAmount, timestamp);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "type=" + type +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", amount=" + centsAmount +
                ", timestamp=" + timestamp +
                '}';
    }
}
