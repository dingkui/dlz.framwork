package com.dlz.kit.json.core;

/**
 * JSON 解析与序列化失败时抛出的统一异常基类。
 *
 * <p>继承自 {@link RuntimeException}，属于非受检异常，调用方无需在方法签名中显式声明。
 * 所有 JSON 相关的错误（语法错误、类型不支持、超限等）都会以本异常或其子类
 * （如 {@link JsonParseException}）的形式抛出。</p>
 */
public class JsonException extends RuntimeException {
    public JsonException(String message) {
        super(message);
    }

    public JsonException(String message, Throwable cause) {
        super(message, cause);
    }
}
