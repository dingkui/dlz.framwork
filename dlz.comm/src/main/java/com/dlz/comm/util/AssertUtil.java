package com.dlz.comm.util;


import com.dlz.comm.exception.ValidateException;

/**
 * Util class for checking arguments.
 */
public class AssertUtil {
    private AssertUtil(){}

    public static void notEmpty(String string, String message) {
        if (StringUtils.isEmpty(string)) {
            throw new ValidateException(message);
        }
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new ValidateException(message);
        }
    }

    public static void isTrue(boolean value, String message) {
        if (!value) {
            throw new ValidateException(message);
        }
    }

    public static void assertState(boolean condition, String message) {
        if (!condition) {
            throw new ValidateException(message);
        }
    }
}