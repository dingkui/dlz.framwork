package com.dlz.kit.util.system;

import com.dlz.kit.exception.SystemException;
import com.dlz.test.beans.TestEntity;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Reflections 工具类单元测试
 *
 * 覆盖 copy、getActualType（两个重载）、getClassGenricType、
 * getTargetClass（两个重载）、newInstance（三个重载）、
 * mkParameterTypes、convertReflectionExceptionToUnchecked 等方法及其异常分支。
 *
 * @author test
 */
public class ReflectionsTest {

    // 用于测试泛型解析的辅助类型
    static class Gen<T> {
    }

    static class Sub extends Gen<String> {
    }

    @Test
    public void testCopy_nullSource_throws() {
        assertThrows(SystemException.class, () -> Reflections.copy(null));
    }

    @Test
    public void testCopy_validSource_noThrow() {
        // copy 内部基于无参构造创建目标并复制字段（结果被丢弃），仅验证不抛异常
        TestEntity src = new TestEntity("张三", 25, true);
        assertDoesNotThrow(() -> Reflections.copy(src));
    }

    // ---------- getActualType(Type, int...) ----------

    @Test
    public void testGetActualType_noIndex_returnsSelf() {
        // 无序号时，Class 直接返回自身
        assertSame(String.class, Reflections.getActualType(String.class));
        assertSame(ArrayList.class, Reflections.getActualType(ArrayList.class));
    }

    @Test
    public void testGetActualType_parameterized() {
        Type pt = new HashMap<String, Integer>() {
        }.getClass().getGenericSuperclass();
        assertSame(String.class, Reflections.getActualType(pt, 0));
        assertSame(Integer.class, Reflections.getActualType(pt, 1));
    }

    @Test
    public void testGetActualType_classWithoutGeneric_throws() {
        // TestEntity 的父类是 Object，无泛型参数
        assertThrows(SystemException.class, () -> Reflections.getActualType(TestEntity.class, 0));
    }

    // ---------- getActualType(Type, TypeVariable) ----------

    @Test
    public void testGetActualType_byTypeVariable_parameterized() {
        TypeVariable<?> tv = Gen.class.getTypeParameters()[0];
        Type pt = new Sub() {
        }.getClass().getGenericSuperclass();
        assertSame(String.class, Reflections.getActualType(pt, tv));
    }

    @Test
    public void testGetActualType_byTypeVariable_fromClass() {
        TypeVariable<?> tv = Gen.class.getTypeParameters()[0];
        assertSame(String.class, Reflections.getActualType(Sub.class, tv));
    }

    @Test
    public void testGetActualType_byTypeVariable_noGeneric_throws() {
        TypeVariable<?> tv = Gen.class.getTypeParameters()[0];
        assertThrows(SystemException.class, () -> Reflections.getActualType(TestEntity.class, tv));
    }

    // ---------- getClassGenricType ----------

    @Test
    public void testGetClassGenricType_noGeneric_returnsObject() {
        assertSame(Object.class, Reflections.getClassGenricType(TestEntity.class));
        assertSame(Object.class, Reflections.getClassGenricType(TestEntity.class, 0));
    }

    @Test
    public void testGetClassGenricType_resolved() {
        // Sub extends Gen<String> -> 第 0 个泛型实参为 String
        assertSame(String.class, Reflections.getClassGenricType(Sub.class));
    }

    @Test
    public void testGetClassGenricType_indexOutOfRange_returnsObject() {
        assertSame(Object.class, Reflections.getClassGenricType(Sub.class, 5));
        assertSame(Object.class, Reflections.getClassGenricType(Sub.class, -1));
    }

    // ---------- getTargetClass ----------

    @Test
    public void testGetTargetClass_null() {
        assertNull(Reflections.getTargetClass((Class<?>) null));
    }

    @Test
    public void testGetTargetClass_normalClass() {
        assertSame(TestEntity.class, Reflections.getTargetClass(TestEntity.class));
    }

