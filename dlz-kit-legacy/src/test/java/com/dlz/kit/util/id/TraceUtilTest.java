package com.dlz.kit.util.id;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TraceUtil测试")
class TraceUtilTest {

    @BeforeEach
    void setUp() {
        MDC.clear();
    }

    @AfterEach
    void tearDown() {
        MDC.clear();
    }

    @Test
    @DisplayName("getTraceid 无设置时返回null")
    void testGetTraceidNull() {
        assertNull(TraceUtil.getTraceid());
    }

    @Test
    @DisplayName("setTraceId() 无参数自动生成")
    void testSetTraceIdAutoGenerate() {
        String traceId = TraceUtil.setTraceId();
        assertNotNull(traceId);
        assertEquals(traceId, TraceUtil.getTraceid());
    }

    @Test
    @DisplayName("setTraceId(value) 设置指定值")
    void testSetTraceIdWithValue() {
        String traceId = TraceUtil.setTraceId("custom-trace-id");
        assertEquals("custom-trace-id", traceId);
        assertEquals("custom-trace-id", TraceUtil.getTraceid());
    }

    @Test
    @DisplayName("setTraceId 重复设置相同值")
    void testSetTraceIdSameValue() {
        TraceUtil.setTraceId("same");
        String result = TraceUtil.setTraceId("same");
        assertEquals("same", result);
    }

    @Test
    @DisplayName("setTraceId 重复设置不同值")
    void testSetTraceIdDifferentValue() {
        TraceUtil.setTraceId("first");
        String result = TraceUtil.setTraceId("second");
        assertEquals("second", result);
        assertEquals("second", TraceUtil.getTraceid());
    }

    @Test
    @DisplayName("clearTraceId 清除已设置的traceId")
    void testClearTraceId() {
        TraceUtil.setTraceId("to-clear");
        TraceUtil.clearTraceId();
        assertNull(TraceUtil.getTraceid());
    }

    @Test
    @DisplayName("clearTraceId 无设置时不异常")
    void testClearTraceIdWhenNull() {
        assertDoesNotThrow(() -> TraceUtil.clearTraceId());
    }

    @Test
    @DisplayName("makeTraceId 无现有id时生成新id")
    void testMakeTraceIdNew() {
        String traceId = TraceUtil.makeTraceId();
        assertNotNull(traceId);
        assertEquals(8, traceId.length());
    }

    @Test
    @DisplayName("makeTraceId 已有id时返回现有id")
    void testMakeTraceIdExisting() {
        TraceUtil.setTraceId("existing");
        String traceId = TraceUtil.makeTraceId();
        assertEquals("existing", traceId);
    }
}
