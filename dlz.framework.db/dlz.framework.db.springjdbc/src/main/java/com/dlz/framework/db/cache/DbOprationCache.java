package com.dlz.framework.db.cache;

import com.dlz.framework.cache.service.AbstractCache;
import com.dlz.framework.db.modal.Page;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 数据库查询结果缓存
 *
 * @author dingkui
 */
public class DbOprationCache extends AbstractCache<String, Page> {
    public DbOprationCache() {
        super(DbOprationCache.class.getSimpleName());
    }
}
