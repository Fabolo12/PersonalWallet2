package com.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

public class Transaction {
    private final TransactionType type;
    private final Category category;
    private final String description;
    private final BigDecimal amount;
    private final LocalDateTime timestamp;

    public Transaction(TransactionType type, Category category, BigDecimal amount) {
        this(type, category, null, amount);
    }

    public Transaction(TransactionType type, Category category, String description, BigDecimal amount) {
        this.type = type;
        this.category = category;
        this.description = description;
        this.amount = checkAmount(amount);
        this.timestamp = LocalDateTime.now(ZoneId.systemDefault());
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

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    private BigDecimal checkAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        return amount;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        final Transaction that = (Transaction) o;
        return type == that.type && category == that.category && Objects.equals(description, that.description) && Objects.equals(amount, that.amount) && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, category, description, amount, timestamp);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "type=" + type +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                '}';
    }
}
