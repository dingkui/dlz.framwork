# MyBatis / MyBatis-Plus 调用者 SQL 日志

已有 Spring Boot + MyBatis 或 MyBatis-Plus 应用添加以下依赖，即可自动注册插件。Boot 2、Boot 3 使用同一坐标，无需配置类，不替换应用已有的 MyBatis Starter。

```xml
<dependency>
    <groupId>top.dlzio</groupId>
    <artifactId>dlz-caller-mybatis-spring-boot-starter</artifactId>
    <version>6.7.5</version>
</dependency>
```

默认开启，以 `dlz-sql` logger 的 INFO 级别输出调用位置、耗时及 SQL 诊断展示。若应用禁用了 INFO，请自行设置 `logging.level.dlz-sql=INFO`。插件不修改全局日志级别、不引入日志实现，也不替应用配置数据源。

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
        log-level: INFO          # INFO 或 DEBUG；Starter 默认 INFO
        show-caller: true
        show-mapper: false
        inject-caller-mdc: false
        ignore-caller-packages:
          - com.example.persistence.
```

`enabled: false` 禁止 Starter 注册插件。改用 DEBUG 时需要应用允许该 logger 的 DEBUG 日志。关闭对应日志级别且未开启 MDC 注入时，不抓栈、不拼 SQL。

## 自定义 Bean

提供自己的 `DlzSqlLogProperties` Bean 可完全接管配置，Starter 会使用它；如需 YAML 绑定，请自行添加 `@ConfigurationProperties(prefix = "dlz.caller.mybatis.sql-log")`。

提供自己的 `DlzMybatisSqlLogInterceptor` Bean 时，自动配置不再创建默认拦截器，避免重复打印：

```java
@Bean
public DlzMybatisSqlLogInterceptor customSqlLogger() {
    DlzSqlLogProperties properties = new DlzSqlLogProperties();
    properties.setLogLevel(DlzSqlLogProperties.LogLevel.DEBUG);
    properties.setShowMapper(true);
    return new DlzMybatisSqlLogInterceptor(properties);
}
```

`enabled: false` 只控制自动配置，不会移除用户 Bean。手动创建 SqlSessionFactory 的应用仍需自行把拦截器设置到工厂；自动接入依赖 MyBatis / MyBatis-Plus 标准 Boot 自动配置。

## 非 Boot 与升级

非 Boot 应用继续使用 `top.dlzio:dlz-caller-mybatis:6.7.5`，手动注册拦截器。基础插件保留 DEBUG 默认值；新增 Starter 默认 INFO。

从 6.7.4 升级可替换为 Starter 依赖。原有拦截器 Bean 可保留（自动配置退让），也可移除并使用 YAML。

## 行为与边界

- 普通诊断异常和链接错误会被隔离：参数提取、SQL 格式化、调用位置解析或日志输出失败，不改变业务结果，不覆盖原数据库异常。失败的日志被跳过；JVM 致命错误不在恢复承诺内。
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
