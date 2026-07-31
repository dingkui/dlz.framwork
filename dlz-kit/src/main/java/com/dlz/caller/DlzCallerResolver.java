package com.dlz.caller;

import java.util.Collection;
import java.util.Set;

/** Resolves the first application frame after skipping configured infrastructure packages. */
public final class DlzCallerResolver {

    private DlzCallerResolver() {
    }

    public static String resolve(DlzCallerProperties properties) {
        return resolve(properties, 0);
    }

    public static String resolve(DlzCallerProperties properties, int additionalFramesToSkip) {
        Set<String> ignoredPackages = properties == null
                ? null : properties.getIgnoreCallerPackages();
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
        if (ignoredPackages != null) {
            for (String ignoredPackage : ignoredPackages) {
                if (matchesIgnoredClassName(className, ignoredPackage)) {
                    return true;
                }
            }
        }
        return className.contains("CGLIB$")
                || className.contains("$$Lambda$")
                || className.contains("lambda$");
    }

    private static boolean matchesIgnoredClassName(String className, String ignoredPackage) {
        if (ignoredPackage == null || ignoredPackage.trim().isEmpty()) {
            return false;
        }
        String ignoredName = ignoredPackage.trim();
        return ignoredName.endsWith(".")
                ? className.startsWith(ignoredName) : className.equals(ignoredName);
    }
}
