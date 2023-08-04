package com.dlz.test.service;

import com.dlz.framework.db.helper.util.SqlHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleTest {
    @Autowired
    SqlHelper sqlHelper;
    @Test
    public void doTest() {
        int num = new Integer(1);
    }
}