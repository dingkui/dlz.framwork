# DLZ Caller

> 让 HttpClient、RedisClient、RPC、缓存和数据库日志直接指向业务调用处。

`dlz-caller` 是 DLZ Kit 的独立 caller/MDC 模块。它只依赖 SLF4J API，不依赖 Spring、
Jackson 或 MyBatis。

```xml
<dependency>
    <groupId>top.dlzio</groupId>
    <artifactId>dlz-caller</artifactId>
    <version>6.7.0</version>
</dependency>
```

在日志格式中加入 MDC：

```xml
<pattern>%d %-5level [%X{caller}] %logger - %msg%n</pattern>
```

在公共组件入口使用 scope：

```java
try (DlzCallerContext ignored = DlzCaller.caller()) {
    log.info("redis get key={}", key);
    return redisClient.get(key);
}
```

应用启动时配置需要跳过的工具类包：

```java
DlzCaller.getProperties().addIgnoreCallerPackage("com.example.http.");
DlzCaller.getProperties().addIgnoreCallerPackage("com.example.redis.");
DlzCaller.getProperties().addIgnoreCallerPackage("com.example.rpc.");
```

最终日志会从：

```text
INFO HttpClientUtil - HTTP POST /payments
```

变为：

```text
INFO [(OrderService.java:86)] HttpClientUtil - HTTP POST /payments
```

MyBatis 用户请额外引入 `top.dlzio:dlz-caller-mybatis`。完整复制式接入步骤、HttpClient、
Redis、代理层级、MyBatis XML 和 Spring 配置见 [3 分钟启用指南](docs/QUICK_START.md)。
