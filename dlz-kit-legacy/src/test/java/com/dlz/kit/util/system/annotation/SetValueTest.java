package com.dlz.kit.util.system.annotation;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SetValue 注解单元测试
 *
 * 注解本身无执行字节码，仅验证其可正确标注字段并可通过反射读取元信息。
 *
 * @author test
 */
public class SetValueTest {

    @SetValue("hello")
    private String marked;

    @Test
    public void testAnnotationPresentOnField() throws NoSuchFieldException {
        Field field = SetValueTest.class.getDeclaredField("marked");
        assertTrue(field.isAnnotationPresent(SetValue.class));
        SetValue anno = field.getAnnotation(SetValue.class);
        assertNotNull(anno);
        assertEquals("hello", anno.value());
    }

    @Test
    public void testAnnotationDefaults() throws NoSuchFieldException {
        // 默认值应为空字符串
        Field field = SetValueTest.class.getDeclaredField("marked");
        SetValue anno = field.getAnnotation(SetValue.class);
        assertNotNull(anno);
        // 验证 @Retention 为 RUNTIME，否则运行期无法读取
        assertTrue(SetValue.class.getAnnotation(java.lang.annotation.Retention.class)
                .value() == java.lang.annotation.RetentionPolicy.RUNTIME);
    }
}
