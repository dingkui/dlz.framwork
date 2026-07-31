# `toInt("abc")` 该返回 0 还是抛异常？这一行代码背后藏着一个 45 年没人回答的问题

> 你写过一百遍 `if (str != null && !str.isEmpty()) { try { ... } catch (...) { ... } }`，却从没想过：**"宽容"到底应该宽容到哪一步？**
> Postel 定律统治了软件工程 45 年，没回答这个问题。这篇文章给出我的答案，并把它命名为 **有界宽容原则（Bounded Leniency Principle）**。

---

## 一、先看一段你写过一百遍的代码

```java
String ageStr = (String) map.get("age");
int age;
if (ageStr == null || ageStr.isEmpty()) {
    age = 0;
} else {
    try {
        age = Integer.parseInt(ageStr.trim());
    } catch (NumberFormatException e) {
        age = 0;  // 吞掉？记日志？抛出去？每个人写的都不一样
    }
}
```

一个"取个年龄"的需求，写出 10 行模板代码。问题不在你菜，而在于——

**Java 生态里，没人告诉你"宽容"应该宽容到哪一步。**

- `map.get` 不存在 → 返回 null（宽容）
- `Integer.parseInt(null)` → 抛 NPE（不宽容）
- `Integer.parseInt("")` → 抛异常（不宽容）
- `Integer.parseInt("abc")` → 抛异常（应该不宽容）

四种情况，四种态度，**毫无章法**。每一个 Java 开发者都在用 try/catch 和 if-null 给这套混乱打补丁。

## 二、行业的两个极端，都不对

### 极端一：Postel 定律的"无限宽容"

> "Be conservative in what you send, be liberal in what you accept." —— RFC 793, 1981

听起来很有道理。但 45 年后的今天，IETF 自己发了 **RFC 9413** 反思 Postel 定律：HTML 解析器互相宽容兼容了二十年，结果是**没人敢写一个新解析器**——因为"宽容"已经变成了一坨没人能定义的玄学。

**无限宽容 = 没有契约。**

### 极端二：Fail-Fast 的"无限严格"

Jackson 默认 `FAIL_ON_UNKNOWN_PROPERTIES = true`，前端多传一个字段就 500。
JDK `Integer.parseInt(null)` 直接 NPE。

**无限严格 = 把"用户没填"和"用户填错了"当成同一种错。**

但这两件事**根本不是一回事**：

- 用户没填年龄 → 业务上很常见，不是错误
- 用户填了 "abc" → 这是**真错**，必须立刻暴露

把它们混为一谈，要么逼业务代码写满 try/catch，要么让脏数据偷偷流进数据库。

## 三、有界宽容原则：在两个极端之间画一条线

我把数据访问 API 面对的输入分成**三个正交维度**：

| 维度 | 含义 | 例子 |
|------|------|------|
| **存在性 (Missing)** | 值在不在 | 路径不存在、`null`、`""` |
| **形式 (Type)** | 类型对不对 | `"123"` 要 `int`、`1` 要 `boolean` |
| **内容 (Content)** | 内容能不能解释 | `"abc"` 要 `int` |

然后给出三条规则：

> ### 🟢 Rule 1：Missing-Lenient（缺失宽容）
> **值不存在时，返回 null 或默认值，绝不抛异常。**
> 理由：缺失是业务常态，不是错误。
>
> ### 🟢 Rule 2：Type-Lenient（形式宽容）
> **类型不同但内容可解释时，自动转换。**
> 理由：前端传 `"123"` 还是 `123` 不该由后端代码关心。
>
> ### 🔴 Rule 3：Content-Strict（内容严格）
> **内容根本无法解释时，立刻抛异常，绝不静默兜底。**
> 理由：脏数据必须在入口暴露，而不是流到数据库才被发现。

**一句话**：对"没有"和"形不同"宽容，对"内容是垃圾"严格。

## 四、判定表：把原则变成机械可判定的契约

任何一个数据访问函数 `f(input) → output`，对照这张表就知道它合不合规：

| 输入情况 | 应有行为 | 违反者示例 |
|----------|----------|------------|
| 路径不存在 | 返回 null / 默认值 | `Map.get` ✅ / `Integer.parseInt(null)` ❌ |
| 值为 `null` | 返回 null / 默认值 | 多数库 ❌ |
| 值为 `""` | 返回 null / 默认值 | `Integer.parseInt("")` ❌ |
| `"123"` → int | 自动转换 | JDK 原生 Map ❌ |
| `1` → boolean | 自动转换 | JDK ❌ |
| `"abc"` → int | **抛异常** | Hutool `Convert.toInt("abc", 0)` 静默返回 0 ❌ |
| `"2024-13-45"` → date | **抛异常** | `LocalDate.parse` ✅ |

