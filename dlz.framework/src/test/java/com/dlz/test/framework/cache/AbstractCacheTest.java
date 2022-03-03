package com.dlz.test.framework.cache;


import com.dlz.framework.cache.service.AbstractCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AbstractCacheTest extends AbstractCache<String, String> {
    public AbstractCacheTest() {
        super();
        this.dbOperator = new DbOperator() {
            protected String getFromDb(String key) {
                return key+"value";
            }
        };
        this.timeToLiveSeconds=3600;
    }
}