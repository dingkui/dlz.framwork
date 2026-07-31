package com.dlz.kit.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ExceptionTrace测试")
class ExceptionTraceTest {

    private static final Pattern DLZ_PATTERN = Pattern.compile("^com\\.(dlz)");

    @Test
    @DisplayName("基本堆栈输出")
    void testBasicStackTrace() {
        Exception ex = new RuntimeException("basic error");
        ExceptionTrace trace = new ExceptionTrace(ex, true, DLZ_PATTERN);
        String result = trace.getStackTrace(null);
        assertNotNull(result);
        assertTrue(result.contains("basic error"));
    }

    @Test
    @DisplayName("带消息的堆栈输出")
    void testStackTraceWithMessage() {
        Exception ex = new RuntimeException("err");
        ExceptionTrace trace = new ExceptionTrace(ex, true, DLZ_PATTERN);
        String result = trace.getStackTrace("prefix message");
        assertTrue(result.startsWith("prefix message"));
    }

    @Test
    @DisplayName("带cause的堆栈输出")
    void testStackTraceWithCause() {
        Exception cause = new IllegalStateException("cause msg");
        Exception ex = new RuntimeException("top", cause);
        ExceptionTrace trace = new ExceptionTrace(ex, true, DLZ_PATTERN);
        String result = trace.getStackTrace(null);
        assertTrue(result.contains("Caused by:"));
        assertTrue(result.contains("cause msg"));
    }

    @Test
    @DisplayName("带suppressed异常的堆栈输出")
    void testStackTraceWithSuppressed() {
        Exception ex = new RuntimeException("main");
        ex.addSuppressed(new IllegalArgumentException("suppressed1"));
        ExceptionTrace trace = new ExceptionTrace(ex, true, DLZ_PATTERN);
        String result = trace.getStackTrace(null);
        assertTrue(result.contains("Suppressed:"));
        assertTrue(result.contains("suppressed1"));
    }

    @Test
    @DisplayName("onlyShowAppLog=false 不过滤")
    void testNoFilteringWhenDisabled() {
        Exception ex = new RuntimeException("no filter");
        ExceptionTrace trace = new ExceptionTrace(ex, false, DLZ_PATTERN);
        String result = trace.getStackTrace(null);
        assertNotNull(result);
        assertTrue(result.contains("no filter"));
    }

    @Test
    @DisplayName("marchPattern=null 不过滤")
    void testNullPatternNoFiltering() {
        Exception ex = new RuntimeException("null pattern");
        ExceptionTrace trace = new ExceptionTrace(ex, true, null);
        String result = trace.getStackTrace(null);
        assertNotNull(result);
    }

    @Test
    @DisplayName("getTraceElement 过滤CGLIB")
    void testGetTraceElementFiltersCGLIB() {
        StackTraceElement cglibElement = new StackTraceElement(
                "com.example.SomeClass$$EnhancerByCGLIB$$abc", "method", "file.java", 10);
        ExceptionTrace trace = new ExceptionTrace(new RuntimeException(), true, DLZ_PATTERN);
        assertNull(trace.getTraceElement(cglibElement));
    }

    @Test
    @DisplayName("getTraceElement 匹配pattern的元素返回")
    void testGetTraceElementMatchesPattern() {
        StackTraceElement element = new StackTraceElement(
                "com.dlz.kit.util.ValUtil", "toStr", "ValUtil.java", 50);
        ExceptionTrace trace = new ExceptionTrace(new RuntimeException(), true, DLZ_PATTERN);
        String result = trace.getTraceElement(element);
        assertNotNull(result);
        assertTrue(result.contains("com.dlz.kit.util.ValUtil"));
    }

    @Test
    @DisplayName("getTraceElement 不匹配pattern的元素返回null")
    void testGetTraceElementNoMatch() {
        StackTraceElement element = new StackTraceElement(
                "org.springframework.web.Servlet", "service", "Servlet.java", 100);
        ExceptionTrace trace = new ExceptionTrace(new RuntimeException(), true, DLZ_PATTERN);
        assertNull(trace.getTraceElement(element));
    }
}
