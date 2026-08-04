package com.dlz.caller.mybatis;

import com.dlz.caller.DlzCallerContext;
import com.dlz.caller.DlzCallerResolver;
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
        properties.addIgnoreCallerPackage("org.apache.ibatis");
        properties.addIgnoreCallerPackage("org.mybatis");
        properties.addIgnoreCallerPackage("org.springframework");
        properties.addIgnoreCallerPackage("com.baomidou");
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        boolean logSql = properties.isEnabled() && LOG.isDebugEnabled();
        boolean injectCallerMdc = properties.isEnabled() && properties.isInjectCallerMdc();
        if (logSql || injectCallerMdc) {
            long startTime = System.currentTimeMillis();
            String caller = (logSql && properties.isShowCaller()) || injectCallerMdc
                    ? DlzCallerResolver.resolve(properties) : "";
            DlzCallerContext callerContext = injectCallerMdc ? DlzCallerContext.open(caller) : null;
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

    public DlzSqlLogProperties getProperties() {
        return properties;
    }
}
