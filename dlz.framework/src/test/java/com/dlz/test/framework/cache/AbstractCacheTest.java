package com.dlz.test.framework.cache;


import com.dlz.framework.cache.ICache;
import com.dlz.framework.cache.service.AbstractCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AbstractCacheTest extends AbstractCache<String, String> {
    public AbstractCacheTest(ICache ehcahe) {
        super(AbstractCacheTest.class.getSimpleName().toLowerCase(), 3600);
        dbOperator = new DbOperator() {
            protected String getFromDb(String key) {
                return key+"value";
            }
        };
    }
}