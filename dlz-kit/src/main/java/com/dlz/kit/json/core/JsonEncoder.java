package com.dlz.kit.json.core;

/**
 * 把 Java 值编码（序列化）为 JSON 文本的编码器契约。
 *
 * <p>与语法层的 {@link Json#stringify(Object)} 类似，但由具体实现（Jackson / Gson 等）完成，
 * 可借助实现库的能力处理 Bean、注解、日期格式等更复杂的对象结构。</p>
 */
public interface JsonEncoder {
    /**
     * 把任意 Java 值序列化成 JSON 文本。
     *
     * @param value 待编码的 Java 对象
     * @return 对应的 JSON 文本
     */
    String encode(Object value);
}
