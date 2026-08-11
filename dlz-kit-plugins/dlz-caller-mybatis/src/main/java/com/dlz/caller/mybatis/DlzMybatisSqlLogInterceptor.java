package com.dlz.caller.mybatis;

import com.dlz.caller.DlzCaller;
import com.dlz.caller.DlzCallerResolver;
import com.dlz.kit.mdc.MdcContext;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Statement;
import java.util.Properties;

/**
 * Logs the final SQL sent by MyBatis to JDBC, including bound parameter values and caller.
 *
 * <p>Register this interceptor after SQL-rewriting plugins so pagination and similar rewrites
 * are included in the emitted SQL.</p>
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "queryCursor", args = {Statement.class}),
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class})
})
public class DlzMybatisSqlLogInterceptor implements Interceptor {
    private static final Logger LOG = LoggerFactory.getLogger("dlz-sql");
    private final DlzSqlLogProperties properties;

    public DlzMybatisSqlLogInterceptor() {
        this(null);
    }

    public DlzMybatisSqlLogInterceptor(DlzSqlLogProperties properties) {
        this.properties = properties == null ? new DlzSqlLogProperties() : properties;
        // 统一使用 this.properties（已处理 null），避免对原始参数解引用导致 NPE
        this.properties.addIgnoreCallerPackage("org.apache.ibatis");
        this.properties.addIgnoreCallerPackage("org.mybatis");
        this.properties.addIgnoreCallerPackage("org.springframework");
        this.properties.addIgnoreCallerPackage("com.baomidou");
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        boolean logSql = properties.isEnabled() && LOG.isDebugEnabled();
        boolean injectCallerMdc = properties.isEnabled() && properties.isInjectCallerMdc();
        if (logSql || injectCallerMdc) {
            long startTime = System.currentTimeMillis();
            String caller = (logSql && properties.isShowCaller()) || injectCallerMdc
                    ? DlzCallerResolver.resolve(properties) : "";
            MdcContext callerContext = injectCallerMdc ? DlzCaller.open(caller) : null;
            try {
                return invocation.proceed();
            } finally {
                try {
                    if (logSql) {
                        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
                        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
                        BoundSql boundSql = statementHandler.getBoundSql();
                        Configuration config = (Configuration) metaObject.getValue("delegate.configuration");
                        String mapper = properties.isShowMapper() ? DlzMybatisSqlLogFormatter.getMapper(metaObject) : "";
                        String callerText = properties.isShowCaller() && !caller.isEmpty() ? caller + " " : "";
                        LOG.debug("{}{} {}ms => {}", callerText, mapper,
                                System.currentTimeMillis() - startTime,
                                DlzMybatisSqlLogFormatter.toExecutableSql(config, boundSql));
                    }
                } finally {
                    if (callerContext != null) {
                        callerContext.close();
                    }
                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public void setProperties(Properties source) {
        if (source == null) {
            return;
        }
        if (source.getProperty("enabled") != null) {
            properties.setEnabled(Boolean.parseBoolean(source.getProperty("enabled")));
        }
        if (source.getProperty("showCaller") != null) {
            properties.setShowCaller(Boolean.parseBoolean(source.getProperty("showCaller")));
        }
        if (source.getProperty("showMapper") != null) {
            properties.setShowMapper(Boolean.parseBoolean(source.getProperty("showMapper")));
        }
        if (source.getProperty("injectCallerMdc") != null) {
            properties.setInjectCallerMdc(Boolean.parseBoolean(source.getProperty("injectCallerMdc")));
        }
        String ignoredPackages = source.getProperty("ignoreCallerPackages");
        if (ignoredPackages != null) {
            for (String ignoredPackage : ignoredPackages.split(",")) {
                properties.addIgnoreCallerPackage(ignoredPackage);
            }
        }
        properties.addIgnoreCallerPackage("org.apache.ibatis");
        properties.addIgnoreCallerPackage("org.mybatis");
        properties.addIgnoreCallerPackage("org.springframework");
        properties.addIgnoreCallerPackage("com.baomidou");
    }

    public DlzSqlLogProperties getProperties() {
        return properties;
    }
}
