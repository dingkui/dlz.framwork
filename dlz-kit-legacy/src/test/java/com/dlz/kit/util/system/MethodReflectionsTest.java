package com.dlz.kit.util.system;

import com.dlz.kit.exception.SystemException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MethodReflections测试")
class MethodReflectionsTest {

    @Test
    @DisplayName("mkMethodName 构建getter名称")
    void testMkMethodNameGetter() {
        assertEquals("getName", MethodReflections.mkMethodName("name", "get"));
    }

    @Test
    @DisplayName("mkMethodName 构建setter名称")
    void testMkMethodNameSetter() {
        assertEquals("setName", MethodReflections.mkMethodName("name", "set"));
    }

    @Test
    @DisplayName("mkMethodName 构建is名称")
    void testMkMethodNameIs() {
        assertEquals("isActive", MethodReflections.mkMethodName("active", "is"));
    }

    @Test
    @DisplayName("invokeGetter 调用getter")
    void testInvokeGetter() {
        TestBean bean = new TestBean();
        bean.setName("hello");
        Object result = MethodReflections.invokeGetter(bean, "name");
        assertEquals("hello", result);
    }

    @Test
    @DisplayName("invokeSetter 调用setter")
    void testInvokeSetter() {
        TestBean bean = new TestBean();
        MethodReflections.invokeSetter(bean, "name", "world");
        assertEquals("world", bean.getName());
    }

    @Test
    @DisplayName("invokeMethod 调用指定方法")
    void testInvokeMethod() {
        TestBean bean = new TestBean();
        bean.setName("test");
        Object result = MethodReflections.invokeMethod(bean, "getName");
        assertEquals("test", result);
    }

    @Test
    @DisplayName("invokeMethod 不存在的方法抛异常")
    void testInvokeMethodNotFound() {
        TestBean bean = new TestBean();
        assertThrows(SystemException.class, () ->
                MethodReflections.invokeMethod(bean, "nonExistentMethod"));
    }

    @Test
    @DisplayName("getAccessibleMethod 查找方法")
    void testGetAccessibleMethod() {
        Method method = MethodReflections.getAccessibleMethod(TestBean.class, "getName");
        assertNotNull(method);
        assertEquals("getName", method.getName());
    }

    @Test
    @DisplayName("getAccessibleMethod 查找带参方法")
    void testGetAccessibleMethodWithParams() {
        Method method = MethodReflections.getAccessibleMethod(TestBean.class, "setName", String.class);
        assertNotNull(method);
        assertEquals("setName", method.getName());
    }

    @Test
    @DisplayName("getAccessibleMethod 不存在返回null")
    void testGetAccessibleMethodNotFound() {
        Method method = MethodReflections.getAccessibleMethod(TestBean.class, "notReal");
        assertNull(method);
    }

    @Test
    @DisplayName("invokeMethod(obj, Method, args) 通过Method对象调用")
    void testInvokeMethodWithMethodObject() {
        TestBean bean = new TestBean();
        bean.setName("via method");
        Method method = MethodReflections.getAccessibleMethod(TestBean.class, "getName");
        Object result = MethodReflections.invokeMethod(bean, method);
        assertEquals("via method", result);
    }

    public static class TestBean {
        private String name;
        private int age;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }
    }
}
