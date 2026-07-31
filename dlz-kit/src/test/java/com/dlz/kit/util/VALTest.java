package com.dlz.kit.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("VAL值容器测试")
class VALTest {

    @Test
    @DisplayName("VAL.of(v1, v2) 创建二元组")
    void testValOf2() {
        VAL<String, Integer> val = VAL.of("hello", 42);
        assertEquals("hello", val.v1);
        assertEquals(42, val.v2);
    }

    @Test
    @DisplayName("VAL.of(v1, v2, v3) 创建三元组")
    void testValOf3() {
        VAL.VAL3<String, Integer, Boolean> val = VAL.of("a", 1, true);
        assertEquals("a", val.v1);
        assertEquals(1, val.v2);
        assertTrue(val.v3);
    }

    @Test
    @DisplayName("VAL.of(v1, v2, v3, v4) 创建四元组")
    void testValOf4() {
        VAL.VAL4<String, Integer, Boolean, Double> val = VAL.of("a", 1, true, 3.14);
        assertEquals("a", val.v1);
        assertEquals(1, val.v2);
        assertTrue(val.v3);
        assertEquals(3.14, val.v4);
    }

    @Test
    @DisplayName("VAL.of(v1, v2, v3, v4, v5) 创建五元组")
    void testValOf5() {
        VAL.VAL5<String, Integer, Boolean, Double, Long> val = VAL.of("a", 1, true, 3.14, 100L);
        assertEquals("a", val.v1);
        assertEquals(1, val.v2);
        assertTrue(val.v3);
        assertEquals(3.14, val.v4);
        assertEquals(100L, val.v5);
    }

    @Test
    @DisplayName("VAL toString")
    void testValToString() {
        VAL<String, Integer> val = VAL.of("hello", 42);
        assertEquals("v1:hello,v2:42", val.toString());
    }

    @Test
    @DisplayName("VAL3 toString")
    void testVal3ToString() {
        VAL.VAL3<String, Integer, Boolean> val = VAL.of("a", 1, true);
        assertEquals("v1:a,v2:1,v3:true", val.toString());
    }

    @Test
    @DisplayName("VAL4 toString")
    void testVal4ToString() {
        VAL.VAL4<String, Integer, Boolean, Double> val = VAL.of("a", 1, true, 3.14);
        assertEquals("v1:a,v2:1,v3:true,v4:3.14", val.toString());
    }

    @Test
    @DisplayName("VAL5 toString")
    void testVal5ToString() {
        VAL.VAL5<String, Integer, Boolean, Double, Long> val = VAL.of("a", 1, true, 3.14, 100L);
        assertEquals("v1:a,v2:1,v3:true,v4:3.14,v5:100", val.toString());
    }

    @Test
    @DisplayName("VAL支持null值")
    void testNullValues() {
        VAL<String, String> val = VAL.of(null, null);
        assertNull(val.v1);
        assertNull(val.v2);
        assertEquals("v1:null,v2:null", val.toString());
    }
}
