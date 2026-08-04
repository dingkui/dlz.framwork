package com.dlz.caller;

import java.util.ArrayDeque;
import java.util.Deque;

/** High-frequency facade for adding caller information to infrastructure logs. */
public final class DlzCaller {

    private static volatile DlzCallerProperties properties = new DlzCallerProperties();
    private static final ThreadLocal<Deque<DlzCallerContext>> CONTEXTS = ThreadLocal.withInitial(() -> new ArrayDeque<>());

    private DlzCaller() {
    }

    public static DlzCallerProperties getProperties() {
        return properties;
    }

    public static void setProperties(DlzCallerProperties callerProperties) {
        if(callerProperties!=null){
            properties = callerProperties;
        }
    }

    public static DlzCallerContext caller(int additionalFramesToSkip) {
        return DlzCallerContext.open( DlzCallerResolver.resolve(properties, additionalFramesToSkip));
    }

    public static String setCaller() {
        return setCaller(0);
    }

    /**
     * Sets the caller for legacy try/finally usage.
     *
     * @param additionalFramesToSkip additional non-framework wrapper frames to skip
     */
    public static String setCaller(int additionalFramesToSkip) {
        DlzCallerContext context = caller(additionalFramesToSkip);
        CONTEXTS.get().push(context);
        return context.getCaller();
    }

    public static void clearCaller() {
        Deque<DlzCallerContext> contexts = CONTEXTS.get();
        if (contexts.isEmpty()) {
            CONTEXTS.remove();
            return;
        }
        contexts.pop().close();
        if (contexts.isEmpty()) {
            CONTEXTS.remove();
        }
    }
}
