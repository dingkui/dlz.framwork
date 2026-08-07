package com.dlz.kit.util;

import com.dlz.kit.json.JSONMap;
import com.dlz.kit.util.beans.TestBean;
import com.dlz.kit.util.system.ConvertUtil;
import com.dlz.kit.util.system.annotation.SetValue;
import com.dlz.test.beans.SourceBean;
import com.dlz.test.beans.TargetBean;
import lombok.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * ValUtil工具类单元测试
 * 
 * 测试ValUtil的各种类型转换和对象操作功能
 * 
 * @author dk
 */
@DisplayName("BeanUtil工具类测试")
class BeanUtilTest {
    @Nested
    @DisplayName("属性访问测试")
    class PropertyAccessTests {

        @Data
        class TestBean {
            private String name = "测试名称";
            private int age = 25;
            private List<String> tags = Arrays.asList("标签1", "标签2");
        }

        @Test
        @DisplayName("getValue方法测试")
        void testGetValue() {
            TestBean bean = new TestBean();
            
            // 直接属性访问
            String name = ValUtil.at(bean, "name");
            assertEquals("测试名称", name);

            Integer age = ValUtil.at(bean, "age");
            assertEquals(Integer.valueOf(25), age);

            // Map访问
            Map<String, Object> map = new HashMap<>();
            map.put("key1", "值1");
            map.put("key2", 123);
            assertEquals("值1", ValUtil.at(map, "key1"));
            assertEquals(Integer.valueOf(123), ValUtil.at(map, "key2"));

            // List访问
            List<String> list = Arrays.asList("元素1", "元素2", "元素3");
            assertEquals("元素2", ValUtil.at(list, "[1]"));

            // JSON字符串访问
            String json = "{\"name\":\"JSON测试\",\"nested\":{\"value\":999}}";
            assertEquals("JSON测试", ValUtil.at(json, "name"));
            assertEquals(Integer.valueOf(999), ValUtil.at(json, "nested.value"));

            // 数组访问
            String[] array = {"数组元素1", "数组元素2"};
            assertEquals("数组元素1", ValUtil.at(array, "[0]"));
        }

        @Test
        @DisplayName("setValue方法测试")
        void testSetValue() {
            TestBean bean = new TestBean();
            
            // 设置简单属性
            ValUtil.set(bean, "name", "新名称", false);
            assertEquals("新名称", bean.getName());
            
            ValUtil.set(bean, "age", 30, false);
            assertEquals(30, bean.getAge());
            
            // Map设置
            Map<String, Object> map = new HashMap<>();
            ValUtil.set(map, "newKey", "新值", false);
            assertEquals("新值", map.get("newKey"));
            
            // List设置
            List<String> list = new ArrayList<>(Arrays.asList("元素1", "元素2"));
            ValUtil.set(list, "1", "修改的元素", false);
            assertEquals("修改的元素", list.get(1));
            
            // JSON字符串设置
            String json = "{\"name\":\"原始名称\"}";
            String newJson = (String) ValUtil.set(json, "name", "修改名称", false);
            JSONMap resultMap = new JSONMap(newJson);
            assertEquals("修改名称", resultMap.getStr("name"));
        }
    }


    @Nested
    @DisplayName("对象拷贝测试")
    class ObjectCopyTests {
        @Test
        @DisplayName("普通对象拷贝测试")
        void testCopy() {
            SourceBean source = new SourceBean();
            TargetBean result = ConvertUtil.convert( source, TargetBean.class);

            assertEquals("源名称", result.getName());
            assertEquals(30, result.getAge());
        }

        @Test
        @DisplayName("JSONMap对象拷贝测试")
        void testCopyToJSONMap() {
            SourceBean source = new SourceBean();
            JSONMap target = ConvertUtil.convert( source, JSONMap.class);
            assertEquals("源名称", target.getStr("name"));
            assertEquals(Integer.valueOf(30), target.getInt("age"));
            assertEquals("test@example.com", target.getStr("email"));
        }
    }


    @Nested
    @DisplayName("复制测试")
    class CopyTests {
        @Test
        @DisplayName("bean to map方法测试")
        public void testcopyAsSource() {
            TestBean bean = new TestBean();
            bean.setXx("xx");
            bean.setXx2("xx2");
            JSONMap target = ConvertUtil.convert(bean, JSONMap.class);
            assertEquals("{\"name\":\"测试名称\",\"info\":{\"xx\":\"xx\",\"xx2\":\"xx2\"}}", target.toString());
        }

        @Test
        @DisplayName("map to bean 方法测试")
        void testGetValue() {
            JSONMap source = new JSONMap("{\"name\":\"测试名称\",\"info\":{\"xx\":\"xx\",\"xx2\":\"xx2\"}}");
            TestBean target = ConvertUtil.convert(source, TestBean.class);
            assertEquals("xx", target.getXx());
            assertEquals("xx2", target.getXx2());
        }
    }
}