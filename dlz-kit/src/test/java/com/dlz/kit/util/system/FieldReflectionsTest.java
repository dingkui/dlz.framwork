package com.dlz.kit.util.system;

import com.dlz.kit.exception.SystemException;
import com.dlz.kit.exception.ValidateException;
import com.dlz.kit.fn.DlzFn;
import com.dlz.kit.util.VAL;
import com.dlz.test.beans.ChildEntity;
import com.dlz.test.beans.TestEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * FieldReflections 工具类单元测试
 * 
 * 测试反射操作字段的各种功能
 * 
 * @author test
 */
@Slf4j
public class FieldReflectionsTest {
    private TestEntity testEntity;
    private ChildEntity childEntity;
    
    @BeforeEach
    public void setUp() {
        testEntity = new TestEntity("张三", 25, true);
        childEntity = new ChildEntity("李四", 30, false, "子类字段", 95.5);
    }
    
    @Test
    public void testGetValue_ByField() {
        // 获取字段
        Field nameField = FieldReflections.getField(testEntity, "name", false);
        
        // 通过字段获取值
        String name = FieldReflections.getValue(testEntity, nameField);
        assertEquals("张三", name, "通过字段获取值应该正确");
        
        // 测试null字段
        try {
            FieldReflections.getValue(testEntity, (Field)null);
            fail("null字段应该抛出异常");
        } catch (Exception e) {
            assertEquals("6001:[field is null]", e.getMessage());
        }
    }
    
    @Test
    public void testGetValue_ByFieldName() {
        // 通过字段名获取值
        String name = FieldReflections.getValue(testEntity, "name", false);
        assertEquals("张三", name, "通过字段名获取值应该正确");
        
        int age = FieldReflections.getValue(testEntity, "age", false);
        assertEquals(25, age, "获取int字段应该正确");
        
        boolean active = FieldReflections.getValue(testEntity, "active", false);
        assertTrue(active, "获取boolean字段应该正确");
        
        // 测试忽略模式
        String nonExist = FieldReflections.getValue(testEntity, "nonExistField", true);
        assertNull(nonExist, "忽略模式下不存在字段应返回null");
        
        // 测试非忽略模式下的异常
        try {
            FieldReflections.getValue(testEntity, "nonExistField", false);
            fail("不存在字段且非忽略模式应该抛出异常");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("NoSuchField"), "应该抛出SystemException");
        }
        
        // 测试null对象
        try {
            FieldReflections.getValue(null, "name", false);
            fail("null对象且非忽略模式应该抛出异常");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Could not getValue"), "应该抛出IllegalArgumentException");
        }
        
