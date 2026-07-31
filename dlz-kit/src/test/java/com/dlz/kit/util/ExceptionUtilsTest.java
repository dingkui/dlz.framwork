package com.dlz.kit.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ExceptionUtils测试")
class ExceptionUtilsTest {

    @Test
    @DisplayName("getStackTrace(Throwable) 基本调用")
    void testGetStackTraceBasic() {
        Exception ex = new RuntimeException("test error");
        String trace = ExceptionUtils.getStackTrace(ex);
        assertNotNull(trace);
        assertTrue(trace.contains("test error"));
        assertTrue(trace.contains("RuntimeException"));
    }

    @Test
    @DisplayName("getStackTrace(msg, Throwable) 带消息")
    void testGetStackTraceWithMessage() {
        Exception ex = new RuntimeException("inner");
        String trace = ExceptionUtils.getStackTrace("context msg", ex);
        assertNotNull(trace);
        assertTrue(trace.contains("context msg"));
        assertTrue(trace.contains("inner"));
    }

    @Test
    @DisplayName("getStackTrace 带cause链")
    void testGetStackTraceWithCause() {
        Exception cause = new IllegalArgumentException("root cause");
        Exception ex = new RuntimeException("wrapper", cause);
        String trace = ExceptionUtils.getStackTrace(ex);
        assertNotNull(trace);
        assertTrue(trace.contains("root cause"));
        assertTrue(trace.contains("Caused by:"));
    }

    @Test
    @DisplayName("getStackTrace 过滤非应用堆栈")
    void testGetStackTraceFiltering() {
        Exception ex = new RuntimeException("filtered");
        String trace = ExceptionUtils.getStackTrace(null, ex, true, Pattern.compile("^com\\.(dlz)"));
        assertNotNull(trace);
        assertTrue(trace.contains("filtered"));
    }

    @Test
    @DisplayName("getStackTrace onlyShowAppLog=false 不过滤")
    void testGetStackTraceNoFilter() {
        Exception ex = new RuntimeException("unfiltered");
        String trace = ExceptionUtils.getStackTrace(null, ex, false, null);
        assertNotNull(trace);
        assertTrue(trace.contains("unfiltered"));
    }

    @Test
    @DisplayName("setCompiles 自定义pattern")
    void testSetCompiles() {
        ExceptionUtils.setCompiles("^com\\.(test)");
        Exception ex = new RuntimeException("custom");
        String trace = ExceptionUtils.getStackTrace(ex);
        assertNotNull(trace);
        // restore
        ExceptionUtils.setCompiles("^com\\.(dlz)");
    }
}
