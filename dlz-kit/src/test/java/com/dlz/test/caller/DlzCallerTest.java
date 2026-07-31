package com.dlz.test.caller;

import com.dlz.caller.DlzCaller;
import com.dlz.caller.DlzCallerContext;
import com.dlz.caller.DlzCallerProperties;
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


    @AfterEach
    void resetGlobalContext() {
        MDC.clear();
        DlzCaller.clearCaller();
    }

    @Test
    void setCallerMakesTheBusinessSourceAvailableToAnyUtilityLog() {
        String caller = DlzCaller.setCaller();
        try {
            assertEquals(caller, MDC.get("caller"));
            assertTrue(caller.contains("DlzCallerTest.java"));
        } finally {
            DlzCaller.clearCaller();
        }
        assertNull(MDC.get("caller"));
    }

    @Test
    void callerScopeCleansMdcAfterAUtilityCallFails() {
        try {
            try (DlzCallerContext context = DlzCaller.caller(0)) {
                assertEquals(context.getCaller(), MDC.get("caller"));
                throw new IllegalStateException("simulated redis timeout");
            }
        } catch (IllegalStateException expected) {
            assertEquals("simulated redis timeout", expected.getMessage());
        }

        assertNull(MDC.get("caller"));
    }
}
