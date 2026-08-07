package com.dlz.kit.json;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JSONMap.removes() 方法测试用例
 * 测试多级键删除功能
 */
public class JSONMapRemovesTest {

    @Test
    public void testRemoveSimpleKey() {
        // 测试删除简单键
        JSONMap map = new JSONMap()
                .set("name", "张三")
                .set("age", 25)
                .set("city", "北京");

        map.removes("age");

        assertNull(map.at("age"), "age应该被删除");
        assertEquals("张三", map.at("name"), "name应该还存在");
        assertEquals("北京", map.at("city"), "city应该还存在");
    }

    @Test
    public void testRemoveNestedKey() {
        // 测试删除嵌套键
        JSONMap map = new JSONMap()
                .set("user.name", "李四")
                .set("user.age", 30)
                .set("user.profile.city", "上海")
                .set("user.profile.country", "中国");

        map.removes("user.profile.city");

        assertNull(map.at("user.profile.city"), "user.profile.city应该被删除");
        assertEquals("李四", map.at("user.name"), "user.name应该还存在");
        assertEquals(Integer.valueOf(30), map.at("user.age"), "user.age应该还存在");
        assertEquals("中国", map.at("user.profile.country"), "user.profile.country应该还存在");
    }

    @Test
    public void testRemoveDeepNestedKey() {
        // 测试删除深层嵌套键
        JSONMap map = new JSONMap()
                .set("a.b.c.d.e.f", "深层值")
                .set("a.b.c.d.e.g", "另一个值")
                .set("a.b.x", "其他值");

        map.removes("a.b.c.d.e.f");

        assertNull(map.at("a.b.c.d.e.f"), "a.b.c.d.e.f应该被删除");
        assertEquals("另一个值", map.at("a.b.c.d.e.g"), "a.b.c.d.e.g应该还存在");
        assertEquals("其他值", map.at("a.b.x"), "a.b.x应该还存在");
    }

    @Test
    public void testRemoveArrayElement() {
        // 测试删除数组元素
        JSONMap map = new JSONMap()
                .set("items[0]", "第一项")
                .set("items[1]", "第二项")
                .set("items[2]", "第三项");

        map.removes("items[1]");

        assertEquals("第一项", map.at("items[0]"), "items[0]应该还存在");
        assertEquals("第三项", map.at("items[1]"), "删除后items[1]应该是原来的items[2]");
        assertNull(map.at("items[2]"), "items[2]应该不存在了");
    }

    @Test
    public void testRemoveMultiDimensionalArray() {
        // 测试删除多维数组元素
        JSONMap map = new JSONMap()
                .set("matrix[0][0]", "A")
                .set("matrix[0][1]", "B")
                .set("matrix[1][0]", "C")
                .set("matrix[1][1]", "D");

        map.removes("matrix[0][1]");

        assertEquals("A", map.at("matrix[0][0]"), "matrix[0][0]应该还存在");
        assertNull(map.at("matrix[0][1]"), "matrix[0][1]应该被删除");
        assertEquals("C", map.at("matrix[1][0]"), "matrix[1][0]应该还存在");
        assertEquals("D", map.at("matrix[1][1]"), "matrix[1][1]应该还存在");
    }

    @Test
    public void testRemoveArrayWithNestedObject() {
        // 测试删除数组中嵌套对象的属性
        JSONMap map = new JSONMap()
                .set("users[0].name", "用户1")
                .set("users[0].age", 20)
                .set("users[1].name", "用户2")
                .set("users[1].age", 25);

        map.removes("users[0].age");

        assertEquals("用户1", map.at("users[0].name"), "users[0].name应该还存在");
        assertNull(map.at("users[0].age"), "users[0].age应该被删除");
        assertEquals("用户2", map.at("users[1].name"), "users[1].name应该还存在");
        assertEquals(Integer.valueOf(25), map.at("users[1].age"), "users[1].age应该还存在");
    }

    @Test
    public void testRemoveComplexPath() {
        // 测试删除复杂路径
        JSONMap map = new JSONMap()
                .set("company.departments[0].employees[0].name", "员工A")
                .set("company.departments[0].employees[0].salary", 5000)
                .set("company.departments[0].employees[1].name", "员工B")
                .set("company.departments[0].name", "技术部");

        map.removes("company.departments[0].employees[0].salary");

        assertEquals("员工A", map.at("company.departments[0].employees[0].name"), "员工A的名字应该还存在");
        assertNull(map.at("company.departments[0].employees[0].salary"), "员工A的薪资应该被删除");
        assertEquals("员工B", map.at("company.departments[0].employees[1].name"), "员工B的名字应该还存在");
        assertEquals("技术部", map.at("company.departments[0].name"), "部门名称应该还存在");
    }

    @Test
    public void testRemoveNonExistentKey() {
        // 测试删除不存在的键（不应该抛出异常）
        JSONMap map = new JSONMap()
                .set("name", "测试")
                .set("age", 30);

        // 删除不存在的键
        map.removes("nonexistent");
        map.removes("user.profile.city");
        map.removes("items[10]");

        // 原有数据应该不受影响
        assertEquals("测试", map.at("name"), "name应该还存在");
        assertEquals(Integer.valueOf(30), map.at("age"), "age应该还存在");
    }

