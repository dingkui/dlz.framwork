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
    private boolean injectCallerMdc = false;

    private Set<String> ignoreCallerPackages = new HashSet<>();

    public void setInjectCallerMdc(boolean injectCallerMdc) {
        this.injectCallerMdc = injectCallerMdc;
    }
    public void setIgnoreCallerPackages(Collection<String> ignoreCallerPackages ) {
        if(ignoreCallerPackages!=null){
            this.ignoreCallerPackages.addAll(ignoreCallerPackages);
        }
    }

    public void addIgnoreCallerPackage(String... packagePrefix) {
        Arrays.stream(packagePrefix)
                .filter(s -> s!=null && !s.trim().isEmpty())
                .forEach(s -> ignoreCallerPackages.add(s.trim()));
    }
}
