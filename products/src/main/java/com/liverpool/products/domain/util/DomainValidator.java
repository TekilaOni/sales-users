package com.liverpool.products.domain.util;

import java.math.BigDecimal;

public final class DomainValidator {

    private DomainValidator() {}

    public static void validateNotBlank(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }
    public static void validatePattern(String value, String regex, String message) {
        if (value == null || !value.matches(regex)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void validateNotNull(Object value, String message) {
        if (value == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static <T extends Comparable<T>> void validateNotNegative(T value, T zero, String message) {
        validateNotNull(value, message);
        if (value.compareTo(zero) < 0) {
            throw new IllegalArgumentException(message);
        }
    }
}
