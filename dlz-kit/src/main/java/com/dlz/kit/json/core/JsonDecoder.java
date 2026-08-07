package com.dlz.kit.json.core;

import java.lang.reflect.Type;

/**
 * 把 JSON 文本或已解析的 Java 值，转换成目标 Java 类型的解码器契约。
 *
 * <p>与仅做"语法层"解析的 {@link Json} 不同，{@code JsonDecoder} 负责把通用的
 * {@link Map}/{@link java.util.List} 结果进一步绑定（bind）到具体的 Bean 类、泛型类型等，
 * 使调用方能直接拿到强类型对象。</p>
 */
public interface JsonDecoder {
    /**
     * 把给定值解码为指定 {@link Class} 的实例。
     *
     * @param value      已解析的源值（通常是 Map / List / 标量，或 JSON 文本）
     * @param targetType 目标类型
     * @param <T>        目标类型的泛型占位
     * @return 解码后的强类型对象
     */
    <T> T decode(Object value, Class<T> targetType);

    /**
     * 把给定值解码为指定的泛型 {@link Type}（如 {@code List<Foo>}）。
     *
     * @param value      已解析的源值
     * @param targetType 目标类型，可携带泛型信息
     * @param <T>        目标类型的泛型占位
     * @return 解码后的强类型对象
     */
    <T> T decode(Object value, Type targetType);
}
