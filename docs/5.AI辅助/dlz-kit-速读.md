# dlz-kit AI 速读

> 本页是给 AI 编程助手的最小事实契约。精确签名以当前源码和 `docs/第07章-附录/7.5-API索引.md` 为准。

## 模块选择

- `top.dlzio:dlz-kit`：核心 JSON、转换、缓存、Caller 和通用工具；核心运行时不依赖 Jackson。
- `top.dlzio:dlz-json-jackson`：可选 Jackson 互操作，入口为 `com.dlz.kit.json.jackson.JacksonUtil`。
- `top.dlzio:dlz-caller-mybatis`：可选 MyBatis SQL 日志插件，需要手动注册拦截器/配置。
- 当前版本：`6.7.5`；编译目标 Java 8。

## 首选 API

- 嵌套动态数据：`JSONMap`、`JSONList`。
- 核心 JSON：`JsonUtil`；不要把 `JacksonUtil` 当作核心依赖。
- 类型转换：`ValUtil`；对象/集合转换：`ConvertUtil.convert`、`ConvertUtil.convertList`。
- 缓存：`ICache`、`MemoryCache`、`CacheUtil`、`CacheMap`；不存在名为 `Cache` 的公共类型。
- 调用方日志：`DlzCaller`，MDC 常量为 `DlzCaller.MDC_KEY_DLZ_CALLER`。

## JSONMap 规则

```java
JSONMap map = new JSONMap(json);
String city = map.getStr("user.profile.city");
map.set("data.user.name", "张三"); // 路径写入，自动创建中间结构
map.put("data.user.name", "张三"); // Map 原语，不解析路径
```

路径支持对象属性、数组索引和负索引。路径缺失通常返回 `null` 或调用方提供的默认值；类型冲突、非法写入和边界错误以源码/测试为准。

## 转换规则

- `null`、缺失值和空输入按具体重载返回 `null` 或默认值。
- 类型可转换时执行转换。
- 内容存在但格式非法（例如 `"abc"` 转整数）抛出 `NumberFormatException`，不要在示例中写成静默返回 `null`。

## JSON 工具边界

`JsonUtil` 提供核心 JSON 读写、路径访问、类型判断和转换；`JacksonUtil` 只提供 Jackson 的字符串读写、树模型、`convertValue`、`coverObj` 和 `JavaType` 构建。不要在 `JacksonUtil` 下引用 `at`、`splitKey`、`isJsonObj`、`isJsonArray` 或 `canSerialize`。

## 生成代码要求

- 示例只使用当前公开 API，不虚构构造器、方法或配置键。
- 先区分核心模块与插件依赖，再生成 Maven 配置。
- 不把 `JSONObject` 当作 dlz-kit API；该方向暂不开发、不开放。
- 不宣称路径解析缓存、完整覆盖率或未经复现的性能倍数。
- 涉及日志和 MyBatis 时，说明生命周期、注册方式和敏感参数风险。

## 验证入口

修改文档或示例后，至少检查 Markdown 链接、代码围栏、包名/方法名存在性，并运行对应模块测试。