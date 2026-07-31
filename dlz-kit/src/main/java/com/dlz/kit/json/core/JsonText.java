package com.dlz.kit.json.core;

/** Fast, shallow JSON root-shape checks. These methods do not validate syntax. */
public final class JsonText {
    private JsonText() {
    }

    public static boolean isObject(String value) {
        return hasDelimiters(value, '{', '}');
    }

    public static boolean isArray(String value) {
        return hasDelimiters(value, '[', ']');
    }

    private static boolean hasDelimiters(String value, char opening, char closing) {
        if (value == null) {
            return false;
        }
        String trimmed = value.trim();
        return trimmed.length() >= 2
                && trimmed.charAt(0) == opening
                && trimmed.charAt(trimmed.length() - 1) == closing;
    }
}
