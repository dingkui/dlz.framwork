package com.dlz.test.caller;

import com.dlz.caller.DlzCaller;
import com.dlz.caller.DlzCallerContext;
import com.dlz.caller.DlzCallerProperties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DlzCallerContextTest {

    @AfterEach
    void clearMdc() {
        MDC.clear();
    }

    @Test
    void keepsTheOuterCallerAcrossNestedInfrastructureScopes() {
        DlzCallerProperties properties = new DlzCallerProperties();
        DlzCaller.setProperties(properties);
        try (DlzCallerContext outer = DlzCaller.caller(0)) {
            String outerCaller = outer.getCaller();
            assertEquals(outerCaller, MDC.get(DlzCallerContext.mdcKey));
        }
        assertNull(MDC.get(DlzCallerContext.mdcKey));
    }

    @Test
    void preservesAnExistingBusinessCaller() {
        MDC.put("caller", "(Controller.java:20)");
        DlzCallerProperties properties = new DlzCallerProperties();
        DlzCaller.setProperties(properties);

        try (DlzCallerContext ignored = DlzCaller.caller(0)) {
            assertEquals("(Controller.java:20)", MDC.get("caller"));
        }

        assertEquals("(Controller.java:20)", MDC.get("caller"));
    }
}
