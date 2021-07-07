package com.dlz.test.framework.cache;

import com.dlz.test.framework.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CacheAnnoTest extends BaseTest {
    @Autowired
    CacheTestService bean;

    @Test
    public void t1() {
        bean.getInfo1("a", "b");
        bean.getInfo1("a", "b");
        bean.getInfo1("a-1", "b-1");
    }

    @Test
    public void t2() {
        bean.getInfo2("a2", "b2");
        bean.getInfo2("a2", "b2");
        bean.getInfo2("a2-1", "b2-1");
    }

    @Test
    public void t3() {
        bean.getInfo3("a3", "b3");
        bean.getInfo3("a3", "b3");
        bean.getInfo3("a3-1", "b3-1");
    }

}
