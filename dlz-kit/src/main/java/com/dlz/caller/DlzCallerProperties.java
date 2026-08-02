package com.dlz.caller;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Configuration shared by caller resolution and MDC injection.
 */
@Getter
public class DlzCallerProperties {

    private boolean injectCallerMdc = true;
    private String callerMdcKey = "caller";
    private Set<String> ignoreCallerPackages = new HashSet<>(Arrays.asList(
            "com.dlz.caller.",
            "org.apache.ibatis.",
            "org.mybatis.",
            "com.baomidou.",
            "org.springframework.",
            "java.",
            "javax.",
            "jdk.",
            "sun."
    ));

    public void setCallerMdcKey(String callerMdcKey) {
        if (callerMdcKey != null && !callerMdcKey.trim().isEmpty()) {
            this.callerMdcKey = callerMdcKey.trim();
        }
    }
    public void setInjectCallerMdc(boolean injectCallerMdc) {
        this.injectCallerMdc = injectCallerMdc;
    }
    public void setIgnoreCallerPackage(Set<String> ignoreCallerPackages) {
        addIgnoreCallerPackage(ignoreCallerPackages);
    }
    public void setIgnoreCallerPackages(Set<String> ignoreCallerPackages) {
        addIgnoreCallerPackage(ignoreCallerPackages);
    }
    public void addIgnoreCallerPackage(Collection<String> ignoreCallerPackages) {
        if (ignoreCallerPackages == null) {
            return;
        }
        this.ignoreCallerPackages.addAll(ignoreCallerPackages);
    }

    public void addIgnoreCallerPackage(String... packagePrefix) {
        Arrays.stream(packagePrefix)
                .filter(s -> s!=null && !s.trim().isEmpty())
                .forEach(s -> ignoreCallerPackages.add(s.trim()));
    }
}
