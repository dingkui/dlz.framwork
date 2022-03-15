package com.dlz.framework.db.cache;

import com.dlz.framework.cache.service.AbstractCache;
import com.dlz.framework.db.dao.IDlzDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
 * 数据库表字段缓存
 */
public class TableInfoCache extends AbstractCache<String, HashMap<String, Integer>> {

    @Autowired
    IDlzDao dao;

    public TableInfoCache() {
        super();
        dbOperator = new DbOperator() {
            protected HashMap<String, Integer> getFromDb(String tableName) {
                return dao.getTableColumsInfo(tableName);
            }
        };
    }
}
