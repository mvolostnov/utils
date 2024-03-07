package com.lseg.testframework.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access= AccessLevel.PRIVATE)
public final class Validate extends org.apache.commons.lang3.Validate {

    /**
     * <p>Validates that String expression is NOT {@code null} or empty.
     * Otherwise throwing an exception with the specified message.</p>
     */
    public static String notNullOrEmpty(final String str, final String message) {
        if (StringUtils.isNullOrEmpty(str)) {
            log.error(message);
            throw new IllegalArgumentException(message);
        }
        return str;
    }

    /**
     * <p>Validates that String expression is NOT {@code null} or blank (empty or whitespace only).
     * Otherwise throwing an exception with the specified message.</p>
     */
    public static String notNullOrBlank(final String str, final String message) {
        if (StringUtils.isNullOrBlank(str)) {
            log.error(message);
            throw new IllegalArgumentException(message);
        }
        return str;
    }

    /**
     * <p>Validates that T object is NOT {@code null}.
     * Otherwise throwing an exception with the specified message.</p>
     */
    public static <T> T notNull(final T object, final String message) {
        if (object == null) {
            log.error(message);
            throw new IllegalArgumentException(message);
        }
        return object;
    }

}
