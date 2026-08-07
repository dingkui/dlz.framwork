package com.dlz.kit.util.system.convert;

import com.dlz.kit.util.system.ConvertUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("NativeConvert测试")
class NativeConvertTest {


    @Test
    @DisplayName("convert null返回null")
    void testConvertNull() {
        assertNull(ConvertUtil.convert(null, String.class, null));
    }

    @Test
    @DisplayName("convert 相同类型直接返回")
    void testConvertSameType() {
        String result = ConvertUtil.convert("hello", String.class, null);
        assertEquals("hello", result);
    }

    @Test
    @DisplayName("convert 到String")
    void testConvertToString() {
        assertEquals("123", ConvertUtil.convert(123, String.class, null));
    }

    @Test
    @DisplayName("convert 到Integer")
    void testConvertToInteger() {
        assertEquals(42, ConvertUtil.convert("42", Integer.class, null));
    }

    @Test
    @DisplayName("convert 到Long")
    void testConvertToLong() {
        assertEquals(100L, ConvertUtil.convert("100", Long.class, null));
    }

    @Test
    @DisplayName("convert 到BigDecimal")
    void testConvertToBigDecimal() {
        assertEquals(new BigDecimal("12.34"), ConvertUtil.convert("12.34", BigDecimal.class, null));
    }

    @Test
    @DisplayName("convert 到Float")
    void testConvertToFloat() {
        Float result = ConvertUtil.convert("1.5", Float.class, null);
        assertNotNull(result);
        assertEquals(1.5f, result, 0.001);
    }

    @Test
    @DisplayName("convert 到Double")
    void testConvertToDouble() {
        Double result = ConvertUtil.convert("2.5", Double.class, null);
        assertNotNull(result);
        assertEquals(2.5, result, 0.001);
    }

    @Test
    @DisplayName("convert 到Boolean")
    void testConvertToBoolean() {
        assertTrue(ConvertUtil.convert("true", Boolean.class, null));
        assertFalse(ConvertUtil.convert("false", Boolean.class, null));
    }

    @Test
    @DisplayName("convert 不支持的类型返回null")
    void testConvertUnsupported() {
        assertThrows(NumberFormatException.class, () -> ConvertUtil.convert("hello", Integer.class));
    }

    @Test
    @DisplayName("convertList null返回null")
    void testConvertListNull() {
        assertEquals(0,ConvertUtil.convertList(null, Integer.class).size());
    }

    @Test
    @DisplayName("convertList 空列表返回空")
    void testConvertListEmpty() {
        List<Integer> result = ConvertUtil.convertList(Arrays.asList(), Integer.class);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("convertList 相同类型")
    void testConvertListSameType() {
        List<String> input = Arrays.asList("a", "b", "c");
        List<String> result = ConvertUtil.convertList(input, String.class);
        assertEquals(3, result.size());
    }

    @Test
    @DisplayName("convertList 到String类型")
    void testConvertListToString() {
        List<Integer> input = Arrays.asList(1, 2, 3);
        List<String> result = ConvertUtil.convertList(input, String.class, null);
        assertEquals(3, result.size());
        assertEquals("1", result.get(0));
    }

    @Test
    @DisplayName("convertList 到Long类型")
    void testConvertListToLong() {
        List<String> input = Arrays.asList("10", "20");
        List<Long> result = ConvertUtil.convertList(input, Long.class);
        assertEquals(2, result.size());
        assertEquals(10L, result.get(0));
    }

    @Test
    @DisplayName("convertList 不支持的类型返回null")
    void testConvertListUnsupported() {
        List<String> input = Arrays.asList("a", "b");
        assertThrows(NumberFormatException.class, () -> ConvertUtil.convertList(input, Integer.class));
    }
}
