# MyBatis / MyBatis-Plus 调用者 SQL 日志

已有 Spring Boot + MyBatis 或 MyBatis-Plus 应用添加以下依赖，即可自动注册插件。Boot 2、Boot 3 使用同一坐标，无需配置类，不替换应用已有的 MyBatis Starter。

```xml
<dependency>
    <groupId>top.dlzio</groupId>
    <artifactId>dlz-caller-mybatis-spring-boot-starter</artifactId>
    <version>6.7.5</version>
</dependency>
```

插件自动注册，SQL 作为诊断日志固定使用 DEBUG 级别。需要查看时，只需在 Logback 中配置：

```xml
<logger name="dlz-sql" level="DEBUG"/>
```

或在 Spring Boot 中配置 `logging.level.dlz-sql=DEBUG`。改为 INFO / WARN / OFF 即可停止 SQL 诊断输出。插件不修改全局日志级别、不引入日志实现，也不替应用配置数据源。

```text
(OrderService.java:86)  12ms => select id, status from orders where id = 7
```

## 自定义配置

以下均为可选项：

```yaml
dlz:
  caller:
    mybatis:
      sql-log:
        enabled: true
        show-caller: true
        show-mapper: false
        inject-caller-mdc: false
        ignore-caller-packages:
          - com.example.persistence.
```

`enabled: false` 禁止 Starter 注册插件。关闭对应日志级别且未开启 MDC 注入时，不抓栈、不拼 SQL。

## TRACE 定位详情

仅需打开 logger 的 TRACE 级别，保留 `show-caller: true`：

```yaml
logging:
  level:
    dlz-sql: TRACE
```

SQL 仍以 DEBUG 级别输出，但同一条日志末尾会追加 ` | caller-path: (OrderService.java:86) -> ...`。不额外打印一条 TRACE 日志，不重复输出 SQL。路径从业务位置走向被跳过的框架/公共封装帧，保留类名、方法和源码坐标；省略解析器自身、JDK 反射及部分生成代理噪声。

这是本次调用位置解析经过的框架路径，不是完整业务调用链，也不包含异步提交前的路径。详情与业务定位复用一次抓栈；非 TRACE 模式不创建详情列表、不格式化路径。`show-caller: false` 时不输出详情；Starter 启动时配置 `enabled: false` 则不注册插件。

公共解析器提供 `DlzCallerResolver.resolveDetails(properties, detailed)`，返回不可变的 `CallerDetails`（`getCaller()`、`getSkippedFrames()`）；帧列表按内层到外层排列，日志层反转为调用方向。解析器不自行打印日志，旧 `resolve()` 接口保持可用。

## 自定义 Bean

提供自己的 `DlzSqlLogProperties` Bean 可完全接管配置，Starter 会使用它；如需 YAML 绑定，请自行添加 `@ConfigurationProperties(prefix = "dlz.caller.mybatis.sql-log")`。

提供自己的 `DlzMybatisSqlLogInterceptor` Bean 时，自动配置不再创建默认拦截器，避免重复打印：

```java
@Bean
public DlzMybatisSqlLogInterceptor customSqlLogger() {
    DlzSqlLogProperties properties = new DlzSqlLogProperties();
    properties.setShowMapper(true);
    return new DlzMybatisSqlLogInterceptor(properties);
}
```

`enabled` 仅是注册阶段开关，不参与每次 SQL 执行判断，也不会动态移除已注册的插件。Starter 根据启动配置决定是否创建 Bean；非 Boot、自定义 Bean、手动工厂必须在创建/注册前自行判断 `properties.isEnabled()`。已注册插件的输出由 Logback 控制；MyBatis XML 中给已声明插件设置 `enabled=false` 也不会取消该插件注册。手动创建 SqlSessionFactory 的应用仍需自行把拦截器设置到工厂；自动接入依赖 MyBatis / MyBatis-Plus 标准 Boot 自动配置。

## 非 Boot 与升级

非 Boot 应用继续使用 `top.dlzio:dlz-caller-mybatis:6.7.5`，手动注册拦截器。基础插件与 Starter 都使用 DEBUG，由日志框架统一控制。

从 6.7.4 升级可替换为 Starter 依赖。原有拦截器 Bean 可保留（自动配置退让），也可移除并使用 YAML。

## 行为与边界

- 普通诊断异常和链接错误会被隔离：参数提取、SQL 格式化、调用位置解析或日志输出失败，不改变业务结果，不覆盖原数据库异常。失败的日志被跳过；JVM 致命错误不在恢复承诺内。
- 普通 SQL 空白压缩为单行，压缩前移除 `--` 行注释，避免后续 SQL 被注释吞掉；字符串字面量中的内容保持原样。
- SQL 是诊断展示，不保证完整复现 TypeHandler、数据库方言和 JDBC 绑定。参数日志可能包含业务数据，请按应用要求管理输出。
- 覆盖 StatementHandler.query/queryCursor/update，不覆盖 batch。
- 调用位置为当前线程首个未忽略栈帧；公共 DAO 可通过忽略包跳过，不自动还原异步提交前的栈。
- 开启定位存在抓栈成本，不承诺零开销。
- 同时提供 Boot 2 spring.factories 和 Boot 3 AutoConfiguration.imports 入口；自定义 Bean 优先。

## 开发验证

从仓库根目录执行；Boot 3 测试需要 JDK 17+：

```bash
mvn -pl dlz-kit-plugins/dlz-caller-mybatis-spring-boot-starter -am test
mvn -pl dlz-kit-plugins/dlz-caller-mybatis-spring-boot-starter -am test -Pboot3
mvn -pl dlz-kit-plugins/dlz-caller-mybatis-spring-boot-starter -am test -Pplus2
mvn -pl dlz-kit-plugins/dlz-caller-mybatis-spring-boot-starter -am test -Pplus3
```

通过真实 H2 查询验证自动发现、唯一注册、调用位置日志、配置绑定、禁用、自定义 Bean 及无 MyBatis 时退让。