        // 测试null对象忽略模式
        Object nullResult = FieldReflections.getValue(null, "name", true);
        assertNull(nullResult, "null对象忽略模式应返回null");
    }
    
    @Test
    public void testSetValue_ByFieldName() {
        // 设置字符串值
        boolean setResult1 = FieldReflections.setValue(testEntity, "name", "王五");
        assertTrue(setResult1, "设置字符串值应该成功");
        assertEquals("王五", testEntity.getName(), "设置后值应该正确");
        
        // 设置数值值
        boolean setResult2 = FieldReflections.setValue(testEntity, "age", 30);
        assertTrue(setResult2, "设置数值值应该成功");
        assertEquals(30, testEntity.getAge(), "设置后值应该正确");
        
        // 设置布尔值
        boolean setResult3 = FieldReflections.setValue(testEntity, "active", false);
        assertTrue(setResult3, "设置布尔值应该成功");
        assertFalse(testEntity.isActive(), "设置后值应该正确");
        
        // 测试忽略模式
        boolean ignoreResult = FieldReflections.setValue(testEntity, "nonExistField", "value", true);
        assertFalse(ignoreResult, "忽略模式下不存在字段应返回false");


        assertThrows(SystemException.class,()->FieldReflections.setValue(testEntity, "nonExistField", "value", false));
        assertThrows(ValidateException.class,()->FieldReflections.setValue(null, "name", "value", false));

        
        // 测试null对象忽略模式
        boolean nullResult = FieldReflections.setValue(null, "name", "value", true);
        assertFalse(nullResult, "null对象忽略模式应返回false");
    }
    
    @Test
    public void testSetValue_ByField() {
        Field nameField = FieldReflections.getField(testEntity, "name", false);
        
        // 通过字段设置值
        boolean setResult = FieldReflections.setValue(testEntity, nameField, "赵六");
        assertTrue(setResult, "通过字段设置值应该成功");
        assertEquals("赵六", testEntity.getName(), "设置后值应该正确");
        
        // 测试null字段
        try {
            FieldReflections.setValue(testEntity, (Field) null, "value");
            fail("null字段应该抛出异常");
        } catch (Exception e) {
            assertEquals("3003:[field is null]", e.getMessage());
        }
    }
    
    @Test
    public void testSetValue_Overload() {
        // 测试不带ignore参数的重载方法
        boolean setResult = FieldReflections.setValue(testEntity, "name", "孙七");
        assertTrue(setResult, "重载方法设置值应该成功");
        assertEquals("孙七", testEntity.getName(), "设置后值应该正确");
    }
    
    @Test
    public void testGetField() {
        // 通过对象获取字段
        Field nameField1 = FieldReflections.getField(testEntity, "name", false);
        assertNotNull(nameField1, "应该能获取到字段");
        assertEquals("name", nameField1.getName(), "字段名应该正确");
        
        // 通过类获取字段
        Field nameField2 = FieldReflections.getField(TestEntity.class, "name", false);
        assertNotNull(nameField2, "应该能获取到字段");
        assertEquals("name", nameField2.getName(), "字段名应该正确");
        
        // 测试继承字段
        Field childField = FieldReflections.getField(childEntity, "childField", false);
        assertNotNull(childField, "应该能获取到子类字段");
        assertEquals("childField", childField.getName(), "子类字段名应该正确");
        
        // 测试父类字段
        Field parentField = FieldReflections.getField(childEntity, "name", false);
        assertNotNull(parentField, "应该能获取到父类字段");
        assertEquals("name", parentField.getName(), "父类字段名应该正确");
        
        // 测试忽略模式
        Field nonExistField = FieldReflections.getField(testEntity, "nonExistField", true);
        assertNull(nonExistField, "忽略模式下不存在字段应返回null");
        
        // 测试非忽略模式下的异常
        try {
            FieldReflections.getField(testEntity, "nonExistField", false);
            fail("不存在字段且非忽略模式应该抛出异常");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("NoSuchField"), "应该抛出SystemException");
        }
    }
    
    @Test
    public void testGetFieldsMap() {
        // 获取字段映射
        Map<String, Field> fieldsMap = FieldReflections.getFieldsMap(TestEntity.class);
        assertNotNull(fieldsMap, "字段映射不应为null");
        
        // 验证包含的字段
        assertTrue(fieldsMap.containsKey("name"), "应该包含name字段");
        assertTrue(fieldsMap.containsKey("age"), "应该包含age字段");
        assertTrue(fieldsMap.containsKey("active"), "应该包含active字段");
        assertFalse(fieldsMap.containsKey("staticField"), "不应该包含static字段");
        
        // 验证字段类型
        assertEquals(String.class, fieldsMap.get("name").getType(), "name字段类型应该正确");
        assertEquals(int.class, fieldsMap.get("age").getType(), "age字段类型应该正确");
        assertEquals(boolean.class, fieldsMap.get("active").getType(), "active字段类型应该正确");
        
        // 测试子类字段映射
        Map<String, Field> childFieldsMap = FieldReflections.getFieldsMap(ChildEntity.class);
        assertTrue(childFieldsMap.containsKey("name"), "子类应该包含父类字段");
        assertTrue(childFieldsMap.containsKey("childField"), "子类应该包含自己的字段");
        assertTrue(childFieldsMap.containsKey("score"), "子类应该包含自己的字段");
    }
    
    @Test
    public void testGetFields() {
        // 获取字段列表
        List<Field> fields = FieldReflections.getFields(TestEntity.class);
        assertNotNull(fields, "字段列表不应为null");
        assertEquals(3, fields.size(), "应该包含3个字段");
        
        // 验证字段顺序和内容
        boolean hasName = false, hasAge = false, hasActive = false;
        for (Field field : fields) {
            switch (field.getName()) {
                case "name": hasName = true; break;
                case "age": hasAge = true; break;
                case "active": hasActive = true; break;
            }
        }
        assertTrue(hasName, "应该包含name字段");
        assertTrue(hasAge, "应该包含age字段");
        assertTrue(hasActive, "应该包含active字段");
        
        // 测试子类字段列表
        List<Field> childFields = FieldReflections.getFields(ChildEntity.class);
        assertTrue(childFields.size() >= 3, "子类字段数量应该大于等于父类");
    }
    
    @Test
    public void testDecapitalize() {
        // 基本测试
        assertEquals("cat", FieldReflections.decapitalize("Cat"), "首字母应该小写");
        assertEquals("cat", FieldReflections.decapitalize("cat"), "已经是小写应该保持");
        assertEquals("a", FieldReflections.decapitalize("A"), "单字符应该小写");
        
        // 边界测试
        assertNull(FieldReflections.decapitalize(null), "null应该返回null");
        assertEquals("", FieldReflections.decapitalize(""), "空字符串应该保持");
        
        // 全大写测试
        assertEquals("ABC", FieldReflections.decapitalize("ABC"), "全大写应该保持");
        assertEquals("HTML", FieldReflections.decapitalize("HTML"), "混合大小写应该处理");
    }
    
    @Test
    public void testMakeAccessible() throws NoSuchFieldException {
        // 获取私有字段
        Field privateField = TestEntity.class.getDeclaredField("name");
        assertFalse(privateField.isAccessible(), "私有字段初始应该是不可访问的");
        
        // 设置可访问
        FieldReflections.makeAccessible(privateField);
        assertTrue(privateField.isAccessible(), "私有字段应该变为可访问的");
        
        // 测试公共字段（应该不需要改变）
        Field publicField = TestEntity.class.getField("staticField");
        FieldReflections.makeAccessible(publicField);
        // 公共字段的状态可能因JVM而异，这里主要测试不抛异常
    }
    
    @Test
    public void testGetFn() {
        // 测试方法引用获取字段
        DlzFn<TestEntity, String> nameGetter = TestEntity::getName;
        VAL<Class<?>, Field> nameResult = FieldReflections.getFn(nameGetter);
        assertNotNull(nameResult, "方法引用结果不应为null");
        assertEquals(TestEntity.class, nameResult.v1, "类应该正确");
        assertEquals("name", nameResult.v2.getName(), "字段应该正确");
        
        // 测试boolean字段的方法引用
        DlzFn<TestEntity, Boolean> activeGetter = TestEntity::isActive;
        VAL<Class<?>, Field> activeResult = FieldReflections.getFn(activeGetter);
        assertNotNull(activeResult, "boolean方法引用结果不应为null");
        assertEquals("active", activeResult.v2.getName(), "字段应该正确");
        
        // 测试lambda表达式（应该抛出异常）
        DlzFn<TestEntity, String> lambdaFn = entity -> entity.getName();
        assertThrows(NoSuchFieldError.class,()->FieldReflections.getFn(lambdaFn));

        // 测试非getter方法引用（应该抛出异常）
        DlzFn<TestEntity, String> toStringFn = Object::toString;
        assertThrows(NoSuchFieldError.class,()->FieldReflections.getFn(toStringFn));
    }
    
    @Test
    public void testInheritanceFieldAccess() {
        // 测试子类访问父类字段
        String parentName = FieldReflections.getValue(childEntity, "name", false);
        assertEquals("李四", parentName, "子类应该能访问父类字段");
        
        // 测试子类自己的字段
        String childFieldValue = FieldReflections.getValue(childEntity, "childField", false);
        assertEquals("子类字段", childFieldValue, "子类应该能访问自己的字段");
        
        double score = FieldReflections.getValue(childEntity, "score", false);
        assertEquals(95.5, score, 0.001, "double字段应该正确获取");
        
        // 测试设置子类字段
        FieldReflections.setValue(childEntity, "childField", "新的子类值");
        assertEquals("新的子类值", childEntity.getChildField(), "子类字段应该能正确设置");
        
        FieldReflections.setValue(childEntity, "score", 88.8);
        assertEquals(88.8, childEntity.getScore(), 0.001, "double字段应该能正确设置");
    }
    
    @Test
    public void testStaticFieldExclusion() {
        // 验证静态字段被排除
        Map<String, Field> fieldsMap = FieldReflections.getFieldsMap(TestEntity.class);
        assertFalse(fieldsMap.containsKey("staticField"), "静态字段应该被排除");
        
        List<Field> fields = FieldReflections.getFields(TestEntity.class);
        boolean hasStatic = fields.stream().anyMatch(field -> "staticField".equals(field.getName()));
        assertFalse(hasStatic, "字段列表中不应该包含静态字段");
    }
    
    @Test
    public void testCacheFunctionality() {
        // 测试缓存功能 - 多次调用应该返回相同的字段对象
        Field field1 = FieldReflections.getField(testEntity, "name", false);
        Field field2 = FieldReflections.getField(testEntity, "name", false);
        
        assertSame(field1, field2, "相同字段应该来自缓存");
        
        // 测试方法引用缓存
        DlzFn<TestEntity, String> getter1 = TestEntity::getName;
        DlzFn<TestEntity, String> getter2 = TestEntity::getName;
        
        VAL<Class<?>, Field> result1 = FieldReflections.getFn(getter1);
        VAL<Class<?>, Field> result2 = FieldReflections.getFn(getter2);
        
        // 注意：由于是不同的lambda实例，可能不会命中缓存
        // 这里主要是测试不抛异常
        assertNotNull(result1, "方法引用结果不应为null");
        assertNotNull(result2, "方法引用结果不应为null");
    }
}