package com.dlz.kit.json.core;

/**
 * DLZ JSON 路径解析器。
 *
 * <p>把一条路径表达式（点号取键 + 方括号索引，如 {@code a.b[0].c}）切分为
 * "第一段"与"剩余段"两部分，封装为 {@link JsonPathPart}，供逐层递归取值使用。</p>
 */
public final class JsonPathParser {
    /** 工具类禁止实例化。 */
    private JsonPathParser() {
    }

    /**
     * 把路径切分为"当前段"和"剩余路径"。
     *
     * @param path 完整路径表达式
     * @return 包含当前段与剩余路径的 {@link JsonPathPart}
     * @throws JsonException 路径为空或格式非法（如缺少 {@code ]}）时抛出
     */
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
