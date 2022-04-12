package com.dlz.test.framework.db;

import com.dlz.framework.db.service.ICommService;
import com.dlz.test.framework.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 单元测试支撑类<br>
 *
 * @author dk
 */
public class CommServiceJdbcTest extends BaseTest {
    @Autowired
    ICommService commService;

    @Test
    public void getInt() {
        commService.getIntList("select 1 from xx where x=?", "666");
    }
}
