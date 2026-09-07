package com.dlz.caller.mybatis;

import com.dlz.caller.DlzCaller;
import com.dlz.caller.DlzCallerResolver;
import com.dlz.kit.mdc.MdcContext;
import lombok.AllArgsConstructor;
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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.sql.Statement;
import java.util.Properties;

/**
 * Logs diagnostic SQL with parameter values and the application caller.
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
        final boolean logSql = LOG.isDebugEnabled();
        final boolean detailed = logSql && properties.isShowCaller() && LOG.isTraceEnabled();
        final boolean injectCallerMdc = properties.isInjectCallerMdc();
        if (!logSql && !injectCallerMdc) {
            return invocation.proceed();
        }
        String caller = "";
        String callerPath = "";
        MdcContext callerContext = null;
        try {
            if ((logSql && properties.isShowCaller()) || injectCallerMdc) {
                DlzCallerResolver.CallerDetails location = DlzCallerResolver.resolveDetails(properties, detailed);
                caller = location.getCaller();
                if (detailed && !location.getSkippedFrames().isEmpty()) {
                    StringBuilder path = new StringBuilder(" | caller-path: ").append(caller);
                    for (int i = location.getSkippedFrames().size() - 1; i >= 0; i--) {
                        path.append(" -> ").append(location.getSkippedFrames().get(i));
                    }
                    callerPath = path.toString();
                }
                if (injectCallerMdc) {
                    callerContext = DlzCaller.open(caller);
                }
            }
        } catch (Exception | LinkageError diagnosticFailure) {
            // Diagnostic setup must never prevent the business operation from executing.
            return invocation.proceed();
        }
        long startTime = System.nanoTime();
        try {
            return invocation.proceed();
        } finally {
            try {
                if (logSql) {
                    long elapsedMillis = (System.nanoTime() - startTime) / 1_000_000;
                    SqlLogDetails val = getSqlLogDetails(invocation, caller);
                    LOG.debug("{}{} {}ms => {}{}", val.caller, val.mapper, elapsedMillis, val.sql, callerPath);
                }
            } catch (Exception | LinkageError diagnosticFailure) {
                // Preserve the result/database exception. The same logger may itself be broken.
            } finally {
                if (callerContext != null) {
                    try {
                        callerContext.close();
                    } catch (Exception | LinkageError diagnosticFailure) {
                        // MDC cleanup is diagnostic-only too.
                    }
                }
            }
        }
    }
    @AllArgsConstructor
    private static class SqlLogDetails {
        final String caller;
        final String mapper;
        final String sql;
    }
    private SqlLogDetails getSqlLogDetails(Invocation invocation, String caller){
        StatementHandler handler = (StatementHandler) invocation.getTarget();
        StatementHandler metadataHandler = handler;
        while (Proxy.isProxyClass(metadataHandler.getClass())) {
            InvocationHandler proxyHandler = Proxy.getInvocationHandler(metadataHandler);
            if (!(proxyHandler instanceof org.apache.ibatis.plugin.Plugin)) {
                break;
            }
            metadataHandler = (StatementHandler) SystemMetaObject.forObject(proxyHandler).getValue("target");
        }
        MetaObject metaObject = SystemMetaObject.forObject(metadataHandler);
        BoundSql boundSql = handler.getBoundSql();
        Configuration config = (Configuration) metaObject.getValue("delegate.configuration");
        String mapper = properties.isShowMapper() ? DlzMybatisSqlLogFormatter.getMapper(metaObject) : "";
        String callerText = properties.isShowCaller() && !caller.isEmpty() ? caller + " " : "";
        String sql = DlzMybatisSqlLogFormatter.toExecutableSql(config, boundSql);
        return new SqlLogDetails(callerText, mapper, sql);
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
