package com.dlz.kit.util.system.convert;

import com.dlz.kit.exception.SystemException;
import com.dlz.kit.json.core.JsonParseException;
import com.dlz.kit.util.system.ConvertUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MapConvert测试")
class MapConvertTest {


    @Test
    @DisplayName("convert null返回null")
    void testConvertNull() {
        assertNull(ConvertUtil.convert(null, TestBean.class, null));
    }

    @Test
    @DisplayName("convert 非Map输入返回null")
    void testConvertNonMap() {
        assertThrows(Exception.class,()-> ConvertUtil.convert("string", TestBean.class));

    }

    @Test
    @DisplayName("convert Map到Bean")
    void testConvertMapToBean() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "test");
        map.put("age", 25);
        TestBean result = ConvertUtil.convert(map, TestBean.class, null);
        assertNotNull(result);
        assertEquals("test", result.name);
        assertEquals(25, result.age);
    }

    @Test
    @DisplayName("convert Map到Map (相同类型)")
    void testConvertMapToSameMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("key", "value");
        HashMap result = ConvertUtil.convert(map, HashMap.class, null);
        assertNotNull(result);
        assertEquals("value", result.get("key"));
    }

    @Test
    @DisplayName("convert Map转Array抛异常")
    void testConvertMapToArrayThrows() {
        Map<String, Object> map = new HashMap<>();
        assertThrows(SystemException.class, () ->
                ConvertUtil.convert(map, Object[].class, null));
    }

    @Test
    @DisplayName("convert Map转List抛异常")
    void testConvertMapToListThrows() {
        Map<String, Object> map = new HashMap<>();
        assertThrows(SystemException.class, () -> ConvertUtil.convert(map, ArrayList.class, null));
    }

    @Test
    @DisplayName("convert Map到Bean with consumer")
    void testConvertMapToBeanWithConsumer() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "original");
        TestBean result = ConvertUtil.convert(map, TestBean.class, bean -> bean.name = "modified");
        assertEquals("modified", result.name);
    }

    @Test
    @DisplayName("convertList null返回null")
    void testConvertListNull() {
        assertEquals(0, ConvertUtil.convertList(null, Map.class).size());
    }

    @Test
    @DisplayName("convertList 空列表返回空")
    void testConvertListEmpty() {
        List result = ConvertUtil.convertList(new ArrayList<>(), Map.class);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("convertList 非Map类型返回null")
    void testConvertListNonMapSource() {
        List<String> input = Arrays.asList("a", "b");
        assertThrows(JsonParseException.class, () -> ConvertUtil.convertList(input, TestBean.class));
    }

    @Test
    @DisplayName("convertList Map列表到Bean列表")
    void testConvertListMapToBean() {
        List<Map> input = new ArrayList<>();
        Map<String, Object> m1 = new HashMap<>();
        m1.put("name", "first");
        m1.put("age", 1);
        Map<String, Object> m2 = new HashMap<>();
        m2.put("name", "second");
        m2.put("age", 2);
        input.add(m1);
        input.add(m2);
        List<TestBean> result = ConvertUtil.convertList(input, TestBean.class);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("first", result.get(0).name);
    }

    public static class TestBean {
        public String name;
        public int age;
    }
}
