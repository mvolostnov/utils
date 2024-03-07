package com.lseg.testframework.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access= AccessLevel.PRIVATE)
public final class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static final String LINE_SEPARATOR = System.lineSeparator();

    /**
     * <p>Checks if a String is null.</p>
     */
    public static boolean isNull(final String str) {
        return str == null;
    }

    /**
     * <p>Checks if a String is NOT null.</p>
     */
    public static boolean isNotNull(final String str) {
        return !isNull(str);
    }

    /**
     * <p>Checks if a String is empty.</p>
     */
    public static boolean isEmpty(final String str) {
        Validate.notNull(str, "[StringUtils] Specified string is NULL object!");
        return str.isEmpty();
    }

    /**
     * <p>Checks if a String is null or empty.</p>
     */
    public static boolean isNullOrEmpty(final String str) {
        return isNull(str) || isEmpty(str);
    }

    /**
     * <p>Checks if a String is null or blank (empty or whitespace only).</p>
     */
    public static boolean isNullOrBlank(final String str) {
        return isBlank(str);
    }

    /**
     * <p>Checks if a String is NOT null or empty.</p>
     */
    public static boolean isNotNullOrEmpty(final String str) {
        return !isNullOrEmpty(str);
    }

    /**
     * <p>Checks if a String is NOT null or blank (empty or whitespace only).</p>
     */
    public static boolean isNotNullOrBlank(final String str) {
        return !isNullOrBlank(str);
    }
}
