package com.dlz.kit.json;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 验证 JDK 17+ 强封装下，InternalJsonMapper 序列化包含 JDK 内部类型（如 Class）的对象不抛
 * {@link java.lang.reflect.InaccessibleObjectException}。
 */
class InternalJsonMapperJdk17Test {

    static class BeanWithClassField {
        private Class<?> type;
        private String name;

        public BeanWithClassField(Class<?> type, String name) {
            this.type = type;
            this.name = name;
        }

        public Class<?> getType() { return type; }
        public String getName() { return name; }
    }

    static class BeanWithJdkField {
        private Thread thread;
        private String label;

        public BeanWithJdkField(Thread thread, String label) {
            this.thread = thread;
            this.label = label;
        }

        public Thread getThread() { return thread; }
        public String getLabel() { return label; }
    }

    @Test
    void testSerializeClassField() {
        InternalJsonMapper mapper = new InternalJsonMapper();
        BeanWithClassField bean = new BeanWithClassField(String.class, "hello");
        String json = mapper.write(bean);
        assertNotNull(json);
        // 不应抛 InaccessibleObjectException，应成功序列化
        assertTrue(json.contains("java.lang.String") || json.contains("type"), "序列化结果应包含 type: " + json);
    }

    @Test
    void testSerializeJdkInternalField() {
        InternalJsonMapper mapper = new InternalJsonMapper();
        BeanWithJdkField bean = new BeanWithJdkField(Thread.currentThread(), "test");
        String json = mapper.write(bean);
        assertNotNull(json);
        // 不应抛 InaccessibleObjectException
        assertTrue(json.contains("test"));
    }
}
