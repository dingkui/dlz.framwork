package com.dlz.kit.json.core;

import java.lang.reflect.Type;

/**
 * 构建在 JSON 语法核心之上的、可插拔的 Bean 映射扩展接口。
 *
 * <p>与 {@link Json} 这类"语法层"工具不同，{@code JsonMapper} 负责把 Java Bean 与 JSON 互相转换，
 * 通常由具体的 JSON 库适配层实现（如 Jackson、Gson 适配）。多个实现可通过 {@link #priority()}
 * 确定优先级，由上层框架选择最合适的映射器。</p>
 */
public interface JsonMapper {
    /**
     * 优先级：数值越大越优先被选中。
     *
     * @return 优先级数值
     */
    int priority();

    /**
     * 把 Java 值写成 JSON 文本。
     *
     * @param value 待写入的 Java 对象
     * @return JSON 文本
     */
    String write(Object value);

    /**
     * 把已解析的 JSON 值转换为目标类型的 Java 对象（支持泛型）。
     *
     * @param value      源值（通常是 Map / List / 标量）
     * @param targetType 目标类型（可携带泛型信息）
     * @param <T>        目标类型泛型占位
     * @return 转换后的强类型对象
     */
    <T> T convert(Object value, Type targetType);
}
