package com.dlz.caller.mybatis;

import com.dlz.caller.DlzCallerResolver;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DlzMybatisSqlLogPropertiesTest {

    @Test
    void defaultsToCompleteDiagnosticOutput() {
        DlzSqlLogProperties properties = new DlzSqlLogProperties();

        assertTrue(properties.isEnabled());
        assertTrue(properties.isShowCaller());
        assertFalse(properties.isShowMapper());
        assertFalse(properties.isInjectCallerMdc());
    }

    @Test
    void honorsConfiguredCallerPackagePrefixes() {
        DlzSqlLogProperties properties = new DlzSqlLogProperties();
        new DlzMybatisSqlLogInterceptor(properties);

        assertTrue(DlzCallerResolver.isIgnored(
                "com.dlz.caller.mybatis.DlzMybatisSqlLogInterceptor", properties.getIgnoreCallerPackages()));
        assertTrue(DlzCallerResolver.isIgnored(
                "org.apache.ibatis.executor.SimpleExecutor", properties.getIgnoreCallerPackages()));
        assertTrue(DlzCallerResolver.isIgnored(
                "com.example.infrastructure.Proxy", Arrays.asList("com.example.infrastructure.")));
    }

    @Test
    void skipsJava8AndModernJdkProxyFrames() {
        assertTrue(DlzCallerResolver.isIgnored("com.sun.proxy.$Proxy51", java.util.Collections.emptySet()));
        assertTrue(DlzCallerResolver.isIgnored("jdk.proxy2.$Proxy51", java.util.Collections.emptySet()));
        assertFalse(DlzCallerResolver.isIgnored("example.OrderService", java.util.Collections.emptySet()));
    }

    @Test
    void acceptsPluralIgnorePackagesPropertyBinding() {
        DlzSqlLogProperties properties = new DlzSqlLogProperties();

        properties.setIgnoreCallerPackages(new HashSet<>(Arrays.asList("com.example.persistence.")));

        assertTrue(properties.getIgnoreCallerPackages().contains("com.example.persistence."));
    }
}
