package com.dlz.kit.util.system.convert;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BeanConvert测试")
class BeanConvertTest {

    private final BeanConvert converter = new BeanConvert();

    @Test
    @DisplayName("convert null返回null")
    void testConvertNull() {
        assertNull(converter.convert(null, TargetBean.class, null));
    }

    @Test
    @DisplayName("convert Map输入返回null (不处理Map)")
    void testConvertMapReturnsNull() {
        assertNull(converter.convert(new HashMap<>(), TargetBean.class, null));
    }

    @Test
    @DisplayName("convert List输入返回null")
    void testConvertListReturnsNull() {
        assertNull(converter.convert(new ArrayList<>(), TargetBean.class, null));
    }

    @Test
    @DisplayName("convert Bean到Bean")
    void testConvertBeanToBean() {
        SourceBean source = new SourceBean();
        source.name = "test";
        source.age = 30;
        TargetBean result = converter.convert(source, TargetBean.class, null);
        assertNotNull(result);
        assertEquals("test", result.name);
        assertEquals(30, result.age);
    }

    @Test
    @DisplayName("convert Bean到Bean with consumer")
    void testConvertBeanToBeanWithConsumer() {
        SourceBean source = new SourceBean();
        source.name = "original";
        TargetBean result = converter.convert(source, TargetBean.class, t -> t.name = "modified");
        assertEquals("modified", result.name);
    }

    @Test
    @DisplayName("convert Bean到Map")
    void testConvertBeanToMap() {
        SourceBean source = new SourceBean();
        source.name = "test";
        source.age = 25;
        HashMap result = converter.convert(source, HashMap.class, null);
        assertNotNull(result);
        assertEquals("test", result.get("name"));
        assertEquals(25, result.get("age"));
    }

    @Test
    @DisplayName("convertList null返回null")
    void testConvertListNull() {
        assertNull(converter.convertList(null, SourceBean.class, TargetBean.class, null));
    }

    @Test
    @DisplayName("convertList 空列表返回空")
    void testConvertListEmpty() {
        List<TargetBean> result = converter.convertList(new ArrayList<>(), SourceBean.class, TargetBean.class, null);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("convertList Map源类型非空时返回null")
    void testConvertListMapSourceReturnsNull() {
        List<Map> input = new ArrayList<>();
        input.add(new HashMap<>());
        assertNull(converter.convertList(input, Map.class, TargetBean.class, null));
    }

    @Test
    @DisplayName("convertList Array源类型返回null")
    void testConvertListArraySourceReturnsNull() {
        List<Object[]> input = new ArrayList<>();
        input.add(new Object[]{"a"});
        assertNull(converter.convertList(input, Object[].class, TargetBean.class, null));
    }

    public static class SourceBean {
        public String name;
        public int age;
    }

    public static class TargetBean {
        public String name;
        public int age;
    }
}
