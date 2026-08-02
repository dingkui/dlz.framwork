package com.dlz.caller.mybatis;

import com.dlz.caller.DlzCallerResolver;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DlzMybatisSqlLogPropertiesTest {

    @Test
    void defaultsToCompleteDiagnosticOutput() {
        DlzSqlLogProperties properties = new DlzSqlLogProperties();

        assertTrue(properties.isEnabled());
        assertTrue(properties.isShowCaller());
        assertTrue(properties.isShowMapper());
        assertTrue(properties.isInjectCallerMdc());
        assertTrue(properties.getIgnoreCallerPackages().contains("com.dlz.caller."));
    }

    @Test
    void honorsConfiguredCallerPackagePrefixes() {
        DlzSqlLogProperties properties = new DlzSqlLogProperties();

        assertTrue(DlzCallerResolver.isIgnored(
                "com.dlz.caller.mybatis.DlzMybatisSqlLogInterceptor", properties.getIgnoreCallerPackages()));
        assertTrue(DlzCallerResolver.isIgnored(
                "org.apache.ibatis.executor.SimpleExecutor", properties.getIgnoreCallerPackages()));
        assertTrue(DlzCallerResolver.isIgnored(
                "com.example.infrastructure.Proxy", Arrays.asList("com.example.infrastructure.")));
    }

    @Test
    void appliesNativeMybatisPluginProperties() {
        Properties source = new Properties();
        source.setProperty("showCaller", "false");
        source.setProperty("callerMdcKey", "source");
        source.setProperty("ignoreCallerPackages", "com.example.http.,com.example.rpc.");
        DlzSqlLogProperties properties = new DlzSqlLogProperties();

        properties.apply(source);

        org.junit.jupiter.api.Assertions.assertFalse(properties.isShowCaller());
        org.junit.jupiter.api.Assertions.assertEquals("source", properties.getCallerMdcKey());
        assertTrue(properties.getIgnoreCallerPackages().contains("com.example.http."));
        assertTrue(properties.getIgnoreCallerPackages().contains("com.example.rpc."));
    }

    @Test
    void acceptsPluralIgnorePackagesPropertyBinding() {
        DlzSqlLogProperties properties = new DlzSqlLogProperties();

        properties.setIgnoreCallerPackages(new HashSet<>(Arrays.asList("com.example.persistence.")));

        assertTrue(properties.getIgnoreCallerPackages().contains("com.dlz.caller."));
        assertTrue(properties.getIgnoreCallerPackages().contains("com.example.persistence."));
    }
}
