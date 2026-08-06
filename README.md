# JSONMap — Java 嵌套数据操作，一行到位

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![JDK](https://img.shields.io/badge/JDK-8%20%7C%2011%20%7C%2017%20%7C%2021-green.svg)]()
[![Size](https://img.shields.io/badge/Size-~100KB-brightgreen.svg)]()
[![Maven](https://img.shields.io/badge/Maven-top.dlzio:dlz-kit)](https://central.sonatype.com/artifact/top.dlzio/dlz-kit)
[![codecov](https://codecov.io/gh/dingkui/dlz-kit/graph/badge.svg?token=IX0QS25K4X)](https://codecov.io/gh/dingkui/dlz-kit)

```java
// 以前：10 行判空 + 强转，还可能 NPE
// 现在：1 行，没有中间对象，不会 NPE
String city = new JSONMap(response).getStr("data.user.profile.addresses[0].city");
```

```xml
<dependency>
    <groupId>top.dlzio</groupId>
    <artifactId>dlz-kit</artifactId>
    <version>6.7.2</version>
</dependency>
```

---

## 30 秒上手

```java
// 解析 JSON
JSONMap data = new JSONMap("{\"user\":{\"name\":\"张三\",\"age\":\"25\"}}");

// 深层取值——路径不存在返回 null，不会 NPE
String name = data.getStr("user.name");          // "张三"
Integer age = data.getInt("user.age");            // 25（自动从字符串转）
List<Integer> ids = data.getList("user.ids", Integer.class); // 空返回空列表

// 构建嵌套结构——中间层级自动创建
JSONMap result = new JSONMap()
    .set("meta.version", "1.0")
    .set("data.user.name", "张三");
// → {"meta":{"version":"1.0"},"data":{"user":{"name":"张三"}}}
```

---

## 五大核心能力

### 深层路径取值

不用中间变量、不用逐层判空、不担心 NPE。

```java
// 任意层级为 null → 返回 null
String v = data.getStr("a.b.c.d.e.f");  // null，没有异常

// 支持数组索引和负索引
String last = data.getStr("tags[-1]");  // 倒数第一个
Long time = data.getLong("history.logs[-2].timestamp");

// 类型自动转换
BigDecimal amount = data.getBigDecimal("order.payment.total");  // "99.9" → 99.9
```

### 动态结构构建

```java
// 对比：传统方式需要逐层 new HashMap
// JSONMap：路径即结构
JSONMap req = new JSONMap()
    .set("header.traceId", UUID.randomUUID().toString())
    .set("header.source", "API")
    .set("body.user.name", "张三")
    .set("body.user.profile.city", "上海");
```

### 类型转换（ValUtil）

数据来源（前端表单、数据库、第三方 API）的类型不可控时，不用到处 try-catch。

```java
Integer age = ValUtil.toInt(params.get("age"));              // 不抛异常，返回 null 或正确值
Integer safe = ValUtil.toInt(params.get("age"), 0);          // 带默认值
List<Integer> ids = ValUtil.toList(params.get("ids"), Integer.class); // "1,2,3" → [1,2,3]
```

### Caller 日志诊断

公共组件日志直接显示业务调用处，排查问题从"全局搜索"变"秒级定位"。

```java
// 在 HttpClient/Redis/MyBatis 等公共组件入口加一行
try (DlzCallerContext ignored = DlzCaller.caller(0)) {
    log.info("HTTP POST {}", url);
}
```

日志从这样：
```text
INFO  HttpClientUtil - HTTP POST /payments
```
变成这样：
```text
INFO  [(OrderService.java:86)] HttpClientUtil - HTTP POST /payments
```

业务代码零侵入，支持嵌套调用、多层代理、MyBatis SQL 日志。

### 超轻量内存缓存

纯 JDK 实现，零额外依赖，getAndSet 一行搞定缓存模式。

```java
// 缓存不存在时自动加载，不用自己写 if-null-加载-缓存
User user = cache.getAndSet("user", "123", () -> 
    VAL.of(userMapper.selectById(123), 3600));
```

支持过期管理、通配符前缀查询、并发安全，可无缝切换到 Redis 等分布式实现。
---

## AI 辅助开发

JSONMap 的 API 模式固定、参数少、无重载歧义，AI 模型容易生成正确代码。

```java
// AI 生成结果示例（来自真实 prompt 测试）
JSONMap resp = new JSONMap(callbackBody);
String orderId = resp.getStr("data.order.orderId");
Integer amount = resp.getInt("data.order.amount");
```

在 Cursor / Copilot 中将 `docs/AI-速读指南.md` 添加到上下文即可。

---

## 20 年积累，不止是一个工具类

这套代码从 2006 年开始积累，经过上百个内部项目的验证。核心设计原则"有界宽容"——对结构缺失宽容，对数据错误不放过——来自三次生产事故的教训。它不是一个月写出来的 demo，是 20 年修修补补沉淀下来的实用工具集。

---

## 同类工具定位

| 库 | 擅长 | 和 JSONMap 的关系 |
|----|------|-------------------|
| Jackson | JSON ↔ 对象 | 互补。Jackson 负责序列化，JSONMap 负责操作 |
| JSONPath | 查询语法 | 只读不写。JSONMap 能读能写能转 Bean |
| Hutool | 全功能工具集 | 定位不同。JSONMap 更聚焦动态数据和嵌套操作 |

---

## 项目状态

- **JDK**：8 / 17 / 21
- **依赖**：核心模块唯一依赖 slf4j（你的项目大概率已有）
- **体积**：~100KB
- **测试**：完整测试用例覆盖
- **JSON 解析**：自研实现，零依赖 Jackson（Jackson 为可选插件）

## 文档

- [快速上手](docs/第01章-快速入门/1.2-五分钟上手.md)
- [JSONMap 完整指南](docs/第02章-核心功能/2.1-JSONMap完整指南.md)
- [DLZ Caller日志快速接入](docs/第02章-核心功能/2.5-Caller日志快速接入.md)
- [ValUtil 类型转换](docs/第03章-工具类库/3.1-ValUtil-类型转换.md)
- [Cache 缓存工具](docs/第03章-工具类库/3.6-Cache-缓存工具.md)
- [@SetValue 注解映射](docs/第04章-高级特性/4.1-SetValue注解映射.md)
- [有界宽容原则](docs/第04章-高级特性/4.4-有界宽容原则.md)
- [性能测试报告](docs/第07章-附录/7.1-性能测试报告.md)
- [AI 速读指南](docs/AI-速读指南.md)（给 AI 模型看的快速参考）

---

如果觉得有用，点个 ⭐ 让更多人看到。
