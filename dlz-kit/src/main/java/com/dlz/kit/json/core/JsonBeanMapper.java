package com.dlz.kit.json.core;

/**
 * 组合式（编解码一体）的 Java Bean 映射契约接口。
 *
 * <p>该接口同时继承 {@link JsonEncoder}（编码为 JSON）与 {@link JsonDecoder}（从 JSON 解码），
 * 表示一个与具体实现（如 Jackson / Gson / Fastjson 等）无关的"Bean 映射器"。
 * 上层代码只需面向本接口编程，即可在运行时切换底层 JSON 库，而不依赖任何第三方依赖。</p>
 */
public interface JsonBeanMapper extends JsonEncoder, JsonDecoder {
}
