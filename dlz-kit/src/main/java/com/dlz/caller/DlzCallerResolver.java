package com.dlz.caller;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Resolves the first application frame after skipping configured infrastructure packages.
 */
public final class DlzCallerResolver {

    private DlzCallerResolver() {
    }

    public static String resolve(DlzCallerProperties properties) {
        return resolve(properties, 0);
    }

    public static String resolve(DlzCallerProperties properties, int additionalFramesToSkip) {
        Set<String> ignoredPackages = new HashSet<>(properties.getIgnoreCallerPackages());
        int remainingFramesToSkip = Math.max(0, additionalFramesToSkip);
        StackTraceElement[] trace = new Throwable().getStackTrace();
        for (int index = 1; index < trace.length; index++) {
            StackTraceElement frame = trace[index];
            if (!isIgnored(frame.getClassName(), ignoredPackages)) {
                if (remainingFramesToSkip > 0) {
                    remainingFramesToSkip--;
                    continue;
                }
                String source = frame.getFileName() == null ? frame.getClassName() : frame.getFileName();
                return "(" + source + ":" + frame.getLineNumber() + ")";
            }
        }
        return "";
    }

    public static boolean isIgnored(String className, Collection<String> ignoredPackages) {
        if (className.startsWith("com.dlz.caller")
                || className.startsWith("java")
                || className.startsWith("jdk")
                || className.startsWith("sun")
        ) {
            return true;
        }
        if (ignoredPackages != null) {
            for (String ignoredPackage : ignoredPackages) {
                if (className.startsWith(ignoredPackage)) {
                    return true;
                }
            }
        }
        return className.contains("CGLIB$") || className.contains("lambda$");
    }
}
