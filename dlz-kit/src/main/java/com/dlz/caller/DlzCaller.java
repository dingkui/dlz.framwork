package com.dlz.caller;

import com.dlz.kit.mdc.MdcContext;

/**
 * High-frequency facade for adding caller information to infrastructure logs.
 */
public final class DlzCaller {
    private static volatile DlzCallerProperties properties = new DlzCallerProperties();
    public static final String MDC_KEY_DLZ_CALLER = "dlz-caller";

    private DlzCaller() {
    }

    public static void setProperties(DlzCallerProperties callerProperties) {
        if (callerProperties != null) {
            properties = callerProperties;
        }
    }

    public static MdcContext open(String resolvedCaller) {
        return MdcContext.open(MDC_KEY_DLZ_CALLER, resolvedCaller);
    }

    public static MdcContext caller(int additionalFramesToSkip) {
        return open(DlzCallerResolver.resolve(properties, additionalFramesToSkip));
    }

    public static MdcContext caller() {
        return caller(0);
    }
}