    @Test
    public void testRemoveWithNegativeIndex() {
        // 测试使用负数索引删除
        JSONMap map = new JSONMap()
                .set("items[0]", "第一项")
                .set("items[1]", "第二项")
                .set("items[2]", "第三项");

        map.removes("items[-1]"); // 删除最后一项

        assertEquals("第一项", map.at("items[0]"), "items[0]应该还存在");
        assertEquals("第二项", map.at("items[1]"), "items[1]应该还存在");
        assertNull(map.at("items[2]"), "items[2]应该被删除");
    }

    @Test
    public void testRemoveEntireObject() {
        // 测试删除整个对象
        JSONMap map = new JSONMap()
                .set("user.name", "张三")
                .set("user.age", 25)
                .set("user.profile.city", "北京")
                .set("other", "其他数据");

        map.removes("user");

        assertNull(map.at("user"), "user对象应该被完全删除");
        assertNull(map.at("user.name"), "user.name应该不存在");
        assertNull(map.at("user.age"), "user.age应该不存在");
        assertNull(map.at("user.profile.city"), "user.profile.city应该不存在");
        assertEquals("其他数据", map.at("other"), "other应该还存在");
    }

    @Test
    public void testRemoveEntireArray() {
        // 测试删除整个数组
        JSONMap map = new JSONMap()
                .set("items[0]", "项目1")
                .set("items[1]", "项目2")
                .set("name", "测试");

        map.removes("items");

        assertNull(map.at("items"), "items数组应该被完全删除");
        assertNull(map.at("items[0]"), "items[0]应该不存在");
        assertEquals("测试", map.at("name"), "name应该还存在");
    }

    @Test
    public void testChainedRemoves() {
        // 测试链式删除
        JSONMap map = new JSONMap()
                .set("a", "值A")
                .set("b", "值B")
                .set("c", "值C")
                .set("d", "值D");

        map.removes("a")
           .removes("c");

        assertNull(map.at("a"), "a应该被删除");
        assertEquals("值B", map.at("b"), "b应该还存在");
        assertNull(map.at("c"), "c应该被删除");
        assertEquals("值D", map.at("d"), "d应该还存在");
    }

    @Test
    public void testRemoveFromJSONString() {
        // 测试从JSON字符串创建后删除
        String json = "{\"name\":\"李四\",\"age\":30,\"profile\":{\"city\":\"上海\",\"country\":\"中国\"}}";
        JSONMap map = new JSONMap(json);

        map.removes("profile.city");

        assertNull(map.at("profile.city"), "profile.city应该被删除");
        assertEquals("李四", map.at("name"), "name应该还存在");
        assertEquals(Integer.valueOf(30), map.at("age"), "age应该还存在");
        assertEquals("中国", map.at("profile.country"), "profile.country应该还存在");
    }

    @Test
    public void testRemoveArrayElementThenAdd() {
        // 测试删除数组元素后再添加
        JSONMap map = new JSONMap()
                .set("items[0]", "项目1")
                .set("items[1]", "项目2")
                .set("items[2]", "项目3");

        map.removes("items[1]");
        map.set("items[1]", "新项目2");

        assertEquals("项目1", map.at("items[0]"), "items[0]应该是项目1");
        assertEquals("新项目2", map.at("items[1]"), "items[1]应该是新项目2");
    }

    @Test
    public void testRemoveEmptyKey() {
        // 测试删除空键（应该抛出异常）
        JSONMap map = new JSONMap();
        assertThrows(Exception.class, () -> map.removes(""));
    }

    @Test
    public void testRemoveNullKey() {
        // 测试删除null键（应该抛出异常）
        JSONMap map = new JSONMap();
        assertThrows(Exception.class, () -> map.removes(null));
    }

    @Test
    public void testRemoveWithMixedArrayAndObject() {
        // 测试混合数组和对象的复杂删除
        JSONMap map = new JSONMap()
                .set("data[0].users[0].name", "用户A")
                .set("data[0].users[0].roles[0]", "管理员")
                .set("data[0].users[0].roles[1]", "编辑")
                .set("data[0].users[1].name", "用户B")
                .set("data[1].name", "其他数据");

        map.removes("data[0].users[0].roles[0]");

        assertEquals("编辑", map.at("data[0].users[0].roles[0]"), "roles[1]应该变成roles[0]");
        assertEquals("用户A", map.at("data[0].users[0].name"), "用户A的名字应该还存在");
        assertEquals("用户B", map.at("data[0].users[1].name"), "用户B的名字应该还存在");
    }

    @Test
    public void testRemoveAndVerifyStructure() {
        // 测试删除后验证整体结构
        JSONMap map = new JSONMap()
                .set("level1.level2.level3.value1", "值1")
                .set("level1.level2.level3.value2", "值2")
                .set("level1.level2.other", "其他");

        map.removes("level1.level2.level3.value1");

        // 验证父级对象仍然存在
        assertNotNull(map.at("level1"), "level1应该还存在");
        assertNotNull(map.at("level1.level2"), "level1.level2应该还存在");
        assertNotNull(map.at("level1.level2.level3"), "level1.level2.level3应该还存在");
        
        // 验证删除的值不存在
        assertNull(map.at("level1.level2.level3.value1"), "level1.level2.level3.value1应该被删除");
        
        // 验证其他值还存在
        assertEquals("值2", map.at("level1.level2.level3.value2"), "level1.level2.level3.value2应该还存在");
        assertEquals("其他", map.at("level1.level2.other"), "level1.level2.other应该还存在");
    }
}
