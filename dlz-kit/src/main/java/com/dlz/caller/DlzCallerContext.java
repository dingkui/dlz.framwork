package com.dlz.caller;

import org.slf4j.MDC;

/**
 * Thread-bound caller scope managed via MDC.
 */
public final class DlzCallerContext implements AutoCloseable {
    public static final String mdcKey="dlz-caller";
    private final String caller;
    private boolean closed;

    private DlzCallerContext(String resolvedCaller) {
        this.caller = resolvedCaller;
        MDC.put(mdcKey, resolvedCaller);
    }

    public static DlzCallerContext open(String resolvedCaller) {
        return new DlzCallerContext(resolvedCaller);
    }

    public String getCaller() {
        return caller == null ? "" : caller;
    }

    @Override
    public void close() {
        if (closed) {
            return;
        }
        closed = true;
        MDC.remove(mdcKey);
    }
}
