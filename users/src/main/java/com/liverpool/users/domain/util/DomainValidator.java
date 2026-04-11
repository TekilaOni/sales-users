package com.liverpool.users.domain.util;

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
}
