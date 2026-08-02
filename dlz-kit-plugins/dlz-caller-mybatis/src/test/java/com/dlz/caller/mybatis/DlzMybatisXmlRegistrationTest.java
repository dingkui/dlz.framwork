package com.dlz.caller.mybatis;

import java.io.StringReader;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.session.Configuration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DlzMybatisXmlRegistrationTest {

    @Test
    void registersTheInterceptorFromTheDocumentedNativeMybatisXml() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"
                + "<!DOCTYPE configuration PUBLIC \"-//mybatis.org//DTD Config 3.0//EN\" "
                + "\"https://mybatis.org/dtd/mybatis-3-config.dtd\">"
                + "<configuration><plugins>"
                + "<plugin interceptor=\"com.dlz.caller.mybatis.DlzMybatisSqlLogInterceptor\">"
                + "<property name=\"enabled\" value=\"true\"/>"
                + "<property name=\"showCaller\" value=\"false\"/>"
                + "<property name=\"showMapper\" value=\"true\"/>"
                + "<property name=\"injectCallerMdc\" value=\"true\"/>"
                + "<property name=\"callerMdcKey\" value=\"source\"/>"
                + "<property name=\"ignoreCallerPackages\" value=\"com.example.http.,com.example.redis.\"/>"
                + "</plugin></plugins></configuration>";

        Configuration configuration = new XMLConfigBuilder(new StringReader(xml)).parse();

        assertEquals(1, configuration.getInterceptors().size());
        Object plugin = configuration.getInterceptors().get(0);
        assertInstanceOf(DlzMybatisSqlLogInterceptor.class, plugin);
        DlzSqlLogProperties properties = ((DlzMybatisSqlLogInterceptor) plugin).getProperties();
        assertTrue(properties.isEnabled());
        assertFalse(properties.isShowCaller());
        assertTrue(properties.isShowMapper());
        assertTrue(properties.isInjectCallerMdc());
        assertEquals("source", properties.getCallerMdcKey());
        assertTrue(properties.getIgnoreCallerPackages().contains("com.example.http."));
        assertTrue(properties.getIgnoreCallerPackages().contains("com.example.redis."));
    }
}
