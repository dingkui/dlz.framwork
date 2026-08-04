# dlz-caller-mybatis 30 秒上手

## 初始化配置
```java
import com.dlz.caller.mybatis.DlzMybatisSqlLogInterceptor;
import com.dlz.caller.mybatis.DlzSqlLogProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DlzMybatisSqlLogConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "dlz.caller.mybatis.sql-log")
    public DlzSqlLogProperties dlzSqlLogProperties() {
        return new DlzSqlLogProperties();
    }
    @Bean
    public DlzMybatisSqlLogInterceptor dlzMybatisSqlLogInterceptor(
            DlzSqlLogProperties properties) {
        return new DlzMybatisSqlLogInterceptor(properties);
    }
}
```
## 配置文件
`application.yml` 示例：

```yaml
dlz:
  caller:
    mybatis:
      sql-log:
        enabled: true
        show-caller: true
        show-mapper: true
        inject-caller-mdc: true
        caller-mdc-key: caller
        ignore-caller-packages:
          - com.example.persistence.
```

## Logback配置

```xml
<logger name="sql" level="DEBUG"/>
```

## 运行结果
执行：

```java
orderMapper.findById(7L);
```

输出：

```text
(OrderService.java:86) OrderMapper.findById 12ms sql=select id, name from orders where id = 7
```
