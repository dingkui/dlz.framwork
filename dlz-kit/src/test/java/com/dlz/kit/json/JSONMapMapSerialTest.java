package com.dlz.kit.json;

import com.dlz.kit.util.JsonUtil;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 验证 Map 序列化后能正确解析回 JSONMap（复现 ModalCoverageTest 场景）。
 */
class JSONMapMapSerialTest {

    @Test
    void testMapSerialization() {
        java.util.Map<String, Integer> map = Collections.singletonMap("id", 1);
        String json = JsonUtil.getJson(map);
        System.out.println("getJson(map) = " + json);
        String direct = JsonUtil.getMapper().write(map);
        System.out.println("mapper.write(map) = " + direct);
        assertNotNull(json);
        assertTrue(json.contains("id"), "JSON 应包含 key id: " + json);
    }

    @Test
    void testRequireMapsScenario() {
        // 复现 ModalCoverageTest: requireMaps(singletonList(singletonMap("id",1)))
        java.util.List<java.util.Map<String, Integer>> list =
                java.util.Collections.singletonList(Collections.singletonMap("id", 1));
        // 模拟 requireMaps：把每个 map 转 JSONMap
        JSONMap jm = new JSONMap(list.get(0));
        System.out.println("JSONMap = " + jm);
        assertTrue(jm.containsKey("id"));
    }
}
