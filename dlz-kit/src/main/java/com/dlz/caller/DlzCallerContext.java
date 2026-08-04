package com.dlz.caller;

import org.slf4j.MDC;

/**
 * Thread-bound caller scope. The previous MDC value is preserved across nested scopes.
 */
public final class DlzCallerContext implements AutoCloseable {

    private final String mdcKey;
    private final String previousCaller;
    private final boolean modified;
    private final String caller;
    private boolean closed;

    private DlzCallerContext(DlzCallerProperties properties, String resolvedCaller) {
        String configuredKey = properties.getCallerMdcKey();
        this.mdcKey = configuredKey == null || configuredKey.trim().isEmpty() ? "caller" : configuredKey.trim();
        this.previousCaller = MDC.get(mdcKey);
        this.caller = previousCaller == null || previousCaller.isEmpty() ? resolvedCaller : previousCaller;
        this.modified = properties.isInjectCallerMdc()
                && previousCaller == null && resolvedCaller != null && !resolvedCaller.isEmpty();
        if (modified) {
            MDC.put(mdcKey, resolvedCaller);
        }
    }

    public static DlzCallerContext open(DlzCallerProperties properties, String resolvedCaller) {
        return new DlzCallerContext(properties, resolvedCaller);
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
        if (modified) {
            if (previousCaller == null) {
                MDC.remove(mdcKey);
            } else {
                MDC.put(mdcKey, previousCaller);
            }
        }
    }
}