    @Test
    public void testGetTargetClass_byInstance() {
        assertSame(TestEntity.class, Reflections.getTargetClass((Object) new TestEntity()));
    }

    // ---------- newInstance(Class) ----------

    @Test
    public void testNewInstance_noArg() {
        Object o = Reflections.newInstance(TestEntity.class);
        assertNotNull(o);
        assertTrue(o instanceof TestEntity);

        Object s = Reflections.newInstance(String.class);
        assertNotNull(s);
        assertEquals("", s);
    }

    @Test
    public void testNewInstance_noAccessibleCtor_throws() {
        // Integer 没有无参构造 -> InstantiationException 转为 RuntimeException
        assertThrows(RuntimeException.class, () -> Reflections.newInstance(Integer.class));
    }

    // ---------- newInstance(Class, Class[], Object[]) ----------

    @Test
    public void testNewInstance_withParameterTypes() {
        TestEntity e = Reflections.newInstance(
                TestEntity.class,
                new Class[]{String.class, int.class, boolean.class},
                new Object[]{"李四", 30, false});
        assertNotNull(e);
        assertEquals("李四", e.getName());
        assertEquals(30, e.getAge());
        assertFalse(e.isActive());
    }

    @Test
    public void testNewInstance_withParameterTypes_noMatch_throws() {
        assertThrows(SystemException.class, () -> Reflections.newInstance(
                TestEntity.class,
                new Class[]{String.class},
                new Object[]{"x"}));
    }

    // ---------- newInstance(Class, Object...) ----------

    @Test
    public void testNewInstance_varargs_success() {
        String s = Reflections.newInstance(String.class, "hello");
        assertEquals("hello", s);
    }

    @Test
    public void testNewInstance_varargs_noMatch_throws() {
        // 自动装箱为 Integer，但构造器形参是基本类型 int，不匹配 -> SystemException
        assertThrows(SystemException.class, () -> Reflections.newInstance(
                TestEntity.class, "a", 1, true));
    }

    // ---------- mkParameterTypes ----------

    @Test
    public void testMkParameterTypes_normal() {
        Class<?>[] types = Reflections.mkParameterTypes("a", 1, true);
        assertArrayEquals(new Class[]{String.class, Integer.class, Boolean.class}, types);
    }

    @Test
    public void testMkParameterTypes_withNull() {
        Class<?>[] types = Reflections.mkParameterTypes(null, "x");
        assertNull(types[0]);
        assertSame(String.class, types[1]);
    }

    // ---------- convertReflectionExceptionToUnchecked ----------

    @Test
    public void testConvert_illegalAccess() {
        // 该方法返回异常（不抛出），IllegalAccessException 被包装为 SystemException
        RuntimeException ex = Reflections.convertReflectionExceptionToUnchecked(new IllegalAccessException());
        assertNotNull(ex);
        assertTrue(ex instanceof SystemException);
    }

    @Test
    public void testConvert_invocationTarget() {
        RuntimeException rt = Reflections.convertReflectionExceptionToUnchecked(
                new InvocationTargetException(new RuntimeException("boom")));
        assertTrue(rt instanceof RuntimeException);
        assertTrue(rt.getMessage() == null || rt.getMessage().contains("boom")
                || rt.getCause() != null && rt.getCause().getMessage() != null
                && rt.getCause().getMessage().contains("boom"));
    }

    @Test
    public void testConvert_runtimePassThrough() {
        RuntimeException src = new RuntimeException("rt");
        RuntimeException rt = Reflections.convertReflectionExceptionToUnchecked(src);
        assertSame(src, rt);
        assertEquals("rt", rt.getMessage());
    }

    @Test
    public void testConvert_otherChecked() {
        RuntimeException rt = Reflections.convertReflectionExceptionToUnchecked(new Exception("plain"));
        assertTrue(rt instanceof RuntimeException);
        assertTrue(rt.getMessage().contains("Unexpected Checked Exception"));
    }
}
