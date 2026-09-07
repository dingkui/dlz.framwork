package com.dlz.caller;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        return resolveDetails(properties, additionalFramesToSkip, false).getCaller();
    }

    /** Resolves the caller and optionally retains skipped frames using one stack capture. */
    public static CallerDetails resolveDetails(DlzCallerProperties properties, boolean detailed) {
        return resolveDetails(properties, 0, detailed);
    }

    public static CallerDetails resolveDetails(DlzCallerProperties properties, int additionalFramesToSkip,
                                               boolean detailed) {
        return resolveFrames(properties, additionalFramesToSkip, detailed, new Throwable().getStackTrace());
    }

    static CallerDetails resolveFrames(DlzCallerProperties properties, int additionalFramesToSkip,
                                       boolean detailed, StackTraceElement[] trace) {
        Set<String> ignoredPackages = new HashSet<>(properties.getIgnoreCallerPackages());
        int remainingFramesToSkip = Math.max(0, additionalFramesToSkip);
        List<StackTraceElement> skipped = detailed ? new ArrayList<>() : Collections.emptyList();
        for (StackTraceElement frame : trace) {
            String className = frame.getClassName();
            if (isIgnored(className, ignoredPackages)) {
                if (detailed && isDetailFrame(className)) {
                    skipped.add(frame);
                }
                continue;
            }
            if (remainingFramesToSkip > 0) {
                remainingFramesToSkip--;
                if (detailed) skipped.add(frame);
                continue;
            }
            String source = frame.getFileName() == null ? className : frame.getFileName();
            return new CallerDetails("(" + source + ":" + frame.getLineNumber() + ")", skipped);
        }
        return new CallerDetails("", skipped);
    }

    private static boolean isDetailFrame(String className) {
        return !className.equals(DlzCallerResolver.class.getName())
                && !className.equals(DlzCaller.class.getName())
                && !className.startsWith("java.") && !className.startsWith("javax.")
                && !className.startsWith("jdk.") && !className.startsWith("sun.")
                && !className.startsWith("com.sun.proxy.")
                && !className.contains("CGLIB$") && !className.contains("$$Lambda$");
    }

    /** Immutable resolution result. Skipped frames are ordered from inner to outer calls. */
    public static final class CallerDetails {
        private final String caller;
        private final List<StackTraceElement> skippedFrames;

        private CallerDetails(String caller, List<StackTraceElement> skippedFrames) {
            this.caller = caller;
            this.skippedFrames = skippedFrames.isEmpty() ? Collections.emptyList()
                    : Collections.unmodifiableList(new ArrayList<>(skippedFrames));
        }

        public String getCaller() { return caller; }
        public List<StackTraceElement> getSkippedFrames() { return skippedFrames; }
    }

    public static boolean isIgnored(String className, Collection<String> ignoredPackages) {
        if (className.startsWith("com.dlz.caller")
                || className.startsWith("java")
                || className.startsWith("jdk")
                || className.startsWith("sun")
                || className.startsWith("com.sun.proxy.")
                || className.startsWith("org.springframework")
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
