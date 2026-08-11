package com.dlz.test.caller;

import com.dlz.caller.DlzCaller;
import com.dlz.caller.DlzCaller;
import com.dlz.caller.DlzCallerProperties;
import com.dlz.kit.mdc.MdcContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;

import static org.junit.jupiter.api.Assertions.*;

class DlzCallerTest {
    @BeforeAll
    static void setUp() {
        DlzCaller.setProperties(new DlzCallerProperties());
    }

    @Test
    void callerScopeCleansMdcAfterAUtilityCallFails() {
        try {
            try (MdcContext context = DlzCaller.caller(0)) {
                assertEquals(context.get(), MDC.get(DlzCaller.MDC_KEY_DLZ_CALLER));
                throw new IllegalStateException("simulated redis timeout");
            }
        } catch (IllegalStateException expected) {
            assertEquals("simulated redis timeout", expected.getMessage());
        }

        assertNull(MDC.get("caller"));
    }

    @Test
    void keepsTheOuterCallerAcrossNestedInfrastructureScopes() {
        DlzCallerProperties properties = new DlzCallerProperties();
        DlzCaller.setProperties(properties);
        try (MdcContext outer = DlzCaller.caller(0)) {
            String outerCaller = outer.get();
            assertEquals(outerCaller, MDC.get(DlzCaller.MDC_KEY_DLZ_CALLER));
        }
        assertNull(MDC.get(DlzCaller.MDC_KEY_DLZ_CALLER));
    }

    @Test
    void preservesAnExistingBusinessCaller() {
        MDC.put("caller", "(Controller.java:20)");
        DlzCallerProperties properties = new DlzCallerProperties();
        DlzCaller.setProperties(properties);

        try (MdcContext ignored = DlzCaller.caller(0)) {
            assertEquals("(Controller.java:20)", MDC.get("caller"));
        }

        assertEquals("(Controller.java:20)", MDC.get("caller"));
    }
}
