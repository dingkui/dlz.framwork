# DLZ-kit AI 速读

给 AI 助手的快速参考。读完本文档即可生成正确的 JSONMap 代码。

---

## 是什么

JSONMap 继承 HashMap，为 JSON 嵌套数据提供路径取值、自动类型转换、链式构建能力。

```xml
<dependency>
    <groupId>top.dlzio</groupId>
    <artifactId>dlz-kit</artifactId>
    <version>6.7.0</version>
</dependency>
```

---

## 构造

```java
new JSONMap("{\"name\":\"张三\"}");                      // JSON 字符串
new JSONMap(existingMap);                                  // 从 Map
new JSONMap();                                             // 空对象
new JSONMap("name", "张三", "age", 25);                    // 键值对
new JSONMap(anyObject);                                    // 从 POJO（字段值拷贝）
```

---

## get 方法 — 路径取值

所有 get 方法：路径不存在 → 返回 null 或默认值，不会 NPE。

```java
map.getStr("user.name")              // → String 或 null
map.getInt("user.age")               // → Integer 或 null
map.getInt("user.age", 0)            // → Integer 或 默认值
map.getLong("key")                   // → Long 或 null
map.getDouble("key")                 // → Double 或 null
map.getBoolean("key")                // → Boolean 或 null
map.getBigDecimal("key")             // → BigDecimal 或 null
map.getMap("key")                    // → JSONMap 或 null（子对象）
map.getList("key")                   // → JSONList 或 null
map.getList("key", Integer.class)    // → List<Integer> 或 null
map.getObj("key", User.class)        // → User 或 null（子节点转 Bean）
```

路径语法：
```java
"user.name"                         // 对象属性
"tags[0]"                           // 数组元素
"tags[-1]"                          // 负索引（倒数第一个）
"data.orders[0].id"                 // 混合
```

---

## set / put — 写入

```java
map.set("a.b.c", 1);    // 解析路径，自动创建中间层 → {"a":{"b":{"c":1}}}
map.put("a.b.c", 1);    // 不解析路径，直接作为键名 → {"a.b.c":1}
map.add("tags", "x");   // 追加到数组，自动创建数组
```

set 是路径模式，put 是 HashMap 原语。构造嵌套结构用 set。

---

## 类型转换规则（ValUtil）

```java
ValUtil.toInt(obj)              // 自动转换，失败返回 null
ValUtil.toInt(obj, defaultValue) // 带默认值
ValUtil.toList("1,2,3", Integer.class)  // → [1,2,3]
```

| 输入 | 目标 | 结果 |
|------|------|------|
| "25" | int | 25 |
| "99.9" | BigDecimal | 99.9 |
| "true" | boolean | true |
| null / 缺失 | 任意 | null 或默认值 |
| "abc" | int | 抛异常（内容不可转换） |

这就是"有界宽容"：缺失/类型宽容，内容严格。

---

## @SetValue 注解 — 扁平 Bean ↔ 嵌套 JSON

```java
public class User {
    private String name;
    @SetValue("ext_info")     // → 存到 ext_info.phone
    private String phone;
    @SetValue("ext_info")
    private String address;
}

// 扁平 Bean → 嵌套 JSON
User user = getUser();
JSONMap target = new JSONMap();
BeanUtil.copyAsSource(user, target, false);
// → {"name":"张三","ext_info":{"phone":"138xxx","address":"上海"}}

// 嵌套 JSON → 扁平 Bean
User user = BeanUtil.copyAsTarget(source, User.class);
```

`copyAsSource(source, target, true)`：第三个参数 true 表示只复制有 @SetValue 的字段。

---

## JSONList

```java
JSONList list = new JSONList("[\"a\",\"b\",\"c\"]");
list.getStr(0);     // "a"
list.getStr(-1);    // "c"（负索引）
list.getStr(-2);    // "b"
```

---

## 核心行为总结

1. 路径取值：任意环节为 null，返回 null，不抛异常
2. 类型转换：源类型不重要，目标类型决定
3. 内容不可转换：抛异常（不静默吞掉）
4. set 解析路径，put 不解析
5. 链式操作：每个方法返回 JSONMap 自身

---

## 完整文档

- [JSONMap 完整指南](第02章-核心功能/2.1-JSONMap完整指南.md)
- [ValUtil 类型转换](第03章-工具类库/3.1-ValUtil-类型转换.md)
- [@SetValue 注解映射](第04章-高级特性/4.1-SetValue注解映射.md)
- [有界宽容原则](第04章-高级特性/4.4-有界宽容原则.md)
