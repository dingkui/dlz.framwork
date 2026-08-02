package com.dlz.kit.json.jackson;

import com.dlz.kit.json.JSONMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JacksonUtilTest {
    @Test
    void explicitlyMapsBeansWithJackson() {
        User user = JacksonUtil.readValue("{\"name\":\"DLZ\",\"age\":18}", User.class);
        assertEquals("DLZ", user.name);
        assertEquals(18, user.age);

        JSONMap tree = JacksonUtil.readValue("{\"nested\":{\"value\":1}}");
        assertTrue(tree.get("nested") instanceof JSONMap);
    }

    public static class User {
        public String name;
        public int age;
    }
}
