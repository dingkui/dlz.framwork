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
        properties.setLogLevel(DlzSqlLogProperties.LogLevel.INFO);
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
        properties.setLogLevel(DlzSqlLogProperties.LogLevel.INFO);
        Handler handler = new Handler();
        assertEquals(7, new DlzMybatisSqlLogInterceptor(properties).intercept(new Invocation(handler,
                StatementHandler.class.getMethod("update", Statement.class), new Object[]{null})));
        assertEquals(1, handler.executions);
    }
}
