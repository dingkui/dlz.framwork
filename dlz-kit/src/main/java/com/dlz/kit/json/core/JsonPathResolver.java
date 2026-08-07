package com.dlz.kit.json.core;

/**
 * 针对"非原生 JSON 容器"的自定义路径解析扩展点。
 *
 * <p>当待解析的值是普通 Java Bean（而非 Map / 数组 / 字符串）时，{@link JsonPath} 无法
 * 直接取字段。通过本接口，适配模块（如基于反射或具体 JSON 库的模块）可在不向 JSON 核心
 * 引入反射或映射依赖的前提下，自行解析路径并取值。</p>
 */
public interface JsonPathResolver {
    /**
     * 针对给定值解析路径并返回结果。
     *
     * @param value 非原生容器类型的 Java 对象
     * @param path  路径表达式
     * @return 解析得到的值
     */
    Object resolve(Object value, String path);
}
