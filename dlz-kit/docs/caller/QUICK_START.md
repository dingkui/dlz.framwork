# DLZ Caller 3 分钟启用指南

本指南只做一件事：让 HttpClient、RedisClient、RPC、MyBatis 等公共组件日志直接显示业务调用处。

完成后，原本这样的日志：

```text
INFO  HttpClientUtil - HTTP POST /payments
```

会变成：

```text
INFO  [(OrderService.java:86)] HttpClientUtil - HTTP POST /payments
```

## 0. 前提

- Java 8+；
- 应用使用 SLF4J；
- 已有 Logback 或 Log4j2；
- MyBatis 集成仅在使用 MyBatis 时需要。

## 1. 引入依赖

```xml
<dependency>
    <groupId>top.dlzio</groupId>
    <artifactId>dlz-caller</artifactId>
    <version>6.7.0</version>
</dependency>
```

## 2. 在日志格式中显示 caller

没有这一步，caller 已经写入 MDC，但不会显示在日志文本中。

### Logback

```xml
<encoder>
    <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} %-5level [%X{caller}] %logger - %msg%n</pattern>
</encoder>
```

### Log4j2

```xml
<PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss.SSS} %-5level [%X{caller}] %c - %m%n"/>
```

`caller` 是默认 MDC key。没有 caller 的普通日志会显示空方括号，不会影响原有功能。

## 3. 配置公共组件包

在应用启动位置执行一次。这里配置的是“日志发生在这些工具类中，但 caller 应继续向业务层查找”的包。

```java
import com.dlz.caller.DlzCaller;

public final class LogBootstrap {

    public static void init() {
        DlzCaller.getProperties().addIgnoreCallerPackage("com.example.http."
                ,"com.example.redis."
                ,"com.example.rpc."
                ,"com.example.cache.");
    }
}
```

将包配置完整后，任意层数的这些代理和工具类都会被跳过，不需要反复调整堆栈层级。

包名前缀请以 `.` 结尾，例如 `com.example.http.`。不以 `.` 结尾的配置按完整类名匹配，适合只忽略
单个历史工具类，例如 `com.example.LegacyHttpClient`。

## 4. HttpClient 快速植入

在公共 HTTP 工具的最外层入口包一层 caller scope：

```java
import com.dlz.caller.DlzCallerContext;
import com.dlz.caller.DlzCaller;

public class HttpClientUtil {

    public HttpResult execute(HttpRequest request) {
        try (DlzCallerContext ignored = DlzCaller.caller(0)) {
            log.info("HTTP {} {}", request.getMethod(), request.getUrl());
            return httpClient.execute(request);
        }
    }
}
```

业务侧不需要任何改动：

```java
public class OrderService {

    public void createOrder() {
        httpClientUtil.execute(HttpRequest.post("/payments"));
    }
}
```

效果：

```text
INFO  [(OrderService.java:86)] HttpClientUtil - HTTP POST /payments
```

## 5. RedisClient 快速植入

Redis、缓存、MQ、文件存储等公共组件的写法完全相同：

```java
import com.dlz.caller.DlzCallerContext;
import com.dlz.caller.DlzCaller;

public class RedisClient {

    public String get(String key) {
        try (DlzCallerContext ignored = DlzCaller.caller(0)) {
            log.debug("redis get key={}", key);
            return redisTemplate.opsForValue().get(key);
        }
    }
}
```

业务日志效果：

```text
DEBUG [(ProductService.java:51)] RedisClient - redis get key=product:1001
```

## 6. 已有工具类：使用 setCaller/clearCaller

无法改成 `try-with-resources` 的旧代码可以采用下面写法：

```java
import com.dlz.caller.DlzCaller;

public HttpResult execute(HttpRequest request) {
    DlzCaller.setCaller(1);
    try {
        log.info("HTTP {} {}", request.getMethod(), request.getUrl());
        return httpClient.execute(request);
    } finally {
        DlzCaller.clearCaller();
    }
}
```

`setCaller(1)` 表示：完成包过滤后，再额外跳过一层包装调用。优先使用第 3 步的包配置；
`n` 仅用于无法按包名区分的额外包装层。

必须在 `finally` 中调用 `clearCaller()`，否则线程池复用时 caller 可能泄漏到下一次请求。

