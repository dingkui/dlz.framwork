package com.dlz.kit.util.system.convert;

import com.dlz.kit.util.system.ConvertUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BeanConvert测试")
class BeanConvertTest {


    @Test
    @DisplayName("convert null返回null")
    void testConvertNull() {
        assertNull(ConvertUtil.convert(null, TargetBean.class, null));
    }

    @Test
    @DisplayName("convert Map输入返回null (不处理Map)")
    void testConvertMapReturnsNull() {
        assertNotNull(ConvertUtil.convert(new HashMap<>(), TargetBean.class, null));
    }

    @Test
    @DisplayName("convert List输入返回null")
    void testConvertListReturnsNull() {
        assertEquals(0,ConvertUtil.convertList(new ArrayList<>(), TargetBean.class).size());
    }

    @Test
    @DisplayName("convert Bean到Bean")
    void testConvertBeanToBean() {
        SourceBean source = new SourceBean();
        source.name = "test";
        source.age = 30;
        TargetBean result = ConvertUtil.convert(source, TargetBean.class, null);
        assertNotNull(result);
        assertEquals("test", result.name);
        assertEquals(30, result.age);
    }

    @Test
    @DisplayName("convert Bean到Bean with consumer")
    void testConvertBeanToBeanWithConsumer() {
        SourceBean source = new SourceBean();
        source.name = "original";
        TargetBean result = ConvertUtil.convert(source, TargetBean.class, t -> t.name = "modified");
        assertEquals("modified", result.name);
    }

    @Test
    @DisplayName("convert Bean到Map")
    void testConvertBeanToMap() {
        SourceBean source = new SourceBean();
        source.name = "test";
        source.age = 25;
        HashMap result = ConvertUtil.convert(source, HashMap.class, null);
        assertNotNull(result);
        assertEquals("test", result.get("name"));
        assertEquals(25, result.get("age"));
    }

    @Test
    @DisplayName("convertList null返回null")
    void testConvertListNull() {
        assertEquals(0,ConvertUtil.convertList(null, SourceBean.class).size());
    }

    @Test
    @DisplayName("convertList 空列表返回空")
    void testConvertListEmpty() {
        List<TargetBean> result = ConvertUtil.convertList(new ArrayList<>(),TargetBean.class);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("convertList Map源类型非空时返回null")
    void testConvertListMapSourceReturnsNull() {
        List<Map> input = new ArrayList<>();
        input.add(new HashMap<>());
        assertEquals(1,ConvertUtil.convertList(input, TargetBean.class).size());
    }

    @Test
    @DisplayName("convertList Array源类型返回null")
    void testConvertListArraySourceReturnsNull() {
        List<Object[]> input = new ArrayList<>();
        input.add(new Object[]{"a"});
        assertEquals(1,ConvertUtil.convertList(input, Object[].class).size());
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
