package com.dlz.caller;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class DlzCallerResolverTest {
    private final StackTraceElement reflection = new StackTraceElement("java.lang.reflect.Method", "invoke", "Method.java", 10);
    private final StackTraceElement framework = new StackTraceElement("org.apache.ibatis.Executor", "query", "Executor.java", 20);
    private final StackTraceElement wrapper = new StackTraceElement("example.dao.BaseDao", "find", "BaseDao.java", 30);
    private final StackTraceElement business = new StackTraceElement("org.example.OrderService", "find", "OrderService.java", 40);

    @Test void detailPreservesLocationAndFrameworkOrderWithoutRuntimeNoise() {
        DlzCallerProperties p = new DlzCallerProperties();
        p.addIgnoreCallerPackage("org.apache.ibatis.", "example.dao.");
        StackTraceElement[] frames = {reflection, framework, wrapper, business};
        DlzCallerResolver.CallerDetails detail = DlzCallerResolver.resolveFrames(p, 0, true, frames);
        assertEquals("(OrderService.java:40)", detail.getCaller());
        assertEquals(Arrays.asList(framework, wrapper), detail.getSkippedFrames());
        assertThrows(UnsupportedOperationException.class, () -> detail.getSkippedFrames().clear());
        DlzCallerResolver.CallerDetails normal = DlzCallerResolver.resolveFrames(p, 0, false, frames);
        assertEquals(detail.getCaller(), normal.getCaller());
        assertTrue(normal.getSkippedFrames().isEmpty());
    }

    @Test void additionalSkipAndMissingSourceRemainSupported() {
        StackTraceElement unknown = new StackTraceElement("example.Caller", "run", null, -1);
        DlzCallerResolver.CallerDetails result = DlzCallerResolver.resolveFrames(new DlzCallerProperties(),
                1, true, new StackTraceElement[]{business, unknown});
        assertEquals("(example.Caller:-1)", result.getCaller());
        assertEquals(Arrays.asList(business), result.getSkippedFrames());
        assertEquals("", DlzCallerResolver.resolveFrames(new DlzCallerProperties(), 0, true,
                new StackTraceElement[]{reflection}).getCaller());
    }
}