## 7. 多层代理场景

假设调用链如下：

```text
OrderService
  -> PaymentFacade
    -> RetryProxy
      -> RpcClient
        -> HttpClientUtil
```

只要启动时配置：

```java
DlzCaller.getProperties().addIgnoreCallerPackage("com.example.retry.");
DlzCaller.getProperties().addIgnoreCallerPackage("com.example.rpc.");
DlzCaller.getProperties().addIgnoreCallerPackage("com.example.http.");
```

日志仍会定位到：

```text
(OrderService.java:86)
```

内部 HttpClient 又调用 RPC、RPC 又调用 Redis 时，内层 scope 不会覆盖已经确定的外层 caller。

## 8. MyBatis 3 分钟接入

MyBatis 集成位于独立 Artifact。除 `dlz-caller` 外，再引入：

```xml
<dependency>
    <groupId>top.dlzio</groupId>
    <artifactId>dlz-caller-mybatis</artifactId>
    <version>6.7.0</version>
</dependency>
```

### 8.1 开启 SQL logger

Logback：

```xml
<logger name="sql" level="DEBUG"/>
```

### 8.2 原生 MyBatis XML

将插件放在分页、租户等 SQL 重写插件之后：

```xml
<plugins>
    <!-- 分页、租户等 SQL 重写插件在前 -->

    <plugin interceptor="com.dlz.caller.mybatis.DlzMybatisSqlLogInterceptor">
        <property name="enabled" value="true"/>
        <property name="showCaller" value="true"/>
        <property name="showMapper" value="true"/>
        <property name="injectCallerMdc" value="true"/>
        <property name="ignoreCallerPackages"
                  value="com.example.persistence.,com.example.infrastructure."/>
    </plugin>
</plugins>
```

执行：

```java
orderMapper.findById(7L);
```

输出：

```text
(OrderService.java:86) OrderMapper.findById 12ms sql=select id, name from orders where id = 7
```

其中 `12ms` 是应用侧 SQL 总耗时，包含 JDBC、网络 IO、数据库处理、结果读取和 MyBatis 映射，
不等于数据库服务端纯执行时间。

### 8.3 Spring Boot / MyBatis-Plus

```java
@Bean
public DlzMybatisSqlLogInterceptor dlzMybatisSqlLogInterceptor() {
    DlzSqlLogProperties properties = new DlzSqlLogProperties();
    properties.addIgnoreCallerPackage("com.example.persistence.");
    return new DlzMybatisSqlLogInterceptor(properties);
}
```

## 9. 启用后检查清单

1. 日志 pattern 中有 `%X{caller}`。
2. 公共工具类入口已使用 `DlzCaller.caller()` 或 `setCaller/clearCaller`。
3. 公共组件所在包已加入 `ignoreCallerPackages`。
4. MyBatis 项目已启用 `sql` logger 的 `DEBUG`。
5. MyBatis 插件位于其他 SQL 重写插件之后。
6. 线程池和异步任务使用项目已有的 MDC 传播方案。

## 10. 常见问题

### 日志中 caller 为空

检查日志是否发生在 caller scope 内；检查 `%X{caller}` 是否配置；检查 `injectCallerMdc` 是否为 `true`。

### caller 仍然显示 HttpClientUtil 或 RedisClient

将该工具类所在包加入忽略列表：

```java
DlzCaller.getProperties().addIgnoreCallerPackage("com.example.http.");
```

### caller 显示成代理类

将代理所在包加入忽略列表。不要只依赖固定层级 `n`，包过滤对代理层数变化更稳定。

### 异步日志没有 caller

caller 基于 ThreadLocal MDC。线程池、`CompletableFuture`、Reactor 等异步切换需要自行传播 MDC。

### SQL 没有输出

确认 logger 名称是 `sql`，级别为 `DEBUG`；确认 MyBatis 插件已经注册；确认插件在 SQL 重写插件之后。

### SQL 中包含敏感参数

可执行 SQL 用于诊断。生产环境请控制 `sql` logger 开关、日志权限和保留期限；当前版本不自动脱敏。

## 下一步

- 完整功能与边界说明见 [README](../README.md)。
- 可执行行为示例见 `src/test/java/com/dlz/log` 和 `src/test/java/com/dlz/log/mybatis`。
