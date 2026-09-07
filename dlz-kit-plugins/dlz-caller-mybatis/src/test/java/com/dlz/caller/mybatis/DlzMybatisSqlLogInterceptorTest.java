package com.dlz.caller.mybatis;

import com.dlz.caller.DlzCaller;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.session.*;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;
import java.sql.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class DlzMybatisSqlLogInterceptorTest {
    private ch.qos.logback.classic.Level originalLevel;
    @org.junit.jupiter.api.BeforeEach void enableDiagnosticLogging() {
        ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory.getLogger("dlz-sql");
        originalLevel = logger.getLevel();
        logger.setLevel(ch.qos.logback.classic.Level.DEBUG);
    }
    @org.junit.jupiter.api.AfterEach void restoreDiagnosticLogging() {
        ((ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory.getLogger("dlz-sql")).setLevel(originalLevel);
    }
    public static class Handler implements StatementHandler {
        public final Delegate delegate = new Delegate();
        int executions;
        int formattingAttempts;
        SQLException failure;
        public Statement prepare(Connection c, Integer timeout) { return null; }
        public void parameterize(Statement s) {}
        public void batch(Statement s) {}
        public int update(Statement s) throws SQLException {
            executions++;
            if (failure != null) throw failure;
            return 7;
        }
        public <E> List<E> query(Statement s, ResultHandler rh) { return null; }
        public <E> Cursor<E> queryCursor(Statement s) { return null; }
        public ParameterHandler getParameterHandler() { return null; }
        public BoundSql getBoundSql() {
            Map<String,Object> values = new HashMap<>();
            values.put("value", new Object() {
                @Override public String toString() { formattingAttempts++; throw new IllegalStateException("broken parameter"); }
            });
            return new BoundSql(delegate.configuration, "select ?",
                    Collections.singletonList(new ParameterMapping.Builder(
                            delegate.configuration, "value", Object.class).build()), values);
        }
    }
    public static class Delegate {
        public Configuration configuration = new Configuration();
    }
    private Object execute(Handler handler) throws Throwable {
        DlzSqlLogProperties properties = new DlzSqlLogProperties();
        properties.setInjectCallerMdc(true);
        return new DlzMybatisSqlLogInterceptor(properties).intercept(new Invocation(handler,
                StatementHandler.class.getMethod("update", Statement.class), new Object[]{null}));
    }
    @Test void formattingFailurePreservesSuccessfulResultAndMdc() throws Throwable {
        Handler handler = new Handler();
        MDC.put(DlzCaller.MDC_KEY_DLZ_CALLER, "outer");
        try {
            assertEquals(7, execute(handler));
            assertEquals(1, handler.executions);
            assertEquals(1, handler.formattingAttempts);
            assertEquals("outer", MDC.get(DlzCaller.MDC_KEY_DLZ_CALLER));
        } finally { MDC.remove(DlzCaller.MDC_KEY_DLZ_CALLER); }
    }
    @Test void formattingFailureDoesNotMaskDatabaseFailure() {
        Handler handler = new Handler();
        handler.failure = new SQLException("database failed");
        Throwable failure = assertThrows(Throwable.class, () -> execute(handler));
        assertSame(handler.failure, failure.getCause());
        assertEquals(1, handler.executions);
        assertEquals(1, handler.formattingAttempts);
        assertNull(MDC.get(DlzCaller.MDC_KEY_DLZ_CALLER));
    }
    @Test void callerResolutionFailureStillExecutesBusinessExactlyOnce() throws Throwable {
        DlzSqlLogProperties properties = new DlzSqlLogProperties() {
            @Override public Set<String> getIgnoreCallerPackages() {
                throw new IllegalStateException("caller failed");
            }
        };
        Handler handler = new Handler();
        assertEquals(7, new DlzMybatisSqlLogInterceptor(properties).intercept(new Invocation(handler,
                StatementHandler.class.getMethod("update", Statement.class), new Object[]{null})));
        assertEquals(1, handler.executions);
    }

    @Test void traceAddsDetailsToOneSqlEventAndRespectsShowCaller() throws Throwable {
        ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger)
                org.slf4j.LoggerFactory.getLogger("dlz-sql");
        ch.qos.logback.classic.Level previous = logger.getLevel();
        ch.qos.logback.core.read.ListAppender<ch.qos.logback.classic.spi.ILoggingEvent> appender =
                new ch.qos.logback.core.read.ListAppender<>();
        appender.start();
        logger.addAppender(appender);
        try {
            Handler handler = new Handler() {
                @Override public BoundSql getBoundSql() {
                    return new BoundSql(delegate.configuration, "select 42", Collections.emptyList(), null);
                }
            };
            DlzSqlLogProperties p = new DlzSqlLogProperties();
            DlzMybatisSqlLogInterceptor interceptor = new DlzMybatisSqlLogInterceptor(p);
            logger.setLevel(ch.qos.logback.classic.Level.TRACE);
            interceptor.intercept(new Invocation(handler, StatementHandler.class.getMethod("update", Statement.class), new Object[]{null}));
            assertEquals(1, appender.list.size());
            String message = appender.list.get(0).getFormattedMessage();
            assertTrue(message.contains("select 42 | caller-path:"));
            assertTrue(message.contains("DlzMybatisSqlLogInterceptor.intercept"));
            assertFalse(message.contains("DlzCallerResolver.resolve"));

            appender.list.clear();
            logger.setLevel(ch.qos.logback.classic.Level.DEBUG);
            interceptor.intercept(new Invocation(handler, StatementHandler.class.getMethod("update", Statement.class), new Object[]{null}));
            assertEquals(1, appender.list.size());
            assertFalse(appender.list.get(0).getFormattedMessage().contains("caller-path:"));

            appender.list.clear();
            logger.setLevel(ch.qos.logback.classic.Level.TRACE);
            p.setShowCaller(false);
            interceptor.intercept(new Invocation(handler, StatementHandler.class.getMethod("update", Statement.class), new Object[]{null}));
            assertEquals(1, appender.list.size());
            assertFalse(appender.list.get(0).getFormattedMessage().contains("caller-path:"));

            appender.list.clear();
            p.setEnabled(false);
            interceptor.intercept(new Invocation(handler, StatementHandler.class.getMethod("update", Statement.class), new Object[]{null}));
            // Once registered, enabled is not a per-call switch; logging controls output.
            assertEquals(1, appender.list.size());
            assertEquals(4, handler.executions);
            appender.list.clear();
            logger.setLevel(ch.qos.logback.classic.Level.INFO);
            DlzSqlLogProperties failOnCapture = new DlzSqlLogProperties() {
                @Override public Set<String> getIgnoreCallerPackages() {
                    fail("INFO must not capture the caller stack");
                    return Collections.emptySet();
                }
            };
            new DlzMybatisSqlLogInterceptor(failOnCapture).intercept(new Invocation(handler,
                    StatementHandler.class.getMethod("update", Statement.class), new Object[]{null}));
            assertTrue(appender.list.isEmpty());
            assertEquals(5, handler.executions);
        } finally {
            logger.setLevel(previous);
            logger.detachAppender(appender);
            appender.stop();
        }
    }
}
