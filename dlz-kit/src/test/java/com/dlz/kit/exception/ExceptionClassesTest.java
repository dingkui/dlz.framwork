package com.dlz.kit.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("异常类测试")
class ExceptionClassesTest {

    @Nested
    @DisplayName("BaseException测试")
    class BaseExceptionTests {

        @Test
        @DisplayName("构造函数 code + message")
        void testConstructorCodeMessage() {
            BaseException ex = new SystemException("test msg");
            assertEquals(6001, ex.getCode());
            assertEquals("test msg", ex.getMsg());
            assertNotNull(ex.getInfo());
            assertTrue(ex.getMessage().contains("test msg"));
        }
//
//        @Test
//        @DisplayName("构造函数 code + Throwable")
//        void testConstructorCodeThrowable() {
//            RuntimeException cause = new RuntimeException("cause");
//            BaseException ex = new SystemException("wrapper", cause);
//            assertEquals(6001, ex.getCode());
//            assertTrue(ex.getSuppressed().length > 0);
//        }

        @Test
        @DisplayName("is 方法判断错误码")
        void testIsCode() {
            BaseException ex = new SystemException("test");
            assertTrue(ex.is(6001));
            assertFalse(ex.is(3001));
        }

        @Test
        @DisplayName("getInfo 返回错误码信息")
        void testGetInfo() {
            BaseException ex = new SystemException("test");
            assertEquals("系统异常", ex.getInfo());
        }
    }

    @Nested
    @DisplayName("SystemException测试")
    class SystemExceptionTests {

        @Test
        @DisplayName("基本构造")
        void testConstruction() {
            SystemException ex = new SystemException("sys error");
            assertEquals(6001, ex.getCode());
        }

        @Test
        @DisplayName("build(Throwable) Error")
        void testBuildFromError() {
            SystemException ex = SystemException.build(new OutOfMemoryError("oom"));
            assertNotNull(ex);
            assertTrue(ex.getMsg().contains("oom"));
        }

        @Test
        @DisplayName("build(Throwable) IllegalAccessException")
        void testBuildFromIllegalAccess() {
            SystemException ex = SystemException.build(new IllegalAccessException("no access"));
            assertTrue(ex.getMsg().contains("无效访问"));
        }

        @Test
        @DisplayName("build(Throwable) InvocationTargetException")
        void testBuildFromInvocationTarget() throws Exception {
            InvocationTargetException ite = new InvocationTargetException(
                    new RuntimeException("target error"));
            SystemException ex = SystemException.build(ite);
            assertTrue(ex.getMsg().contains("无效目标"));
        }

        @Test
        @DisplayName("build(Throwable) RuntimeException")
        void testBuildFromRuntimeException() {
            SystemException ex = SystemException.build(new RuntimeException("runtime"));
            assertTrue(ex.getMsg().contains("运行异常"));
        }

        @Test
        @DisplayName("build(Throwable) InterruptedException")
        void testBuildFromInterrupted() {
            SystemException ex = SystemException.build(new InterruptedException("interrupted"));
            assertTrue(ex.getMsg().contains("中断异常"));
            assertTrue(Thread.currentThread().isInterrupted());
            Thread.interrupted(); // clear
        }

        @Test
        @DisplayName("build(String, Throwable)")
        void testBuildMessageAndCause() {
            SystemException ex = SystemException.build("custom", new RuntimeException("cause"));
            assertEquals("custom", ex.getMsg());
        }

        @Test
        @DisplayName("build(String)")
        void testBuildMessage() {
            SystemException ex = SystemException.build("simple");
            assertEquals("simple", ex.getMsg());
        }

        @Test
        @DisplayName("isTrue 表达式为false时抛异常")
        void testIsTrueThrows() {
            assertThrows(SystemException.class, () ->
                    SystemException.isTrue(false, "must be true"));
        }

        @Test
        @DisplayName("isTrue 表达式为true时不抛异常")
        void testIsTrueNoThrow() {
            assertDoesNotThrow(() ->
                    SystemException.isTrue(true, "ok"));
        }

        @Test
        @DisplayName("notNull null时抛异常")
        void testNotNullThrows() {
            assertThrows(SystemException.class, () ->
                    SystemException.notNull(null, "not null"));
        }

        @Test
        @DisplayName("notEmpty 空字符串时抛异常")
        void testNotEmptyThrows() {
            assertThrows(SystemException.class, () ->
                    SystemException.notEmpty("", "not empty"));
        }

