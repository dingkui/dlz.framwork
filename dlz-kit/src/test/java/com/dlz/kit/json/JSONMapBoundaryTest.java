package com.dlz.kit.json;

import com.dlz.kit.util.JsonUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 验证 JSONMap 构造对普通字符串（非 JSON 对象）的边界行为。
 */
class JSONMapBoundaryTest {

    @Test
    void testGetJsonOfPlainString() {
        // 序列化普通字符串
        String json = JsonUtil.getJson("bad");
        System.out.println("getJson(\"bad\") = " + json);
        // 期望是带引号的 JSON 字符串
        assertEquals("\"bad\"", json);
    }

    @Test
    void testNewJSONMapOfPlainString() {
        // 用普通字符串构造 JSONMap，应抛异常（因为不是 JSON 对象）
        assertThrows(RuntimeException.class, () -> new JSONMap("bad"));
    }
}
