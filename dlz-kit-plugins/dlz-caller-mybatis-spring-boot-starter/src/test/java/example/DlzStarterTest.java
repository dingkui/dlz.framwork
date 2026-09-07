package example;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.dlz.caller.mybatis.DlzMybatisSqlLogInterceptor;
import com.dlz.caller.mybatis.DlzSqlLogProperties;
import com.dlz.caller.mybatis.autoconfigure.DlzMybatisSqlLogAutoConfiguration;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.FilteredClassLoader;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.Statement;

import static org.assertj.core.api.Assertions.assertThat;

class DlzStarterTest {
    private final ApplicationContextRunner runner = new ApplicationContextRunner()
            .withUserConfiguration(Application.class)
            .withPropertyValues("spring.datasource.url=jdbc:h2:mem:dlz;DB_CLOSE_DELAY=-1",
                    "spring.datasource.driver-class-name=org.h2.Driver");

    @Configuration(proxyBeanMethods = false)
    @EnableAutoConfiguration
    static class Application {
        @Bean
        Interceptor otherPlugin() {
            return new OtherPlugin();
        }
    }

    @Intercepts({
            @Signature(type = StatementHandler.class,
                    method = "query", args = {Statement.class, ResultHandler.class})
    })
    public static class OtherPlugin implements Interceptor {
        @Override
        public Object intercept(Invocation invocation) throws Throwable {
            return invocation.proceed();
        }
    }

    public interface ProbeMapper {
        @Select("select 42")
        int answer();
    }

    @Test
    void dependencyAloneRegistersExactlyOnePluginAndLogsRealQuery() {
        runner.run(context -> {
            assertThat(context).hasNotFailed().hasSingleBean(DlzMybatisSqlLogInterceptor.class);
            DlzMybatisSqlLogInterceptor plugin = context.getBean(DlzMybatisSqlLogInterceptor.class);
            assertThat(plugin.getProperties().getLogLevel()).isEqualTo(DlzSqlLogProperties.LogLevel.INFO);
            SqlSessionFactory factory = context.getBean(SqlSessionFactory.class);
            assertThat(factory.getConfiguration().getInterceptors().stream()
                    .filter(DlzMybatisSqlLogInterceptor.class::isInstance).count()).isEqualTo(1);
            factory.getConfiguration().addMapper(ProbeMapper.class);
            Logger logger = (Logger) LoggerFactory.getLogger("dlz-sql");
            ListAppender<ILoggingEvent> appender = new ListAppender<>();
            appender.start();
            logger.addAppender(appender);
            try (SqlSession session = factory.openSession()) {
                assertThat(session.getMapper(ProbeMapper.class).answer()).isEqualTo(42);
                assertThat(appender.list).anySatisfy(event -> {
                    assertThat(event.getFormattedMessage()).contains("select 42", "DlzStarterTest.java:");
                    assertThat(event.getLevel().toString()).isEqualTo("INFO");
                });
            } finally {
                logger.detachAppender(appender);
                appender.stop();
            }
        });
    }

    @Test
    void bindsCustomSettings() {
        runner.withPropertyValues("dlz.caller.mybatis.sql-log.log-level=DEBUG",
                        "dlz.caller.mybatis.sql-log.show-mapper=true",
                        "dlz.caller.mybatis.sql-log.show-caller=false",
                        "dlz.caller.mybatis.sql-log.ignore-caller-packages[0]=example.dao.")
                .run(context -> {
                    assertThat(context).hasNotFailed();
                    DlzSqlLogProperties p = context.getBean(DlzSqlLogProperties.class);
                    assertThat(p.getLogLevel()).isEqualTo(DlzSqlLogProperties.LogLevel.DEBUG);
                    assertThat(p.isShowMapper()).isTrue();
                    assertThat(p.isShowCaller()).isFalse();
                    assertThat(p.getIgnoreCallerPackages()).contains("example.dao.", "org.apache.ibatis");
                });
    }

    @Test
    void disabledDoesNotRegisterPlugin() {
        runner.withPropertyValues("dlz.caller.mybatis.sql-log.enabled=false").run(context ->
                assertThat(context).hasNotFailed().doesNotHaveBean(DlzMybatisSqlLogInterceptor.class));
    }

    @Test
    void customInterceptorWinsWithoutDuplicateRegistration() {
        runner.withUserConfiguration(CustomInterceptor.class).run(context -> {
            assertThat(context).hasNotFailed().hasSingleBean(DlzMybatisSqlLogInterceptor.class);
            assertThat(context.getBean(DlzMybatisSqlLogInterceptor.class))
                    .isSameAs(context.getBean("customInterceptor"));
            assertThat(context.getBean(SqlSessionFactory.class).getConfiguration().getInterceptors()
                    .stream().filter(DlzMybatisSqlLogInterceptor.class::isInstance).count()).isEqualTo(1);
        });
    }

    @Test
    void customPropertiesAreUsed() {
        runner.withUserConfiguration(CustomProperties.class).run(context -> {
            assertThat(context).hasNotFailed().hasSingleBean(DlzSqlLogProperties.class);
            assertThat(context.getBean(DlzMybatisSqlLogInterceptor.class).getProperties())
                    .isSameAs(context.getBean("customProperties"));
        });
    }

    @Test
    void absentMybatisDoesNotLoadPlugin() {
        new ApplicationContextRunner().withConfiguration(AutoConfigurations.of(
                        DlzMybatisSqlLogAutoConfiguration.class))
                .withClassLoader(new FilteredClassLoader("org.apache.ibatis"))
                .run(context -> assertThat(context).hasNotFailed()
                        .doesNotHaveBean(DlzMybatisSqlLogInterceptor.class));
    }

    @Configuration(proxyBeanMethods = false)
    static class CustomInterceptor {
        @Bean
        DlzMybatisSqlLogInterceptor customInterceptor() {
            return new DlzMybatisSqlLogInterceptor();
        }
    }

    @Configuration(proxyBeanMethods = false)
    static class CustomProperties {
        @Bean
        DlzSqlLogProperties customProperties() {
            DlzSqlLogProperties p = new DlzSqlLogProperties();
            p.setShowMapper(true);
            return p;
        }
    }
}