**这张表是这个原则最有价值的产物**：你不用再争论"这个 API 设计得对不对"，对着表打勾就行。

## 五、用这个原则审判主流库

| 库 | Missing | Type | Content | 评价 |
|----|---------|------|---------|------|
| **JDK 原生** | ❌ NPE | ❌ ClassCastException | ✅ 抛异常 | 三层全错前两层 |
| **Jackson 默认** | ❌ 抛异常 | 部分 ✅ | ✅ | 缺失层过严 |
| **Gson** | ✅ null | 部分 ✅ | 部分静默 | 内容层有时太宽 |
| **Hutool Convert** | ✅ | ✅ | ❌ 静默兜底 | 内容层放水，脏数据天堂 |
| **Apache MapUtils** | ✅ | 部分 ✅ | ❌ 静默返回默认值 | 同上 |
| **有界宽容** | ✅ | ✅ | ✅ 严格 | 三层都对 |

看出来了吗？**主流库里居然没有一个三层都对的**——这就是这个原则存在的意义。

## 六、违反它会怎样：一个真实事故

某次促销活动，前端 bug 让 `discount` 字段偶发传成 `"undefined"` 字符串。

**用 Hutool 的版本**（内容宽容）：
```java
int discount = Convert.toInt(params.get("discount"), 0);
// "undefined" → 静默返回 0 → 用户全价下单 → 投诉 → 第二天才发现
```

**用有界宽容的版本**（内容严格）：
```java
int discount = ValUtil.toInt(params.get("discount"), 0);
// "undefined" → NumberFormatException → 上线前测试就挂了
```

**宽容是有代价的，代价由谁付——是开发者还是用户——这就是原则要回答的问题。**

## 七、与既有原则的关系

| 原则 | 关系 |
|------|------|
| **Postel's Law**（1981） | 本原则是它在数据访问层的**精化**：保留"宽容接收"，但加上"内容必须严格"。 |
| **Fail-Fast** | 在 Content 层一致，在 Missing 层**反向**：缺失不是错。 |
| **Tolerant Reader Pattern**（Fowler） | 互补：TRP 讲怎么读结构未知的数据，本原则讲读出来之后怎么取值。 |
| **Principle of Least Astonishment** | 是它的一个具体实例：`getStr("不存在")` 抛异常很惊人，`toInt("abc")` 静默返回 0 也很惊人。 |

## 八、给一个参考实现

我把这套原则实现在了开源库 [dlz-kit](https://github.com/dlzio/dlz-kit) 里，核心两个 API：

```java
// 取值（Missing-Lenient + Type-Lenient）
JSONMap data = new JSONMap(jsonStr);
String name = data.getStr("user.name");          // 不存在 → null
Integer age = data.getInt("user.age", 18);        // 不存在 → 18
Integer id  = data.getInt("user.id");             // "123" → 123（自动转）

// 转换（Type-Lenient + Content-Strict）
ValUtil.toInt("123");      // → 123
ValUtil.toInt(null);       // → null（不抛）
ValUtil.toInt("");         // → null（不抛）
ValUtil.toInt("abc");      // → NumberFormatException（抛！）
```

**前两行帮你少写 10 行模板代码，最后一行帮你少修一个生产事故。**

但**库只是参考实现，原则才是核心**。你完全可以用任何语言、任何库实现它——只要对着第四节的判定表打勾就行。

## 九、把它带走

如果你只能记一句话：

> **对"没有"宽容，对"形不同"宽容，对"内容是垃圾"严格。**

如果你只能记一张图：

```
        宽容 ←——————————|——————————→ 严格
   Missing       Type    │   Content
    (缺失)       (形式)   │    (内容)
                         ↑
                   这条线就是"有界"
```

如果你想引用这个原则：

> **Bounded Leniency Principle**：在 Missing 与 Type 维度上宽容，在 Content 维度上严格。是 Postel's Law 在数据访问层的精化。

---

**讨论**：你见过的最离谱的"宽容/严格"翻车现场是什么？评论区聊聊。

**如果这个原则帮到你，点个赞让更多人看到——一个有名字的原则，才能在 Code Review 里被引用。**
