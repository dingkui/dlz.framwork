package com.dlz.kit.util.system;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ConvertUtil测试")
class ConvertUtilTest {

    @Test
    @DisplayName("convert null输入返回null")
    void testConvertNull() {
        assertNull(ConvertUtil.convert(null, String.class));
    }

    @Test
    @DisplayName("convert 相同类型直接返回")
    void testConvertSameType() {
        String result = ConvertUtil.convert("hello", String.class);
        assertEquals("hello", result);
    }

    @Test
    @DisplayName("convert Integer到String")
    void testConvertIntToString() {
        String result = ConvertUtil.convert(123, String.class);
        assertEquals("123", result);
    }

    @Test
    @DisplayName("convert String到Integer")
    void testConvertStringToInt() {
        Integer result = ConvertUtil.convert("42", Integer.class);
        assertEquals(42, result);
    }

    @Test
    @DisplayName("convert String到Long")
    void testConvertStringToLong() {
        Long result = ConvertUtil.convert("999", Long.class);
        assertEquals(999L, result);
    }

    @Test
    @DisplayName("convert String到BigDecimal")
    void testConvertStringToBigDecimal() {
        BigDecimal result = ConvertUtil.convert("12.34", BigDecimal.class);
        assertEquals(new BigDecimal("12.34"), result);
    }

    @Test
    @DisplayName("convert String到Boolean")
    void testConvertStringToBoolean() {
        Boolean result = ConvertUtil.convert("true", Boolean.class);
        assertTrue(result);
    }

    @Test
    @DisplayName("convert Map到Bean")
    void testConvertMapToBean() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "test");
        map.put("value", 42);
        SimpleBean result = ConvertUtil.convert(map, SimpleBean.class);
        assertNotNull(result);
        assertEquals("test", result.name);
    }

    @Test
    @DisplayName("convertList 空列表返回空列表")
    void testConvertListEmpty() {
        List<String> result = ConvertUtil.convertList(new ArrayList<>(), String.class);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("convertList null返回空列表")
    void testConvertListNull() {
        List<String> result = ConvertUtil.convertList(null, String.class, null);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("convertList 类型转换")
    void testConvertListTypeConversion() {
        List<Object> input = Arrays.asList(1, 2, 3);
        List<String> result = ConvertUtil.convertList(input, String.class);
        assertEquals(3, result.size());
        assertEquals("1", result.get(0));
    }

    @Test
    @DisplayName("convert with consumer")
    void testConvertWithConsumer() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "original");
        SimpleBean result = ConvertUtil.convert(map, SimpleBean.class, bean -> bean.name = "modified");
        assertEquals("modified", result.name);
    }

    public static class SimpleBean {
        public String name;
        public int value;
    }
}
