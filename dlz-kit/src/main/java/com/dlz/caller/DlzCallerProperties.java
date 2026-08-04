package com.dlz.caller;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * Configuration shared by caller resolution and MDC injection.
 */
@Getter
@Setter
public class DlzCallerProperties {

    private boolean injectCallerMdc = true;
    private String callerMdcKey = "caller";

    private Set<String> ignoreCallerPackages = new HashSet<>();

    public void setCallerMdcKey(String callerMdcKey) {
        if (callerMdcKey != null && !callerMdcKey.trim().isEmpty()) {
            this.callerMdcKey = callerMdcKey.trim();
        }
    }
    public void setInjectCallerMdc(boolean injectCallerMdc) {
        this.injectCallerMdc = injectCallerMdc;
    }

    public void addIgnoreCallerPackage(String... packagePrefix) {
        Arrays.stream(packagePrefix)
                .filter(s -> s!=null && !s.trim().isEmpty())
                .forEach(s -> ignoreCallerPackages.add(s.trim()));
    }
}