        @Test
        @DisplayName("notEmpty 非空时不抛异常")
        void testNotEmptyNoThrow() {
            assertDoesNotThrow(() ->
                    SystemException.notEmpty("value", "ok"));
        }
    }

    @Nested
    @DisplayName("ValidateException测试")
    class ValidateExceptionTests {

        @Test
        @DisplayName("基本构造")
        void testConstruction() {
            ValidateException ex = new ValidateException("validate error");
            assertEquals(3003, ex.getCode());
            assertEquals("validate error", ex.getMsg());
        }

        @Test
        @DisplayName("build 静态构造")
        void testBuild() {
            ValidateException ex = ValidateException.build("build msg");
            assertEquals("build msg", ex.getMsg());
        }

        @Test
        @DisplayName("isTrue 表达式为false时抛异常")
        void testIsTrueThrows() {
            assertThrows(ValidateException.class, () ->
                    ValidateException.isTrue(false, "must be true"));
        }

        @Test
        @DisplayName("isTrue 表达式为true时不抛异常")
        void testIsTrueNoThrow() {
            assertDoesNotThrow(() ->
                    ValidateException.isTrue(true, "ok"));
        }

        @Test
        @DisplayName("isTrue Supplier版本")
        void testIsTrueSupplier() {
            assertThrows(ValidateException.class, () ->
                    ValidateException.isTrue(false, () -> "lazy msg"));
            assertDoesNotThrow(() ->
                    ValidateException.isTrue(true, () -> "ok"));
        }

        @Test
        @DisplayName("notNull null时抛异常")
        void testNotNullThrows() {
            assertThrows(ValidateException.class, () ->
                    ValidateException.notNull(null, "not null"));
        }

        @Test
        @DisplayName("notNull 非null不抛")
        void testNotNullNoThrow() {
            assertDoesNotThrow(() ->
                    ValidateException.notNull("val", "msg"));
        }

        @Test
        @DisplayName("notNull Supplier版本")
        void testNotNullSupplier() {
            assertThrows(ValidateException.class, () ->
                    ValidateException.notNull(null, () -> "null!"));
        }

        @Test
        @DisplayName("notEmpty 空值抛异常")
        void testNotEmptyThrows() {
            assertThrows(ValidateException.class, () ->
                    ValidateException.notEmpty("", "not empty"));
        }

        @Test
        @DisplayName("notEmpty 非空不抛")
        void testNotEmptyNoThrow() {
            assertDoesNotThrow(() ->
                    ValidateException.notEmpty("val", "msg"));
        }

        @Test
        @DisplayName("notEmpty Supplier版本")
        void testNotEmptySupplier() {
            assertThrows(ValidateException.class, () ->
                    ValidateException.notEmpty("", () -> "empty!"));
        }
    }

    @Nested
    @DisplayName("BussinessException测试")
    class BussinessExceptionTests {

        @Test
        @DisplayName("基本构造不会重复注册错误码")
        void testConstruction() {
            BussinessException ex = new BussinessException("business error");
            assertEquals(3001, ex.getCode());
            assertEquals("业务异常", ex.getInfo());
        }

        @Test
        @DisplayName("isTrue使用标准断言语义")
        void testIsTrue() {
            assertThrows(BussinessException.class,
                    () -> BussinessException.isTrue(false, "must be true"));
            assertDoesNotThrow(() -> BussinessException.isTrue(true, "ok"));
        }

        @Test
        @DisplayName("Supplier仅在断言失败时求值")
        void testSupplier() {
            assertThrows(BussinessException.class,
                    () -> BussinessException.isTrue(false, () -> "lazy message"));
            assertDoesNotThrow(() -> BussinessException.isTrue(true, () -> {
                throw new AssertionError("supplier should not be evaluated");
            }));
        }
    }

    @Nested
    @DisplayName("ExceptionErrors测试")
    class ExceptionErrorsTests {

        @Test
        @DisplayName("getInfo 已知code")
        void testGetInfoKnown() {
            assertEquals("系统异常", ExceptionErrors.getInfo(6001));
            assertEquals("业务异常", ExceptionErrors.getInfo(3001));
            assertEquals("校验错误", ExceptionErrors.getInfo(3003));
        }

        @Test
        @DisplayName("getInfo 未知code抛异常")
        void testGetInfoUnknown() {
            assertThrows(SystemException.class, () ->
                    ExceptionErrors.getInfo(99999));
        }

        @Test
        @DisplayName("addErrors 重复code抛异常")
        void testAddDuplicateErrors() {
            assertThrows(SystemException.class, () ->
                    ExceptionErrors.addErrors(6001, "duplicate"));
        }
    }
}
