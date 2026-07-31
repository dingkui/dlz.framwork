package com.dlz.kit.json.core;

/** Parser for the dot and array-index path syntax used by JSONMap. */
public final class JsonPathParser {
    private JsonPathParser() {
    }

    public static JsonPathPart split(String path) {
        if (path == null || path.isEmpty() || ".".equals(path)) {
            throw new JsonException("JSON path cannot be empty");
        }

        int dotIndex = path.indexOf('.');
        int rightBracketIndex = path.indexOf(']');

        if (path.charAt(0) == '[') {
            if (rightBracketIndex < 0) {
                throw new JsonException("Array index is missing ']': " + path);
            }
            String remaining = rightBracketIndex + 1 < path.length()
                    ? path.substring(rightBracketIndex + 1)
                    : null;
            if (remaining != null && remaining.startsWith(".")) {
                remaining = remaining.substring(1);
            }
            if (remaining != null && remaining.isEmpty()) {
                remaining = null;
            }
            return new JsonPathPart(path.substring(0, rightBracketIndex + 1), remaining);
        }

        if (dotIndex >= 0) {
            if (rightBracketIndex >= 0 && rightBracketIndex < dotIndex) {
                int dotAfterBracket = path.indexOf('.', rightBracketIndex);
                if (dotAfterBracket >= 0) {
                    return new JsonPathPart(
                            path.substring(0, dotAfterBracket),
                            path.substring(dotAfterBracket + 1)
                    );
                }
            }
            return new JsonPathPart(path.substring(0, dotIndex), path.substring(dotIndex + 1));
        }

        return new JsonPathPart(path, null);
    }
}
