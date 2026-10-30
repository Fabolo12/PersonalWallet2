package com.utils;

public class Money {
    private Money() {

    }

    // "12.50" -> 1250;
    public static long parse(final String amount) {
        final String[] parts = checkAndPrepareParts(amount);

        try {
            final long firstPart = Long.parseLong(parts[0]);
            if (parts.length == 2) {
                final String secondPart = parts[1];
                return parseWithSecondPart(firstPart, secondPart);
            }

            return firstPart * 100;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid amount format: " + amount, e);
        }
    }

    // 1250 -> "12.50"
    public static String format(final long amount) {
        final long absAmount = Math.abs(amount);
        final long firstPart = absAmount / 100;
        final long secondPart = absAmount % 100;

        return (amount < 0 ? "-" : "") + firstPart + "." + (secondPart < 10 ? "0" : "") + secondPart;
    }

    private static String[] checkAndPrepareParts(final String amount) {
        if (amount == null || amount.isEmpty()) {
            throw new IllegalArgumentException("Amount cannot be null or empty");
        }
        final String pureAmount = replaceDifferentDelimiters(amount.trim())
                .replace(",", ".")
                .replace("_", "")
                .replace(" ", "");

        final String[] parts = pureAmount.split("\\.");
        if (parts.length > 2) {
            throw new IllegalArgumentException("Invalid amount format: " + amount);
        }
        return parts;
    }

    private static String replaceDifferentDelimiters(final String pureAmount) {
        if (pureAmount.contains(".") && pureAmount.contains(",")) {
            final int lastedIndexOfComma = pureAmount.lastIndexOf(",");
            final int lastedIndexOfDot = pureAmount.lastIndexOf(".");

            return lastedIndexOfComma > lastedIndexOfDot ?
                    pureAmount.replace(".", "")
                    : pureAmount.replace(",", "");
        }
        return pureAmount;
    }

    private static long parseWithSecondPart(final long firstPart, final String secondPart) {
        if (secondPart.length() > 2) {
            throw new IllegalArgumentException("Invalid amount format: " + secondPart);
        }
        final long secondPartValue = firstPart < 0 ? -Long.parseLong(secondPart) : Long.parseLong(secondPart);
        return firstPart * 100 + secondPartValue * (secondPart.length() == 1 ? 10 : 1);
    }
}
